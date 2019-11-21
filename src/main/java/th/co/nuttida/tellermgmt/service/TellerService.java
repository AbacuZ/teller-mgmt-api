package th.co.nuttida.tellermgmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import th.co.nuttida.tellermgmt.dao.jpa.TellerPagingRepository;

import th.co.nuttida.tellermgmt.dao.jpa.TellerRepository;
import th.co.nuttida.tellermgmt.domain.Teller;
import th.co.nuttida.tellermgmt.domain.TellerSearchPaging;

@Service
@Transactional
public class TellerService {

    @Autowired
    private TellerRepository tellerRepository;
    
    @Autowired
    private TellerPagingRepository tellerPagingRepository;

    public List<Teller> findAll() {
        return tellerRepository.findAll();
    }

    public Teller findTellerById(int id) {
        Teller found = tellerRepository.findById(id);
        return found;
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

}
