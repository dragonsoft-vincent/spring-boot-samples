package netgloo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * An entity User composed by three fields (id, email, name).
 * The Entity annotation indicates that this class is a JPA entity.
 * The Table annotation specifies the name for the table in the db.
 *
 * @author netgloo
 */
@Entity
@Table(name = "tb_user")
@Data
@NoArgsConstructor
public class User extends UUIDEntity{
  
  @NotNull
  private String email;
  
  @NotNull
  private String name;

  @NotNull
  private int age;

  public User(String id) {
    super.setId(id);
  }

  public User(String email, String name, int age) {
    this.email = email;
    this.name = name;
    this.age = age;
  }
}
