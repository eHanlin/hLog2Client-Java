package example.service;

import com.eHanlin.hLog2.client.Level;
import com.eHanlin.hLog2.client.aop.ann.LogAround;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MainService {

    @LogAround(value = Level.INFO, include = {"email"})
    public Boolean checkUserPassword(String email, String password)
    {
        return true;
    }

    @LogAround(Level.DEBUG)
    public Map<String, Object> getUserInfo(String email)
    {
        Map userInfo = new HashMap();
        userInfo.put("email", email);
        return userInfo;
    }
}
