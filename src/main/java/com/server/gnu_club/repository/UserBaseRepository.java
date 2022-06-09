package com.server.gnu_club.repository;

import com.server.gnu_club.domain.user.UserBase;

public interface UserBaseRepository<T extends UserBase> {

    void save(T user);
    T findOne(Long userId);
    T findById(String signInId);
}
