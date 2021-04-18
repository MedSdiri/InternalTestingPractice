package com.pojo;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


@Getter @Setter
@ToString
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Spartan {
    private String name;
    private String gender;
    private long phone;


}
