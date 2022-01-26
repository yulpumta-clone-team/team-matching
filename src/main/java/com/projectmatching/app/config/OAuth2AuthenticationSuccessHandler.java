package com.projectmatching.app.config;

import com.projectmatching.app.domain.user.Role;
import com.projectmatching.app.domain.user.UserRepository;
import com.projectmatching.app.domain.user.dto.UserDto;
import com.projectmatching.app.util.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final AuthTokenProvider authTokenProvider;
    private final UserRepository userRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        UserDto user = toDto(oAuth2User);

        String token = authTokenProvider.createToken(user.getName(),Role.GUEST);
        log.info("Oatuh 로그인후 토큰 생성  : {}",token);

        writeTokenResponse(response,token);

    }

    private void writeTokenResponse(HttpServletResponse response, String token) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.addHeader("Authorization",token);
        response.setContentType("application/json;charset=UTF-8");

        var writer = response.getWriter();
        writer.println(token);
        writer.flush();
    }

    private UserDto toDto(OAuth2User oAuth2User) {
        var attributes = oAuth2User.getAttributes();
        return UserDto.builder()
                .email((String)attributes.get("email"))
                .name((String)attributes.get("name")).build();

    }
}
