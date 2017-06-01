package netgloo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author vincentchen
 * @date 17/5/29.
 */
@Entity
@Table(name = "tb_hobby")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hobby extends UUIDEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    private String name;

    @NotNull
    private int cost;
}
