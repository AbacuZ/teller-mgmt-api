package th.co.nuttida.tellermgmt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import th.co.nuttida.tellermgmt.dao.jpa.BrandTellerRepository;
import th.co.nuttida.tellermgmt.dao.jpa.DistrictRepository;
import th.co.nuttida.tellermgmt.dao.jpa.ProvinceRepository;
import th.co.nuttida.tellermgmt.dao.jpa.TellerDetailsRepository;
import th.co.nuttida.tellermgmt.dao.jpa.TellerPagingRepository;

import th.co.nuttida.tellermgmt.dao.jpa.TellerRepository;
import th.co.nuttida.tellermgmt.dao.jpa.TypeAddressRepository;
import th.co.nuttida.tellermgmt.dao.jpa.TypeTellerRepository;
import th.co.nuttida.tellermgmt.dao.jpa.VersionRepository;
import th.co.nuttida.tellermgmt.dao.jpa.ZoneRepository;
import th.co.nuttida.tellermgmt.domain.BrandTeller;
import th.co.nuttida.tellermgmt.domain.DataInsertTeller;
import th.co.nuttida.tellermgmt.domain.DataSearchCriteria;
import th.co.nuttida.tellermgmt.domain.District;
import th.co.nuttida.tellermgmt.domain.Province;
import th.co.nuttida.tellermgmt.domain.ResultTeller;
import th.co.nuttida.tellermgmt.domain.ResultTellerAndTellerDetails;
import th.co.nuttida.tellermgmt.domain.Teller;
import th.co.nuttida.tellermgmt.domain.TellerDetails;
import th.co.nuttida.tellermgmt.domain.TellerSearchPaging;
import th.co.nuttida.tellermgmt.domain.TypeAddress;
import th.co.nuttida.tellermgmt.domain.TypeTeller;
import th.co.nuttida.tellermgmt.domain.VersionTeller;
import th.co.nuttida.tellermgmt.domain.Zone;
import th.co.nuttida.tellermgmt.jdbc.TellerImpl;

@Service
@Transactional
public class TellerService {

    @Autowired
    private TellerRepository tellerRepository;

    @Autowired
    private TellerPagingRepository tellerPagingRepository;

    @Autowired
    private TellerDetailsRepository tellerDetailsRepository;

    @Autowired
    private BrandTellerRepository brandTellerRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private TypeTellerRepository typeTellerRepository;

    @Autowired
    private TypeAddressRepository typeAddressRepository;

    @Autowired
    private VersionRepository versionRepository;
    
