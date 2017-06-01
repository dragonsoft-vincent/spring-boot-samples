package netgloo.service;

import netgloo.domain.User;

import java.util.List;

/**
 *
 * @author vincentchen
 * @date 17/5/26.
 */
public interface UserService {

    String getUserIdByEmail(String email);

    List<User> getUsersByName(String name);

    String getOldestUser();

    User getUserByName(String name);

    int updateEmailByName(String email, String name);

    User createUserWithHobbies(String email, String name, int age, String[] hobbyNames);
}

