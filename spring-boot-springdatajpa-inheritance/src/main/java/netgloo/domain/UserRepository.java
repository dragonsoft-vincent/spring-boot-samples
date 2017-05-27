package netgloo.domain;

import javax.transaction.Transactional;

/**
 * Repository for the entity User.
 * 
 * @see netgloo.domain.UserBaseRepository
 */
@Transactional
public interface UserRepository extends UserBaseRepository<User> { }
