package com.publicis.sapient.p2p.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto implements Serializable {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneno;
    private String profileImage;
    private String description;
    private String location;
    private List<String> socialUrls = new ArrayList<>();
    
}
