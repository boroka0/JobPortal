package com.boroka.jobsearchingapp.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "Please enter a valid name (should be under 100 characters)")
    @Size(min=2, max=100)
    private String name;
    @NotBlank(message = "Please enter a valid email")
    @Email
    @Column(unique = true)
    private String email;
}
