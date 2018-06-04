package com.caf.tstredis;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class DealerService implements InitializingBean {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    @Autowired
    public DealerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(value = "all-users", key="#root.targetClass.name + '.' + #root.method.name")
    public List<User> getAllUsers() {
        LOG.info("############ Getting all users.");
        //
        List<User> users = new ArrayList<>();
        User shubham = new User("Shubham", 2000);
        users.add(shubham);
        User pankaj = new User("Pankaj", 29000);
        users.add(pankaj);
        User lewis = new User("Lewis", 550);
        users.add(lewis);
        //
        return users; //userRepository.findAll();
    }

    @Cacheable(value = "users", key = "#userId")
    public User getUser(@PathVariable String userId) {
        LOG.info("Getting user with ID {}.", userId);
        return userRepository.findOne(Long.valueOf(userId));
    }
    
    @Cacheable(value = "users", key = "#userId")
    public User getTheUser(@PathVariable String userId) {
        LOG.info("Getting user with ID {}.", userId);
        return userRepository.findOne(Long.valueOf(userId));
    }

    @CachePut(value = "users", key = "#user.id")
    public User updatePersonByID(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @CacheEvict(value = "users", allEntries = true)
    public void deleteUserByID(@PathVariable Long userId) {
        LOG.info("deleting person with id {}", userId);
        userRepository.delete(userId);
    }

    public void populatingDB() {

        LOG.info("Saving users. Current user count is {}.", userRepository.count());
        User shubham = new User("Shubham", 2000);
        User pankaj = new User("Pankaj", 29000);
        User lewis = new User("Lewis", 550);

        userRepository.save(shubham);
        userRepository.save(pankaj);
        userRepository.save(lewis);
        LOG.info("Done saving users. Data: {}.", userRepository.findAll());
    }
/*
    @PostConstruct
    public void init() {
        //
        populatingDB();        
        //
        getAllUsers();
        LOG.info("############ Inside init method");
    }

    @PreDestroy
    public void destroy() {
        LOG.info("############ Inside destroy method");
    }
*/
    @Override
    public void afterPropertiesSet() throws Exception {
        populatingDB();        
        //
        getAllUsers();
        
    }
}
