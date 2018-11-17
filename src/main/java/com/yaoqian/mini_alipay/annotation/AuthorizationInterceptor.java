package com.yaoqian.mini_alipay.annotation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yaoqian.mini_alipay.Service.TokenService;
import com.yaoqian.mini_alipay.entity.ResultEntity;
import com.yaoqian.mini_alipay.entity.TokenEntity;
import com.yaoqian.mini_alipay.tools.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;


@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenService tokenService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.getAnnotation(Authorization.class) == null){
            return true;
        }
        String authorization = request.getHeader(Constants.AUTHORIZATION);
        TokenEntity tokenEntity = tokenService.getToken(authorization);

        if (tokenService.checkToken(tokenEntity)) {
            //如果token验证成功，将token对应的用户id存在request中，便于之后注入
            request.setAttribute(Constants.CURRENT_USER_ID, tokenEntity.getUid());
            return true;
        }else{
            //如果token验证不成功，返回一个状态码为1005的返回类
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            try{
                ResultEntity result = ResultTools.error(1005);
                String jsonout = JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
                PrintWriter out = response.getWriter();
                out.append(jsonout);
                return false;
            }
            catch (Exception e){
                e.printStackTrace();
                response.setStatus(500);
                return false;
            }

        }
    }

}
