package netgloo.controllers;

import netgloo.domain.User;
import netgloo.domain.UserRepository;
import netgloo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * A class to test interactions with the MySQL database using the UserRepository class.
 *
 * @author netgloo
 */
@Controller
public class UserController {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  /**
   * /create  --> Create a new user and save it in the database.
   * 
   * @param email User's email
   * @param name User's name
   * @return A string describing if the user is succesfully created or not.
   */
  @RequestMapping("/create")
  @ResponseBody
  public String create(String email, String name, int age) {
    User user = null;
    try {
      user = new User(email, name, age);
      userRepository.save(user);
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "User succesfully created! (id = " + user.getId() + ")";
  }
  
  /**
   * /delete  --> Delete the user having the passed id.
   * 
   * @param id The id of the user to delete
   * @return A string describing if the user is succesfully deleted or not.
   */
  @RequestMapping("/delete")
  @ResponseBody
  public String delete(String id) {
    try {
      User user = new User(id);
      userRepository.delete(user);
    }
    catch (Exception ex) {
      return "Error deleting the user: " + ex.toString();
    }
    return "User succesfully deleted!";
  }
  
  /**
   * /get single user by email  --> Return the id for the user having the passed email.
   * 
   * @param email The email to search in the database.
   * @return The user id or a message error if the user is not found.
   */
  @RequestMapping("/findByEmail")
  @ResponseBody
  public String getByEmail(String email) {
    return "The user id is: " + userService.getUserIdByEmail(email);
  }

  /**
   * /get user collection by name  --> Return the id for the user having the passed name.
   *
   * @param name The name to search in the database.
   * @return The user id or a message error if the user is not found.
   */
  @RequestMapping("/findUsers")
  @ResponseBody
  public String findUsers(String name) {
    log.info("findUsers by name={}", name);
    List<User> users = userService.getUsersByName(name);
    for (User single : users) {
      log.info("find user id {}" + single.getId());
    }
    return "find user count is " + users.size();
  }

  /**
   * /get single user by name  --> Return the id for the user having the passed name.
   *
   * @param name The name to search in the database.
   * @return The user id or a message error if the user is not found.
   */
  @RequestMapping("/findUser")
  @ResponseBody
  public String findUser(String name) {
    log.info("find user by name={}", name);
    User user = userService.getUserByName(name);
    return "find user name is " + user.getName();
  }

  /**
   * find the oldest user
   *
   * @return
   */
  @RequestMapping("/findOldestUser")
  @ResponseBody
  public String getOldestUser() {
    String userName = userService.getOldestUser();
    return "Oldest user is " + userName;
  }

  /**
   *
   * @param email
   * @param name
   * @return
   */
  @RequestMapping("/updateEmail")
  @ResponseBody
  public String updateEmail(String email, String name) {
    int count = userService.updateEmailByName(email, name);
    return "successfully update user count " + count;
  }

  /**
   * /update  --> Update the email and the name for the user in the database 
   * having the passed id.
   *
   * @param id The id for the user to update.
   * @param email The new email.
   * @param name The new name.
   * @return A string describing if the user is succesfully updated or not.
   */
  @RequestMapping("/update")
  @ResponseBody
  public String updateUser(long id, String email, String name) {
    try {
      User user = userRepository.findOne(id);
      user.setEmail(email);
      user.setName(name);
      userRepository.save(user);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User succesfully updated!";
  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService userService;
  
} // class UserController
