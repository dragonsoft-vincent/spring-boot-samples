package netgloo.service.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author vincentchen
 * @date 17/5/29.
 */
@Data
@AllArgsConstructor
public class UserCostSummary {

    private String name;

    private Double averageCost;
}
