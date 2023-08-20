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
import java.util.Optional;

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

    public boolean checkExistingEmail(String email){ return repo.existsByEmail(email);}

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<AppUser> appUser = repo.findByEmail(email);

        if (appUser.isPresent()){
            AppUser user = appUser.get();
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
            return new User(user.getEmail(), user.getPassword(), authorities);
        }
        return null;
    }

    public AppUser authenticateUser(String email, String password){

        Optional<AppUser> appUser = repo.findByEmail(email);

        if (appUser.isPresent()){
            AppUser user = appUser.get();
            if (user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
}
