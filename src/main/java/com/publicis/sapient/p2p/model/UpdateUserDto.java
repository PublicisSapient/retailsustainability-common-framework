package com.publicis.sapient.p2p.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserDto {

    private String firstName;

    private String lastName;

    private String phoneno;

    private String description;

    private String location;

    private String profileImage;

    private List<String> socialUrls = new ArrayList<>();

}

