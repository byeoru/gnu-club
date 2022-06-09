package com.server.gnu_club.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity @Getter
@NoArgsConstructor(access = PROTECTED)
public class Manager extends UserBase {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "manager_id")
    private Long id;

    public Manager(String signInId, String password) {
        this.signInId = signInId;
        this.password = password;
    }
}
