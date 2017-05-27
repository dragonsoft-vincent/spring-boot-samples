package netgloo.domain;

import javax.transaction.Transactional;

/**
 * Repository for the entity Person.
 * 
 * @see netgloo.domain.UserBaseRepository
 */
@Transactional
public interface PersonRepository extends UserBaseRepository<Person> { }
