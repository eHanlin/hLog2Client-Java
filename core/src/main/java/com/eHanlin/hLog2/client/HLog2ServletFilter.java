package com.eHanlin.hLog2.client;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用來處理 logSessionId 的 filter ， 會從 request 的 cookie 中去尋找 logSessionId ， 若有找到就寫到 ThreadLocal 之中。
 */
public class HLog2ServletFilter implements Filter {

    private String sessionField = "JSESSIONID";
    public String getSessionField() {
        return sessionField;
    }
    public void setSessionField(String value) {
        sessionField = value;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try{
            HttpServletRequest req = (HttpServletRequest) request;

            String sessionId = null;

            Cookie[] cookies = req.getCookies();
            if(cookies != null){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals(sessionField)){
                        sessionId = cookie.getValue();
                        break;
                    }
                }
            }

            if(sessionId == null){
                HttpSession session = req.getSession(true);
                sessionId = session.getId();
            }

            HLog2ThreadLocal.setSession(sessionId);


            HttpServletResponse httpResponse = (HttpServletResponse) response;
            Cookie logSessionIdCookie = new Cookie(sessionField, sessionId);
            logSessionIdCookie.setPath("/");
            httpResponse.addCookie(logSessionIdCookie);

        }catch(Exception e){

        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
