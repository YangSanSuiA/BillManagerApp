package cn.yangsansui.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录拦截器
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws ServletException, IOException {
        HttpSession httpSession=request.getSession();
        if(httpSession.getAttribute("CURRENT_USER")!=null){
            return true;
        }
        //进行拦截
        request.setAttribute("msg","没有权限，请先进行登录！");
        request.getRequestDispatcher("/index.html").forward(request,response);
        return false;
    }

}
