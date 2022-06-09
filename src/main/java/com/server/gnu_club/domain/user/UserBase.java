package com.server.gnu_club.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

import static lombok.AccessLevel.*;

@Getter
@MappedSuperclass
@NoArgsConstructor(access = PROTECTED)
public class UserBase {

    protected String signInId;
    protected String password;
    protected String profileImgUrl;

    public void putPassword(String newPassword) {
        this.password = newPassword;
    }

    public void putProfileImageUrl(String url) {
        this.profileImgUrl = url;
    }
}
