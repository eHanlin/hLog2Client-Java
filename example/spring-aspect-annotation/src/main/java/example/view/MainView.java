package example.view;

import com.eHanlin.hLog2.client.aop.ann.LogBefore;
import com.eHanlin.hLog2.client.aop.ann.LogIntent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainView {

    @LogIntent("requestLoginPage")
    @LogBefore
    @RequestMapping(value = {"/login.html"}, method = {RequestMethod.GET})
    public String loginPage(){
        return "login";
    }

    @LogIntent("requestUserInfoPage")
    @LogBefore
    @RequestMapping(value = {"/userInfo.html"}, method = {RequestMethod.GET})
    public String userInfoPage(){
        return "userInfo";
    }

}
