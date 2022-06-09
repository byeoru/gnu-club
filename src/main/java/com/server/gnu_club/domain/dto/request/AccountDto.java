package com.server.gnu_club.domain.dto.request;

import lombok.*;

import static lombok.AccessLevel.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class AccountDto {

    private String signInId;
    private String password;
}
