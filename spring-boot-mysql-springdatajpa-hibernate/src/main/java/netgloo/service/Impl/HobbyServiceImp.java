package netgloo.service.Impl;

import netgloo.domain.HobbyRepository;
import netgloo.service.HobbyService;
import netgloo.service.vo.UserCostSummaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 *
 * @author vincentchen
 * @date 17/6/1.
 */
@Service
public class HobbyServiceImp implements HobbyService {

    @Autowired
    HobbyRepository hobbyRepository;

    @Override
    @Transactional
    public Page<UserCostSummaryVO> getAvgHobbyCostByAge(int age, Pageable pageable) {
        return hobbyRepository.getCostSummaryByAge(age, pageable);
    }
}
