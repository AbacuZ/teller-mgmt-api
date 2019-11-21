/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.nuttida.tellermgmt.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.co.nuttida.tellermgmt.dao.jpa.VersionRepository;
import th.co.nuttida.tellermgmt.domain.VersionTeller;

@Service
public class VersionService {

    @Autowired
    private VersionRepository versionRepository;

    public List<VersionTeller> findAll() {
        return versionRepository.findAll();
    }

    public VersionTeller findById(int id) {
        Optional<VersionTeller> found = versionRepository.findById(id);
        return found.get();
    }
}
