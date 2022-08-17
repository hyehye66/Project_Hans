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

        if("OPTIONS".equals(request.getMethod())){
            return true;
        }

        try{
            String accessToken = request.getHeader("Authorization").replace("Bearer ","");
            String refreshToken = request.getHeader("refreshToken");

            if(jwtService.validateToken(accessToken)){
                String email = jwtService.getEmailFromPayload(accessToken);
                request.setAttribute("email",email);
                return true;
            } else {
                if(refreshToken != null){
                    try{
                        if(jwtService.validateToken(refreshToken) && jwtService.compareRefreshToken(refreshToken)){
                            // Access Token 재발급
                            String email = jwtService.getEmailFromPayload(accessToken);
                            String newAccessToken = jwtService.createAccessToken(email);
                            response.setHeader("Authorization","Bearer "+newAccessToken);
                            return true;
                        }
                    }catch (NoMatchRefreshTokenException | JwtException e){
                        throw new JwtException("유효하지 않은 Refresh Token 입니다.");
                    }
                }
                throw new JwtException("유효하지 않은 Access Token 입니다.");
            }
        }catch (IllegalArgumentException | JwtException e) {
            throw new JwtException("유효하지 않은 Access Token 입니다.");
        }catch (NullPointerException e){
            throw new NotLoggedInException("로그인한 사용자만 접근가능합니다.");
        }
    }
}
