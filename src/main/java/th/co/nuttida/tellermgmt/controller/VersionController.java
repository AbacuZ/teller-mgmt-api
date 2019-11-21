package th.co.nuttida.tellermgmt.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import th.co.nuttida.tellermgmt.domain.VersionTeller;
import th.co.nuttida.tellermgmt.service.VersionService;

@RestController
@RequestMapping("/api/v1/tellermgmt/version")
@Api(value = "Version Teller")
public class VersionController {
    
    @Autowired
    private VersionService versionService;

    @GetMapping
    @ApiOperation(value = "Get all version teller", notes = "")
    public ResponseEntity<List<VersionTeller>> getAllTypeTeller() {
        return new ResponseEntity<>(versionService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get version teller by id", notes = "")
    public VersionTeller getTypeTellerById(
            @ApiParam(value = "A version teller id", required = true) 
            @PathVariable int id) {
        return versionService.findById(id);
    }
}
