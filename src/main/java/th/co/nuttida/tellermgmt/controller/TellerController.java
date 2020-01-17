package th.co.nuttida.tellermgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import th.co.nuttida.tellermgmt.domain.CurrentLocation;
import th.co.nuttida.tellermgmt.domain.DataInsertTeller;
import th.co.nuttida.tellermgmt.domain.DataSearchCriteria;
import th.co.nuttida.tellermgmt.domain.ResultTeller;
import th.co.nuttida.tellermgmt.domain.ResultTellerAndTellerDetails;
import th.co.nuttida.tellermgmt.domain.Teller;
import th.co.nuttida.tellermgmt.domain.TellerSearchPaging;
import th.co.nuttida.tellermgmt.service.TellerService;

@RestController
@RequestMapping("/api/v1/tellermgmt/teller")
@Api(value = "Teller Management System")
public class TellerController {

    @Autowired
    private TellerService tellerService;

    @PostMapping
    @ApiOperation(value = "Save teller", notes = "")
    public ResponseEntity<Teller> saveTeller(
            @ApiParam(value = "A teller", required = true)
            @RequestBody DataInsertTeller dataInsertTeller) {
        return new ResponseEntity<>(tellerService.saveTeller(dataInsertTeller), HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value = "Update teller", notes = "")
    public ResponseEntity<Teller> updateTeller(
            @ApiParam(value = "A teller", required = true)
            @RequestBody DataInsertTeller dataInsertTeller) {
        return new ResponseEntity<>(tellerService.updateTeller(dataInsertTeller), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete teller", notes = "")
    public ResponseEntity<Teller> deleteTeller(
            @ApiParam(value = "A teller id", required = true)
            @PathVariable int id) {
        tellerService.deleteTeller(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @ApiOperation(value = "Get all teller", notes = "")
    public ResponseEntity<List<Teller>> getAllTeller() {
        return new ResponseEntity<>(tellerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get teller by id", notes = "")
    public Teller getTellerTellerById(
            @ApiParam(value = "A teller details id", required = true)
            @PathVariable int id) {
        return tellerService.findTellerById(id);
    }

    @GetMapping("/search")
    @ApiOperation(value = "Search teller", notes = "")
    public TellerSearchPaging searchTeller(
            @ApiParam(value = "page number", required = false)
            @RequestParam(defaultValue = "0") Integer pageNo,
            @ApiParam(value = "page size", required = false)
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return tellerService.findTeller(pageNo, pageSize);
    }
    
    @PostMapping("/search-map")
    @ApiOperation(value = "Search teller", notes = "")
    public ResponseEntity<List<Teller>> searchMapTeller(
            @ApiParam(value = "Data search criteria", required = false)
            @RequestBody DataSearchCriteria data) {
        return new ResponseEntity<>(tellerService.findCriteria(data), HttpStatus.OK);
    }
    
    @PostMapping("/find-nearest-location")
    @ApiOperation(value = "Search nearest current location", notes = "")
    public ResponseEntity<List<ResultTellerAndTellerDetails>> searchNearestLocationTeller(
            @ApiParam(value = "A latitude, longitude", required = false)
            @RequestBody CurrentLocation data) {
        return new ResponseEntity<>(tellerService.findNearest(data.getLat(), data.getLng()), HttpStatus.OK);
    }
    
    @PostMapping("/search-teller-no")
    @ApiOperation(value = "Search teller", notes = "")
    public ResponseEntity<TellerSearchPaging> searchTellerNo(
            @ApiParam(value = "page number", required = false)
            @RequestParam(defaultValue = "0") Integer pageNo,
            @ApiParam(value = "page size", required = false)
            @RequestParam(defaultValue = "10") Integer pageSize,
            @ApiParam(value = "Data search criteria", required = false)
            @RequestBody String tellerNo) {
        return new ResponseEntity<>(tellerService.findByTellerNO(tellerNo, pageNo, pageSize), HttpStatus.OK);
    }
    
    @PostMapping("/export-excel")
    @ApiOperation(value = "Search teller", notes = "")
    public ResponseEntity<List<ResultTeller>> exportExcel(
            @ApiParam(value = "Data search criteria", required = false)
            @RequestBody DataSearchCriteria data) {
        return new ResponseEntity<>(tellerService.exportExcel(data), HttpStatus.OK);
    }
    
    @PostMapping("/export-excel-map")
    @ApiOperation(value = "Search teller", notes = "")
    public ResponseEntity<List<ResultTeller>> exportExcelMap(
            @ApiParam(value = "Data search criteria", required = false)
            @RequestBody CurrentLocation data) {
        return new ResponseEntity<>(tellerService.exportExcelMap(data.getLat(), data.getLng()), HttpStatus.OK);
    }
}
