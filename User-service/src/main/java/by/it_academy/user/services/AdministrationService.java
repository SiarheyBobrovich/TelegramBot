package by.it_academy.user.services;

import by.it_academy.user.dao.api.IUserDao;
import by.it_academy.user.services.api.IAdministrationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AdministrationService implements IAdministrationService {

    private final IUserDao userDao;

    public AdministrationService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Long getCountUsers() {
        return userDao.count();
    }
}
