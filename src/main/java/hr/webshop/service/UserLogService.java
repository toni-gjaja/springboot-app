package hr.webshop.service;

import hr.webshop.entity.UserLog;
import hr.webshop.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLogService {

    private final LogRepository repo;

    @Autowired
    public UserLogService(LogRepository repo) {
        this.repo = repo;
    }

    public List<UserLog> getAllLogs(){ return repo.findAll(); }
}
