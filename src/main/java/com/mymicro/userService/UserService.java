package com.mymicro.userService;

import com.mymicro.exceptions.UserExistsException;
import com.mymicro.exceptions.UserNotFoundException;
import com.mymicro.persist.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService{

    User saveUser (User user);

    User findUser(String id);

    User updateUser(String id, User user);

    void deleteUser(String userId);

}