    @Autowired
    private TellerImpl tellerImpl;
    
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
        teller.setTellerDetailsId(tellerDetails.getTellerDetailsId());
        teller.setTypeAddressId(data.getTeller().getTypeAddressId());
        teller.setVersionTellerId(data.getTeller().getVersionTellerId());
        teller.setTypeTellerId(data.getTeller().getTypeTellerId());
        teller.setBrandTellerId(data.getTeller().getBrandTellerId());
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
        teller.setTellerDetailsId(tellerDetails.getTellerDetailsId());
        teller.setTypeAddressId(data.getTeller().getTypeAddressId());
        teller.setVersionTellerId(data.getTeller().getVersionTellerId());
        teller.setTypeTellerId(data.getTeller().getTypeTellerId());
        teller.setBrandTellerId(data.getTeller().getBrandTellerId());
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
        if (tellerFound.hasContent()) {
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
        if (tellerFound.hasContent()) {
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

    public List<ResultTellerAndTellerDetails> findNearest(String lat, String lng) {
        System.out.println("lat " + lat);
        System.out.println("lng " + lng);
        List<ResultTellerAndTellerDetails> tellerList = tellerImpl.findNearestLocationMap(lat, lng);
        return tellerList;
    }

    public List<ResultTeller> exportExcel(DataSearchCriteria data) {
        List<Teller> teller = tellerRepository.findAll(getTellerSpecification(data));
        List<ResultTeller> resultTellerList = new ArrayList<>();

        teller.forEach(item -> {
            ResultTeller resultTeller = new ResultTeller();
            resultTeller.setTellerNo(item.getTellerNo());
            resultTeller.setTelTellerAddress(item.getTellerAddress());
            resultTeller.setTelTellerAddress(item.getTelTellerAddress());
            resultTeller.setLongitude(item.getLongitude());
            resultTeller.setLatitude(item.getLatitude());
            resultTeller.setSerial(item.getSerial());
            resultTeller.setContractNo(item.getContractNo());
            resultTeller.setBranch(item.getBranch());
            resultTeller.setGprsCompany(item.getGprsCompany());

            TellerDetails tellerDetails = tellerDetailsRepository.findById(item.getTellerDetailsId());
            resultTeller.setIndexMasterKey(tellerDetails.getIndexMasterKey());
            resultTeller.setIpAddress(tellerDetails.getIpAddress());
            resultTeller.setIpGateway(tellerDetails.getIpGateway());
            resultTeller.setLocalPort(tellerDetails.getLocalPort());
            resultTeller.setServicePort(tellerDetails.getServicePort());

            BrandTeller brand = brandTellerRepository.findById(item.getBrandTellerId());
            resultTeller.setBrandTeller(brand.getBrandTellerName());

            District district = districtRepository.findById(item.getDistrictId());
            resultTeller.setDistrict(district.getDistrictNameThai());

            Province province = provinceRepository.findById(item.getProvinceId());
            resultTeller.setProvince(province.getProvinceNameThai());

            Zone zone = zoneRepository.findById(item.getZoneId());
            resultTeller.setZone(zone.getZoneName());

            TypeTeller typeTeller = typeTellerRepository.findById(item.getTypeTellerId());
            resultTeller.setTypeTeller(typeTeller.getTypeTellerName());

            TypeAddress typeAddress = typeAddressRepository.findById(item.getTypeAddressId());
            resultTeller.setTypeAddress(typeAddress.getTypeAddressName());

            Optional<VersionTeller> versionTeller = versionRepository.findById(item.getVersionTellerId());
            resultTeller.setVersionTeller(versionTeller.get().getVersionTellerName());

            resultTellerList.add(resultTeller);
        });
        return resultTellerList;
    }

    public List<ResultTeller> exportExcelMap(String lat, String lng) {
        List<Teller> teller = tellerRepository.findNearestLocation(lat, lng);
        List<ResultTeller> resultTellerList = new ArrayList<>();
        ResultTeller resultTeller = new ResultTeller();

        teller.forEach(item -> {
            resultTeller.setTellerNo(item.getTellerNo());
            resultTeller.setTelTellerAddress(item.getTellerAddress());
            resultTeller.setTelTellerAddress(item.getTelTellerAddress());
            resultTeller.setLongitude(item.getLongitude());
            resultTeller.setLatitude(item.getLatitude());
            resultTeller.setSerial(item.getSerial());
            resultTeller.setContractNo(item.getContractNo());
            resultTeller.setBranch(item.getBranch());
            resultTeller.setGprsCompany(item.getGprsCompany());

            TellerDetails tellerDetails = tellerDetailsRepository.findById(item.getTellerDetailsId());
            resultTeller.setIndexMasterKey(tellerDetails.getIndexMasterKey());
            resultTeller.setIpAddress(tellerDetails.getIpAddress());
            resultTeller.setIpGateway(tellerDetails.getIpGateway());
            resultTeller.setLocalPort(tellerDetails.getLocalPort());
            resultTeller.setServicePort(tellerDetails.getServicePort());

            BrandTeller brand = brandTellerRepository.findById(item.getBrandTellerId());
            resultTeller.setBrandTeller(brand.getBrandTellerName());

            District district = districtRepository.findById(item.getDistrictId());
            resultTeller.setDistrict(district.getDistrictNameThai());

            Province province = provinceRepository.findById(item.getProvinceId());
            resultTeller.setProvince(province.getProvinceNameThai());

            Zone zone = zoneRepository.findById(item.getZoneId());
            resultTeller.setZone(zone.getZoneName());

            TypeTeller typeTeller = typeTellerRepository.findById(item.getTypeTellerId());
            resultTeller.setTypeTeller(typeTeller.getTypeTellerName());

            TypeAddress typeAddress = typeAddressRepository.findById(item.getTypeAddressId());
            resultTeller.setTypeAddress(typeAddress.getTypeAddressName());

            Optional<VersionTeller> versionTeller = versionRepository.findById(item.getVersionTellerId());
            resultTeller.setVersionTeller(versionTeller.get().getVersionTellerName());

            resultTellerList.add(resultTeller);
        });
        return resultTellerList;
    }

    private Specification<Teller> getTellerNoSpecification(String tellerNo) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isEmpty(tellerNo)) {
                predicates.add(cb.or(
                        cb.like(root.get("tellerAddress"), "%" + tellerNo + "%"),
                        cb.like(root.get("tellerNo"), "%" + tellerNo + "%")
                ));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    private Specification<Teller> getTellerSpecification(DataSearchCriteria data) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isEmpty(data.getTellerAddress())) {
                predicates.add(cb.or(
                        cb.like(root.get("tellerAddress"), "%" + data.getTellerAddress() + "%"),
                        cb.like(root.get("tellerNo"), "%" + data.getTellerAddress() + "%")
                ));
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

            if (data.getVersionTellerId() != 0) {
                predicates.add(cb.equal(root.get("versionTellerId"), data.getVersionTellerId()));
            }

            if (data.getTypeTellerId() != 0) {
                predicates.add(cb.equal(root.get("typeTellerId"), data.getTypeTellerId()));
            }

            if (data.getBrandTellerId() != 0) {
                predicates.add(cb.equal(root.get("brandTellerId"), data.getBrandTellerId()));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
