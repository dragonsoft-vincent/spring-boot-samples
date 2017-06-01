package netgloo.web;

import netgloo.domain.User;
import netgloo.service.HobbyService;
import netgloo.service.UserService;
import netgloo.service.vo.UserCostSummaryVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
   * @return A string describing if the user is successfully created or not.
   */
  @RequestMapping(value = "/create", method = RequestMethod.POST)
  @ResponseBody
  public String create(String email, String name, int age, String[] hobbyNames) {
    User user = userService.createUserWithHobbies(email, name, age, hobbyNames);
    return "User successfully created! (id = " + user.getId() + ")";
  }

  
  /**
   * /get single user by email  --> Return the id for the user having the passed email.
   * 
   * @param email The email to search in the database.
   * @return The user id or a message error if the user is not found.
   */
  @RequestMapping(value = "/findByEmail", method = RequestMethod.GET)
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
  @RequestMapping(value = "/findUsers", method = RequestMethod.GET)
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
  @RequestMapping(value = "/findUser", method = RequestMethod.GET)
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
  @RequestMapping(value = "/findOldestUser", method = RequestMethod.GET)
  @ResponseBody
  public String getOldestUser() {
    String userName = userService.getOldestUser();
    return "Oldest user is " + userName;
  }

  /**
   * find the oldest user
   *
   * @return
   */
  @RequestMapping(value = "/avgCostByAge", method = RequestMethod.GET)
  @ResponseBody
  public String getAvgCostByAge(int age) {
    Page<UserCostSummaryVO> summary = hobbyService.getAvgHobbyCostByAge(age, new PageRequest(0, 10));
    for (UserCostSummaryVO single : summary) {
      log.info(" avg cost: " + single.getAverageCost());
    }
    return "Avg cost";
  }

  /**
   *
   * @param email
   * @param name
   * @return
   */
  @RequestMapping(value = "/updateEmail", method = RequestMethod.POST)
  @ResponseBody
  public String updateEmail(String email, String name) {
    int count = userService.updateEmailByName(email, name);
    return "successfully update user count " + count;
  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------

  @Autowired
  private UserService userService;

  @Autowired
  private HobbyService hobbyService;
  
} // class UserController
