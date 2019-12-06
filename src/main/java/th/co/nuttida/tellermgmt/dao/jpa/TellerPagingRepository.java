package th.co.nuttida.tellermgmt.dao.jpa;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import th.co.nuttida.tellermgmt.domain.Teller;

@Repository
public interface TellerPagingRepository extends PagingAndSortingRepository<Teller, Integer>, JpaSpecificationExecutor<Teller> {
    
}
