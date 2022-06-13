package com.mymicro.controllers;


import com.mymicro.persist.User;
import com.mymicro.userService.UserService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Bulkhead(name="userService")
    @PostMapping("/save")
    public User saveUser(@Valid  @RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @GetMapping("/{id}")
    @RateLimiter(name="userService")
    public User getUserById(@PathVariable String id){
        return userService.findUser(id);
    }

    @CircuitBreaker(name="userService", fallbackMethod="someFallBackMethodThatAcceptsThrowable")
    @PutMapping("/{id}")
    public User update(@Valid @PathVariable String id, @RequestBody User user) {
       return userService.updateUser(id, user);
    }

    @Retry(name="userService", fallbackMethod = "someMethodNameHereThatAcceptThrowable")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id){
        userService.deleteUser(id);
    }

}
