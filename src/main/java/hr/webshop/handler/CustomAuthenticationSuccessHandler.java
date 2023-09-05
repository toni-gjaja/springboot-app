package hr.webshop.handler;

import hr.webshop.entity.AppUser;
import hr.webshop.entity.UserLog;
import hr.webshop.service.AppUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final AppUserService service;

    private final UserDetailsService userDetailsService;

    public CustomAuthenticationSuccessHandler(AppUserService service, UserDetailsService userDetailsService) {
        this.service = service;
        this.userDetailsService = userDetailsService;

    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{

        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        AppUser appUser = service.getUserByEmail(userDetails.getUsername());
        String ipAddress = request.getLocalAddr();

        System.out.println("User: " + appUser.getFirstname() + " " + appUser.getLastname() + " - authenticated: " + authentication.isAuthenticated());

        UserLog log = new UserLog(LocalDate.now(), ipAddress, appUser);

        service.saveUserLog(log);

        request.getSession().setAttribute("user", appUser);

        for (GrantedAuthority ga : userDetails.getAuthorities()){
            if (ga.getAuthority().equals("ROLE_ADMIN")){
                response.sendRedirect("/admin/adminprofile");
            }
            else {
                response.sendRedirect("/profile");
            }
        }

    }
}
