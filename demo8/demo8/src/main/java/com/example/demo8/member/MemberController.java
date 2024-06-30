package com.example.demo8.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="/v1/members")
public class MemberController {

    @PostMapping
    public ResponseEntity postMember(@RequestBody MemberPostDto memberPostDto){
        return new ResponseEntity<>(memberPostDto,HttpStatus.CREATED);


    }
}
