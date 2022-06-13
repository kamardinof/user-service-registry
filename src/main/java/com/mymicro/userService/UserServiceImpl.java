package com.mymicro.userService;

import com.mymicro.exceptions.UserExistsException;
import com.mymicro.exceptions.UserNotFoundException;
import com.mymicro.persist.User;
import com.mymicro.persist.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user){
        List<User> listOfUsersWithSameEmail = userRepository.findAllUsersByEmail(user.getEmail());
        if (listOfUsersWithSameEmail.size() != 0){
            throw new UserExistsException();
        }
       return userRepository.save(user);
    }

    @Override
    public User findUser(String id){
        User user = userRepository.findUserById(id);
        if (user == null){
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public User updateUser(String id, User user){
        User queriedUser = userRepository.findUserById(id);
        if (queriedUser == null){
            throw new UserNotFoundException();
        }
       return userRepository.update(id, user);
    }

    @Override
    public void deleteUser(String userId){
       User user =  userRepository.findUserById(userId);
       if (user == null){
           throw new UserNotFoundException();
       }
        userRepository.delete(user);
    }



}
