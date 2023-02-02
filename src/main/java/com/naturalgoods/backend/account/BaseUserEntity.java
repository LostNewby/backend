package com.naturalgoods.backend.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@MappedSuperclass
public class BaseUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String email;

    @JsonIgnore
    @Column
    private String password;

    @Column(name = "temp_pwd_expire_date")
    private Date tempPwdExpireDte;

    @Column
    private String token;

    @Column(name = "created_date")
    private Date createdDate = new Date();

    @Column(name = "last_auth_date")
    private Date lastAuthDate;

    @Column(name = "is_temp")
    private boolean isTemp = false;
}
