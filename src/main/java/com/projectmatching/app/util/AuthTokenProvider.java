package com.projectmatching.app.util;

import com.projectmatching.app.config.secret.Secret;
import com.projectmatching.app.domain.user.Role;
import com.projectmatching.app.domain.user.dto.UserLoginResDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthTokenProvider {


    private String secretKey = Secret.JWT_SECRET_KEY;

    // 토큰 유효시간 30분
    private long tokenValidTime = 30 * 60 * 1000L;

    private final UserDetailsService userDetailsService;

    // JWT 토큰 생성
    public String createToken(String userName, Role roles) {
        Claims claims = Jwts.claims().setSubject(userName); // JWT payload 에 저장되는 정보단위
        claims.put("roles", roles.getKey()); // 정보는 key / value 쌍으로 저장된다.
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime))// set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserName(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserName(String token)  {

        Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(Secret.JWT_SECRET_KEY)
                    .build().parseClaimsJws(token);


        return claims.getBody().get("username",String.class);

    }

    // Request의 Header에서 token 값을 가져옵니다. "Authorization" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 토큰 쿠키에 저장
     *
     */
    public void createCookie(HttpServletResponse response,String token){
        ResponseCookie cookie = ResponseCookie.from("Authorization",token)
                .httpOnly(true)
                .sameSite("lax")
                .maxAge(60*60)
                .path("/")
                .build();

        response.addHeader("Set-Cookie",cookie.toString());

    }


    /**
     * 쿠키에 있는 토큰 분석
     */

    public String resolveCookie(HttpServletRequest request){
        final Cookie[] cookies = request.getCookies();
        if(cookies == null)return  null;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("Authorization")){
                return cookie.getValue();
            }
        }
        return null;

    }

    public String createToken(UserLoginResDto user){
        Claims claims = Jwts.claims().setSubject(user.getName()); // JWT payload 에 저장되는 정보단위
        claims.put("roles", user.getRole().getKey()); // 정보는 key / value 쌍으로 저장된다.
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime))// set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();

    }
}
