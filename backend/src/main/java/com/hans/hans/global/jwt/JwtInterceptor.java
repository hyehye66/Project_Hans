package com.hans.hans.global.jwt;

import com.hans.hans.domain.member.service.MemberService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor  {
    private final JwtService jwtService;
    private final MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("#################Jwt Interceptor Start#################");

        String accessToken = request.getHeader("Authorization").replace("Bearer ","");
        String refreshToken = request.getHeader("jwt-refresh-token");

        if("OPTIONS".equals(request.getMethod())){
            System.out.println("request method is OPTIONS");
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
                                response.setHeader("jwt-access-token",newAccessToken);

                                return true;
                            }
                        }catch (IllegalArgumentException | JwtException e){
                            throw new JwtException("유효하지 않은 Refresh Token 입니다.");
                        }
                    }
                    else return true;
                }
                throw new JwtException("유효하지 않은 Access Token 입니다.");
            }else{
                response.sendRedirect("/api/login");
                response.setStatus(401);
                throw new IllegalArgumentException("로그인한 사용자만 접근가능합니다.");
            }
        }catch (IllegalArgumentException | JwtException e) {
            throw new JwtException("유효하지 않은 Access Token 입니다.");
        }

    }
}
