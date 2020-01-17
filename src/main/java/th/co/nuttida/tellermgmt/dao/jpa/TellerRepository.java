package th.co.nuttida.tellermgmt.dao.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import th.co.nuttida.tellermgmt.domain.Teller;

@Repository
public interface TellerRepository extends JpaRepository<Teller, Long>, JpaSpecificationExecutor<Teller> {

//	@Query("SELECT * FROM teller")
	List<Teller> findAll();

	@Query(value = "SELECT * FROM teller u WHERE u.teller_id = :teller_id", nativeQuery = true)
	Teller findById(@Param("teller_id") int tellerId);
	
	@Query(value = "SELECT * FROM teller u WHERE u.teller_no = :teller_no", nativeQuery = true)
	Teller findTellerNo(@Param("teller_no") String tellerNo);
        
        @Query(value = "SELECT *, (6371 * acos( cos( radians(:lat) ) * cos( radians( latitude ) )" +
                " * cos( radians( longitude ) - radians(:lng) ) + sin( radians(:lat) )" +
                " * sin( radians( latitude ) ) ) ) AS distance FROM teller HAVING" +
                " distance < 5 ORDER BY distance asc;", nativeQuery = true)
        List<Teller> findNearestLocation(@Param("lat") String lat, @Param("lng") String lng);
        
        @Query(value = "SELECT *, (6371 * acos( cos( radians(:lat) ) * cos( radians( latitude ) )" +
                " * cos( radians( longitude ) - radians(:lng) ) + sin( radians(:lat) )" +
                " * sin( radians( latitude ) ) ) ) AS distance FROM teller HAVING" +
                " distance < 5 ORDER BY distance asc;", nativeQuery = true)
        List<Object> findNearestLocationExcel(@Param("lat") String lat, @Param("lng") String lng);
}
