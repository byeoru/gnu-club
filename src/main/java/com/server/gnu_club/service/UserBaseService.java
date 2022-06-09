package com.server.gnu_club.service;

import com.server.gnu_club.domain.dto.request.AccountDto;
import com.server.gnu_club.domain.user.UserBase;

public interface UserBaseService<T extends UserBase> {

    Boolean isIdDuplicated(String signInId);
    Long signup(AccountDto accountDto);
    Long signIn(AccountDto accountDto);
    T findUser(Long userId);
    Boolean isCorrectPassword(Long userId, String inputPassword);
    void putPassword(Long userId, String newPassword);
}
