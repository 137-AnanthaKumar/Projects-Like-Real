package com.example.irctc.dto.request;


import javax.validation.constraints.*;

import lombok.Data;

@Data
public class SignupRequest {
  @NotBlank
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;
 

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;
  private String authorno;
  private int pincode;
  private String nation;
  private String state;
  private String area;
  private String district;
  private String streetName;
  private int age;
  private String fullName;
  private String doorNo;
  private String mobileNo;

}
