package netgloo.service.Impl;

import com.google.common.collect.Lists;
import netgloo.domain.User;
import netgloo.domain.UserRepository;
import netgloo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author vincentchen
 * @date 17/5/26.
 */
@Service
public class UserServiceImp implements UserService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public String getUserIdByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            log.warn("User not found by email=[{}]", email);
            return null;
        } else {
            return user.getId();
        }
    }

    @Override
    @Transactional
    public List<User> getUsersByName(String name) {
        Page<User> page = userRepository.findByName(name, new PageRequest(0, 10, new Sort(Sort.Direction.ASC, "id")));
        return page.getContent();
    }

    @Override
    @Transactional
    public User getUserByName(String name) {
        return userRepository.findByName(Lists.newArrayList(name));
    }

    @Override
    @Transactional
    public String getOldestUser() {
        return userRepository.findTopByOrderByAgeDesc().getName();
    }

    @Override
    @Transactional
    public int updateEmailByName(String email, String name) {
        return userRepository.modifyEmailByName(email, name);
    }
}
