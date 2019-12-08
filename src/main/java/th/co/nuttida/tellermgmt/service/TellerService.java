package th.co.nuttida.tellermgmt.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import th.co.nuttida.tellermgmt.dao.jpa.TellerDetailsRepository;
import th.co.nuttida.tellermgmt.dao.jpa.TellerPagingRepository;

import th.co.nuttida.tellermgmt.dao.jpa.TellerRepository;
import th.co.nuttida.tellermgmt.domain.DataInsertTeller;
import th.co.nuttida.tellermgmt.domain.DataSearchCriteria;
import th.co.nuttida.tellermgmt.domain.Teller;
import th.co.nuttida.tellermgmt.domain.TellerDetails;
import th.co.nuttida.tellermgmt.domain.TellerSearchPaging;

@Service
@Transactional
public class TellerService {

    @Autowired
    private TellerRepository tellerRepository;
    
    @Autowired
    private TellerPagingRepository tellerPagingRepository;
    
    @Autowired
    private TellerDetailsRepository tellerDetailsRepository;

    public List<Teller> findAll() {
        return tellerRepository.findAll();
    }

    public Teller findTellerById(int id) {
        Teller found = tellerRepository.findById(id);
        return found;
    }
    
    public Teller saveTeller(DataInsertTeller data) {
        TellerDetails tellerDetails = tellerDetailsRepository.save(data.getTellerDetails());
        Teller teller = new Teller();
        teller.setTellerNo(data.getTeller().getTellerNo());
        teller.setTellerAddress(data.getTeller().getTellerAddress());
        teller.setBranch(data.getTeller().getBranch());
        teller.setContractNo(data.getTeller().getContractNo());
        teller.setGprsCompany(data.getTeller().getGprsCompany());
        teller.setLatitude(data.getTeller().getLatitude());
        teller.setLongitude(data.getTeller().getLongitude());
        teller.setSerial(data.getTeller().getSerial());
        teller.setTelTellerAddress(data.getTeller().getTelTellerAddress());
        teller.setZoneId(data.getTeller().getZoneId());
        teller.setProvinceId(data.getTeller().getProvinceId());
        teller.setDistrictId(data.getTeller().getDistrictId());
        teller.setTypeAddressId(data.getTeller().getTypeAddressId());
        teller.setVersionTellerId(data.getTeller().getVersionTellerId());
        teller.setTellerDetailsId(tellerDetails.getTellerDetailsId());
        return tellerRepository.save(teller);
    }
    
    public Teller updateTeller(DataInsertTeller data) {
        TellerDetails tellerDetails = tellerDetailsRepository.findById(data.getTellerDetails().getTellerDetailsId());
        tellerDetails.setIndexMasterKey(data.getTellerDetails().getIndexMasterKey());
        tellerDetails.setIpAddress(data.getTellerDetails().getIpAddress());
        tellerDetails.setIpGateway(data.getTellerDetails().getIpGateway());
        tellerDetails.setLocalPort(data.getTellerDetails().getLocalPort());
        tellerDetails.setServicePort(data.getTellerDetails().getServicePort());
        tellerDetailsRepository.save(tellerDetails);
        
        Teller teller = tellerRepository.findById(data.getTeller().getTellerId());
        teller.setTellerNo(data.getTeller().getTellerNo());
        teller.setTellerAddress(data.getTeller().getTellerAddress());
        teller.setBranch(data.getTeller().getBranch());
        teller.setContractNo(data.getTeller().getContractNo());
        teller.setGprsCompany(data.getTeller().getGprsCompany());
        teller.setLatitude(data.getTeller().getLatitude());
        teller.setLongitude(data.getTeller().getLongitude());
        teller.setSerial(data.getTeller().getSerial());
        teller.setTelTellerAddress(data.getTeller().getTelTellerAddress());
        teller.setZoneId(data.getTeller().getZoneId());
        teller.setProvinceId(data.getTeller().getProvinceId());
        teller.setDistrictId(data.getTeller().getDistrictId());
        teller.setTypeAddressId(data.getTeller().getTypeAddressId());
        teller.setVersionTellerId(data.getTeller().getVersionTellerId());
        teller.setTellerDetailsId(tellerDetails.getTellerDetailsId());
        return tellerRepository.save(teller);
    }
    
    public void deleteTeller(int id) {
        Teller teller = tellerRepository.findById(id);
        tellerRepository.delete(teller);
        TellerDetails tellerDetails = tellerDetailsRepository.findById(teller.getTellerDetailsId());
        tellerDetailsRepository.delete(tellerDetails);
    }
    
