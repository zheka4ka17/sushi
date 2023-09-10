package ru.vitstep.sushi.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Username should be not empty")
    private String username;
    @NotEmpty(message = "Name should be not empty")
    private String name;
    @NotEmpty(message = "Surname should be not empty")
    private String surname;
    @NotEmpty(message = "Password should be not empty")
    private String password;
    @NotEmpty(message = "Email should be not empty")
    @Email
    private String email;
    @Min(value=100000000, message = "Enter correct number")
    @Max(value=999999999, message = "Enter correct number")
    private int phoneNumber;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", name = "role_id")
    private Role role;


}