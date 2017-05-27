package netgloo.domain;

import javax.transaction.Transactional;

/**
 * Repository for the entity Company.
 * 
 * @see netgloo.domain.UserBaseRepository
 */
@Transactional
public interface CompanyRepository extends UserBaseRepository<Company> { }
