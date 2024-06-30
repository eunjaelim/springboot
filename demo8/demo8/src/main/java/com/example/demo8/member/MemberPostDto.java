package com.example.demo8.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberPostDto {
    private String email;
    private String name;
    private String phone;
}
