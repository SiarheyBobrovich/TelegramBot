package by.it_academy.user.services;

import by.it_academy.user.dao.api.IRoleDao;
import by.it_academy.user.dao.api.IUserDao;
import by.it_academy.user.dao.entity.User;
import by.it_academy.user.dto.request.UserRegistrationDto;
import by.it_academy.user.dto.request.UserUpdateDto;
import by.it_academy.user.dto.response.ResponseUser;
import by.it_academy.user.services.api.IUserPersonalService;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserPersonalService implements IUserPersonalService {

    private final ConversionService conversionService;
    private final IUserDao userDao;
    private final IRoleDao roleDao;

    public UserPersonalService(ConversionService conversionService,
                               IUserDao userDao,
                               IRoleDao roleDao) {
        this.conversionService = conversionService;
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public void save(UserRegistrationDto newUser) {
        if (userDao.existsByChatId(newUser.getChatId())) {
            throw new EntityExistsException("Пользователь уже существует");
        }

        User user = conversionService.convert(newUser, User.class);

        if (user == null) {
            throw new NotYetImplementedException("Конвертер не реализован");
        }

        setAuthoritiesFromDao(user);

        userDao.save(user);
    }

    @Override
    @Transactional
    public void update(UserUpdateDto updatedUser, LocalDateTime dtUpdate) {
        User user = userDao.findByChatId(updatedUser.getChatId()).
                orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        if (!user.getDtUpdate().equals(dtUpdate)) {
            throw new OptimisticLockException("Кто-то уже обновил пользователя");
        }

        User upUser = conversionService.convert(updatedUser, User.class);

        upUser.setUuid(user.getUuid());
        upUser.setAuthorities(user.getAuthorities());
        upUser.setDtCreate(user.getDtCreate());
        upUser.setDtUpdate(dtUpdate);

        userDao.save(upUser);
    }

    @Override
    public User getByChatId(long chatId) {
        return userDao.findByChatId(chatId).orElseThrow(EntityNotFoundException::new);
    }

    private void setAuthoritiesFromDao(User user) {
        user.setAuthorities(
                user.getAuthorities()
                        .stream()
                        .map(x -> roleDao.findByAuthority(x.getAuthority()).orElseThrow(() -> new NotYetImplementedException("Роль не реализована")))
                        .collect(Collectors.toSet())
        );
    }
}
