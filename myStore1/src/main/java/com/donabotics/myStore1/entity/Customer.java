package com.donabotics.myStore1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ToString
@Entity
@Table(name = "customers")
@Configuration
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name",length = 45, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 45)
    private String lastName;
    @Column(name = "email", length = 45, nullable = false)
    private String email;
    @Column(name = "password", length = 15, nullable = false)
    private String password;
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;
    @Column(name = "address", length = 45, nullable = false)
    private String address;

}
