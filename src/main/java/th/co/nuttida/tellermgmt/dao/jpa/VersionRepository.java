package th.co.nuttida.tellermgmt.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.co.nuttida.tellermgmt.domain.VersionTeller;

@Repository
public interface VersionRepository extends JpaRepository<VersionTeller, Integer> {
    
}
