package com.server.gnu_club.domain.dto.response;

import com.server.gnu_club.domain.Bookmark;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class BookmarkDto {

    private Long clubPk;
    private Long bookmarkPk;
    private String bookmarkName;

    public BookmarkDto(Bookmark bookmark) {
        this.clubPk = bookmark.getClub().getPk();
        this.bookmarkPk = bookmark.getPk();
        this.bookmarkName = bookmark.getClub().getClubName();
    }
}
