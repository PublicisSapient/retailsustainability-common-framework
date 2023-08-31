package com.publicis.sapient.p2p.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PublicUserDto {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneno;

    private String description;

    private String location;

    private String profileImage;

    private List<String> socialUrls = new ArrayList<>();

    private Date createdTime;

}
