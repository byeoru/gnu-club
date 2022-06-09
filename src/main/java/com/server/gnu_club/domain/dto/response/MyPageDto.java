package com.server.gnu_club.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class MyPageDto {

    private String signInId;
    private String profileImageUrl;
    private List<JoinedClubDto> joinedClubs;
    private List<BookmarkDto> bookmarks;
}
