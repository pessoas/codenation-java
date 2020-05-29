package com.challenge.endpoints;

import com.challenge.dto.CandidateDTO;
import com.challenge.entity.Candidate;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CandidateMapper mapper;

    @GetMapping("/{userId}/{companyId}/{accelerationId}")
    public ResponseEntity<CandidateDTO> findById(@PathVariable("userId") Long userId,
                                                 @PathVariable("companyId") Long companyId,
                                                 @PathVariable("accelerationId") Long accelerationId) {
        return new ResponseEntity<>(mapper.map(candidateService
                .findById(userId, companyId, accelerationId)
                .orElse(new Candidate())), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> findAll(@RequestParam(required = false) Long companyId,
                                                      @RequestParam(required = false) Long accelerationId) {
        if(companyId != null){
            return new ResponseEntity<>(mapper.map(candidateService.findByCompanyId(companyId)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(mapper.map(candidateService.findByAccelerationId(accelerationId)), HttpStatus.OK);
        }
    }
}
