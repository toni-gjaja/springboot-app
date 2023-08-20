package hr.webshop.repository;

import hr.webshop.entity.AppUser;
import hr.webshop.entity.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<UserLog, Long> {
}
