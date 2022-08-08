package com.hans.hans.global.jwt;

import com.hans.hans.global.exception.NoMatchRefreshTokenException;
import com.hans.hans.global.exception.NotLoggedInException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor  {
    private final JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String accessToken = request.getHeader("Authorization").replace("Bearer ","");
        String refreshToken = request.getHeader("refreshToken");

        if("OPTIONS".equals(request.getMethod())){
            return true;
        }

        try{
            if(accessToken!=null){
                if(jwtService.validateToken(accessToken)){
                    String email = jwtService.getEmailFromPayload(accessToken);
                    request.setAttribute("email",email);
                    if(refreshToken != null) {
                        try{
                            if(jwtService.validateToken(refreshToken) && jwtService.compareRefreshToken(refreshToken,email)){
                                // Access Token 재발급
                                String newAccessToken = jwtService.createAccessToken(email);
                                response.setHeader("Authorization","Bearer "+newAccessToken);

                                return true;
                            }
                        }catch (NoMatchRefreshTokenException | JwtException e){
                            throw new JwtException("유효하지 않은 Refresh Token 입니다.");
                        }
                    }
                    else return true;
                }
                throw new JwtException("유효하지 않은 Access Token 입니다.");
            }else{
                response.sendRedirect("/api/login");
                response.setStatus(401);
                throw new NotLoggedInException("로그인한 사용자만 접근가능합니다.");
            }
        }catch (IllegalArgumentException | JwtException e) {
            throw new JwtException("유효하지 않은 Access Token 입니다.");
        }

    }
}
