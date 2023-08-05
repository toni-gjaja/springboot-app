package hr.webshop.service;

import hr.webshop.entity.AppUser;
import hr.webshop.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    private final AppUserRepository repo;

    @Autowired
    public AppUserService(AppUserRepository repo) {
        this.repo = repo;
    }

    public AppUser saveAppUser(AppUser appUser) {
        return repo.save(appUser);
    }


}
