package com.caf.tstredis;

import java.util.List;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    //private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private DealerService dealerService;

    public UserController() {

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getAllUser() {
        return dealerService.getAllUsers();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable String userId) {
        return dealerService.getUser(userId);
    }

    @PutMapping("/update")
    public User updatePersonByID(@RequestBody User user) {
        dealerService.updatePersonByID(user);
        return user;
    }

    @DeleteMapping("/{userId}")
    public void deleteUserByID(@PathVariable Long userId) {
        dealerService.deleteUserByID(userId);
    }
}
