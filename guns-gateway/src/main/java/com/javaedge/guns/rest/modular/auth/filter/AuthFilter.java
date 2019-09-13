package com.javaedge.guns.rest.modular.auth.filter;

import com.javaedge.guns.rest.modular.auth.util.JwtTokenUtil;
import com.javaedge.guns.core.base.tips.ErrorTip;
import com.javaedge.guns.core.util.RenderUtil;
import com.javaedge.guns.rest.common.CurrentUser;
import com.javaedge.guns.rest.common.exception.BizExceptionEnum;
import com.javaedge.guns.rest.config.properties.JwtProperties;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 对客户端请求的JWT token验证过滤器
 *
 * @author JavaEdge
 */
@Slf4j
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getServletPath().equals("/" + jwtProperties.getAuthPath())) {
            chain.doFilter(request, response);
            return;
        }

        // 配置忽略列表
        String ignoreUrl = jwtProperties.getIgnoreUrl();
        String[] ignoreUrls = ignoreUrl.split(",");
        for (int i = 0; i < ignoreUrls.length; i++) {
            if (request.getServletPath().equals(ignoreUrls[i])) {
                chain.doFilter(request, response);
                return;
            }
        }


        final String requestHeader = request.getHeader(jwtProperties.getHeader());
        String authToken = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            authToken = requestHeader.substring(7);
            // 通过Token获取userID，并且将之存入Threadlocal，以便后续业务调用
            String userId = jwtTokenUtil.getUsernameFromToken(authToken);
            if (userId == null) {
                return;
            } else {
                CurrentUser.saveUserId(userId);
            }

            //验证token是否过期,包含了验证jwt是否正确
            try {
                boolean flag = jwtTokenUtil.isTokenExpired(authToken);
                if (flag) {
                    RenderUtil.renderJson(response, new ErrorTip(BizExceptionEnum.TOKEN_EXPIRED.getCode(), BizExceptionEnum.TOKEN_EXPIRED.getMessage()));
                    return;
                }
            } catch (JwtException e) {
                //有异常就是token解析失败
                RenderUtil.renderJson(response, new ErrorTip(BizExceptionEnum.TOKEN_ERROR.getCode(), BizExceptionEnum.TOKEN_ERROR.getMessage()));
                return;
            }
        } else {
            //header没有带Bearer字段
            RenderUtil.renderJson(response, new ErrorTip(BizExceptionEnum.TOKEN_ERROR.getCode(), BizExceptionEnum.TOKEN_ERROR.getMessage()));
            return;
        }
        chain.doFilter(request, response);
    }
}