    @Transactional(readOnly = true)
    public TellerSearchPaging findTeller(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("tellerId").ascending());
        Page<Teller> tellerFound = tellerPagingRepository.findAll(paging);
        if(tellerFound.hasContent()) {
            TellerSearchPaging tellerSearchPaing = new TellerSearchPaging();
            tellerSearchPaing.setTellerResult(tellerFound.getContent());
            tellerSearchPaing.setHasNext(tellerFound.hasNext());
            tellerSearchPaing.setHasPrevious(tellerFound.hasPrevious());
            tellerSearchPaing.setFirst(tellerFound.isFirst());
            tellerSearchPaing.setLast(tellerFound.isLast());
            tellerSearchPaing.setNumber(tellerFound.getNumber());
            tellerSearchPaing.setNumberOfElements(tellerFound.getNumberOfElements());
            tellerSearchPaing.setSize(tellerFound.getSize()); 
            tellerSearchPaing.setTotalPages(tellerFound.getTotalPages());
            tellerSearchPaing.setRecordsTotal(tellerFound.getTotalElements());
            tellerSearchPaing.setRecordsFiltered(tellerFound.getTotalElements());
            return tellerSearchPaing;
        } else {
            return new TellerSearchPaging();
        }
    }
    
    @Transactional(readOnly = true)
    public TellerSearchPaging findByTellerNO(String tellerNo, int pageNo, int pageSize) {
        System.out.println("TellerNo ==================================================================> " + tellerNo);
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("tellerId").ascending());
        Page<Teller> tellerFound = tellerPagingRepository.findAll(getTellerNoSpecification(tellerNo), paging);
        if(tellerFound.hasContent()) {
            TellerSearchPaging tellerSearchPaing = new TellerSearchPaging();
            tellerSearchPaing.setTellerResult(tellerFound.getContent());
            tellerSearchPaing.setHasNext(tellerFound.hasNext());
            tellerSearchPaing.setHasPrevious(tellerFound.hasPrevious());
            tellerSearchPaing.setFirst(tellerFound.isFirst());
            tellerSearchPaing.setLast(tellerFound.isLast());
            tellerSearchPaing.setNumber(tellerFound.getNumber());
            tellerSearchPaing.setNumberOfElements(tellerFound.getNumberOfElements());
            tellerSearchPaing.setSize(tellerFound.getSize()); 
            tellerSearchPaing.setTotalPages(tellerFound.getTotalPages());
            tellerSearchPaing.setRecordsTotal(tellerFound.getTotalElements());
            tellerSearchPaing.setRecordsFiltered(tellerFound.getTotalElements());
            return tellerSearchPaing;
        } else {
            return new TellerSearchPaging();
        }
    }
    
    public List<Teller> findCriteria(DataSearchCriteria data) {
        return tellerRepository.findAll(getTellerSpecification(data));
    }
    
    public List<Teller> findNearest(String lat, String lng) {
        System.out.println("lat " + lat);
        System.out.println("lng " + lng);
        return tellerRepository.findNearestLocation(lat, lng);
    }
    
    private Specification<Teller> getTellerNoSpecification(String tellerNo) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
                        
            if (!StringUtils.isEmpty(tellerNo)) {
                predicates.add(cb.like(root.get("tellerNo"), "%" + tellerNo + "%"));
            }
            
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
    
    private Specification<Teller> getTellerSpecification(DataSearchCriteria data) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            if (!StringUtils.isEmpty(data.getTellerAddress())) {
                predicates.add(cb.like(root.get("tellerAddress"), "%" + data.getTellerAddress() + "%"));
            }
                        
            if (!StringUtils.isEmpty(data.getTellerNo())) {
                predicates.add(cb.equal(root.get("tellerNo"), data.getTellerNo()));
            }
            
            if (data.getDistrictId() != 0) {
                predicates.add(cb.equal(root.get("districtId"), data.getDistrictId()));
            }
            
            if (data.getProvinceId() != 0) {
                predicates.add(cb.equal(root.get("provinceId"), data.getProvinceId()));
            }
            
            if (data.getZoneId() != 0) {
                predicates.add(cb.equal(root.get("zoneId"), data.getZoneId()));
            }
            
            if (data.getTypeAddressId() != 0) {
                predicates.add(cb.equal(root.get("typeAddressId"), data.getTypeAddressId()));
            }
            
            if (data.getVersionTellerId() != 0) {
                predicates.add(cb.equal(root.get("versionTellerId"), data.getVersionTellerId()));
            }
            
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
