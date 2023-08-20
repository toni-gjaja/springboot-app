package hr.webshop.handler;

import hr.webshop.entity.AppUser;
import hr.webshop.entity.UserLog;
import hr.webshop.service.AppUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Date;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final AppUserService service;

    public CustomAuthenticationSuccessHandler(AppUserService service) {
        this.service = service;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{

        AppUser authUser = (AppUser) authentication.getPrincipal();
        String ipAddress = request.getRemoteAddr();

        UserLog log = new UserLog(new Date(), ipAddress, authUser);

        service.saveUserLog(log);

        response.sendRedirect("/profile");

    }
}
