package by.it_academy.user;

import by.it_academy.user.dao.api.IRoleDao;
import by.it_academy.user.dao.api.IUserDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories(
        basePackageClasses = {
                IUserDao.class, IRoleDao.class
        }
)
public class UserApplicationMain {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(UserApplicationMain.class, args);
    }
}