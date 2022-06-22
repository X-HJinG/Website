package Server.interceptor;

import Server.utils.JwtUtil;
import Server.utils.ResultEnum;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminTokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(token==null){
            return false ;
        }
        else if(JwtUtil.checkSign(token)&&JwtUtil.isAdmin(token)){
            return true;
        }
        response.setHeader("Access-Control-Expose-Headers","*");
        response.setHeader("code", String.valueOf(ResultEnum.PERMISSION_DENIED.code));
        response.setHeader("msg",ResultEnum.PERMISSION_DENIED.msg);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
