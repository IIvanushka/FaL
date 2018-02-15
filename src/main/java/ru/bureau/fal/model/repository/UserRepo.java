package ru.bureau.fal.model.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bureau.fal.model.User;

public interface UserRepo extends CrudRepository<User, Integer> {
}
