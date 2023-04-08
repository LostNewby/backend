package com.naturalgoods.backend.account;

import com.naturalgoods.backend.auth.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseUserEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private boolean active;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "phone_number")
    private String phoneNumber;
}
