package com.challenge.endpoints;

import com.challenge.endpoints.advice.ResourceNotFoundException;
import com.challenge.entity.Company;
import com.challenge.service.impl.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.companyService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company")), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List <Company>> findAll(@RequestParam(required = false) Long accelerationId,
                                                  @RequestParam(required = false) Long userId) {
        if(accelerationId != null){
            return new ResponseEntity<>(this.companyService.findByAccelerationId(accelerationId), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(this.companyService.findByUserId(userId),HttpStatus.OK);
        }
    }

}
