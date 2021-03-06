package ru.bureau.fal.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bureau.fal.model.User;
import ru.bureau.fal.repository.UserRepo;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service("UserService")
public class UserService {

    private static final Logger log = getLogger(UserService.class);

    @Autowired
    private UserRepo userRepo;

    public User createOrUpdate(User user) {
        if (user.isNew()) {
            log.info("Creating " + user);
        } else log.info("Updating " + user);
        return userRepo.save(user);
    }

    public User getById(Integer id) {
        return userRepo.findById(id).get();
    }

    public List<User> getAll() {
        return (List<User>) userRepo.findAll();
    }
}