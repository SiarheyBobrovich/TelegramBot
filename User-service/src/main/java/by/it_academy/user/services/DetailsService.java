package by.it_academy.user.services;

import by.it_academy.user.dao.api.IUserDao;
import by.it_academy.user.dao.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DetailsService implements UserDetailsService {

    private final IUserDao userDao;

    public DetailsService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User currentUser = userDao.findByUsername(username);

        if (currentUser == null || currentUser.getAuthorities().isEmpty()) {
            throw new UsernameNotFoundException("Не авторизирован");
        }

        return currentUser;
    }
}
