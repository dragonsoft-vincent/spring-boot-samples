package netgloo;


import netgloo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author vincentchen
 * @date 17/5/31.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void getOldestUserName() {
        assertEquals("Vincent", userService.getOldestUser());
    }

}
