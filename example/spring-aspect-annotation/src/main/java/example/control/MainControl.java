package example.control;

import com.eHanlin.hLog2.client.Level;
import com.eHanlin.hLog2.client.aop.ann.LogAround;
import com.eHanlin.hLog2.client.aop.ann.LogIntent;
import example.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainControl {

    @Autowired
    MainService service = null;

    @LogIntent("login")
    @LogAround(value = Level.DEBUG, include = {"email"})
    @RequestMapping(value = {"/user/login"}, method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> userLogin(
        @RequestParam("email") String email,
        @RequestParam("email") String password,
        HttpSession session)
    {
        boolean matched = service.checkUserPassword(email, password);
        if(matched){
            session.setAttribute("user", email);
        }
        Map result = new HashMap();
        result.put("success", matched);
        return result;
    }

    @LogIntent("getUserInfo")
    @LogAround(value = Level.DEBUG, exclude = {"session"})
    @RequestMapping(value = {"/user/info"}, method = {RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> userInfo(HttpSession session)
    {
        Map result = new HashMap();
        result.put("value", service.getUserInfo(session.getAttribute("user").toString()));
        result.put("success", true);
        return result;
    }

    @LogIntent("logout")
    @LogAround(value = Level.DEBUG, exclude = {"session"})
    @RequestMapping(value = {"/user/logout"}, method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> userLogout(HttpSession session)
    {
        session.removeAttribute("user");
        Map result = new HashMap();
        result.put("success", true);
        return result;
    }
}
