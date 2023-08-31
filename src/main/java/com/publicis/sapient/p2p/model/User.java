package com.publicis.sapient.p2p.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Validated
@Valid
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
@Entity
public class User {

    
    @JsonProperty("id")
    private @Id String id;

    @JsonProperty("firstName")
    @NotBlank(message = "First Name is Mandatory")
    @Size(min = 1, message = "First name must be greater then 1")
    private String firstName;

    @JsonProperty("lastName")
    @NotBlank(message = "Last Name is Mandatory")
    @Size(min = 1, message = "Last name must be greater then 1")
    private String lastName;

    @JsonProperty(namespace = "password")
    @NotBlank(message = "Password is Mandatory")
    private String password;

    @JsonProperty("email")
    @NotBlank(message = "Email is Mandatory & Must be Unique")
    @Column(unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Please provide valid mail id")
    private String email;

    @JsonProperty("status")
    private String status;

    @JsonProperty("phoneno")
    @NotBlank(message = "Phone Number is Mandatory")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number is not is valid format")
    private String phoneno;

    private String description;

    private String location;

    private String profileImage;

    private List<String> socialUrls = new ArrayList<>();

    private Date createdTime;
    
}
