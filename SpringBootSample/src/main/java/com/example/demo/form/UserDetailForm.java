package com.example.demo.form;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserDetailForm {
    private String userId;
    
    @NotBlank
	@Length(min = 4, max = 100)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String password;
    
    @NotBlank
    private String userName;
    private Date birthday;
    private Integer age;
    private Integer gender;
}