package netgloo.web.tasks;

import netgloo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户相关定时任务
 *
 * @author vincentchen
 * @date 17/6/1.
 */
@Component
public class UserTask {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    /**
     * @Scheduled(fixedRate = 5000) 上一次开始执行时间点之后5秒再执行
     * @Scheduled(fixedDelay = 5000) 上一次执行完毕时间点之后5秒再执行
     * @Scheduled(initialDelay=1000, fixedRate=5000) ：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
     */
    //@Scheduled(fixedRate = 5000)
    public void getOldestUser() {
        logger.info("the oldest user is {}", userService.getOldestUser());
    }
}
