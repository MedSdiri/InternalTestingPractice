package com.pojo;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpartanGet {
    private int id;
    private String name;
    private String gender;
    private long phone;


}
