package com.challenge.endpoints;

import com.challenge.endpoints.advice.ResourceNotFoundException;
import com.challenge.entity.User;
import com.challenge.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User")), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<User>> findAll(@RequestParam(required = false) String accelerationName,
                                              @RequestParam(required = false) Long companyId){
        if (accelerationName != null){
            return new ResponseEntity<>(this.userService.findByAccelerationName(accelerationName), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(this.userService.findByCompanyId(companyId), HttpStatus.OK);
        }
    }


}
