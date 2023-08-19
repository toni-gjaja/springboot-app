package hr.webshop.service;

import hr.webshop.entity.AppUser;
import hr.webshop.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserService implements UserDetailsService {

    private final AppUserRepository repo;

    @Autowired
    public AppUserService(AppUserRepository repo) {
        this.repo = repo;
    }

    public void saveAppUser(AppUser appUser) {
        repo.save(appUser);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AppUser user = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (user.isAdmin()){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new User(user.getEmail(), user.getPassword(), authorities);

    }
}
