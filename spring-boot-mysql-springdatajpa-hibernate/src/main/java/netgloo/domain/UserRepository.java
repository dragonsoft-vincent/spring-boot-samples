package netgloo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * A DAO for the entity User is simply created by extending the CrudRepository
 * interface provided by spring. The following methods are some of the ones
 * available from such interface: save, delete, deleteAll, findOne and findAll.
 * The magic is that such methods must not be implemented, and moreover it is
 * possible create new query methods working only by defining their signature!
 *
 * @author netgloo
 */
public interface UserRepository extends JpaRepository<User, String> {

  /**
   * Return the user having the passed email or null if no user is found.
   *
   * @param email the user email.
   */
  User findByEmail(String email);

  /**
   * Return the users having the name by pages.
   *
   * @param name
   * @param pageable
   * @return
   */
  Page<User> findByName(String name, Pageable pageable);

  /**
   * Return the oldest user
   *
   * @return
   */
  User findTopByOrderByAgeDesc();


  /**
   * JPQL Sample
   *
   * @param name
   * @return
   */
  @Query("select u from User u where u.name in ?1")
  User findByName(List<String> name);

  /**
   * Modifying Sample
   *
   * @param email
   * @param name
   * @return record count that successfully updated
   */
  @Modifying
  @Query("update User u set u.email = ?1 where u.name = ?2")
  int modifyEmailByName(String email, String name);
}
