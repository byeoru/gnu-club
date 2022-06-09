package com.server.gnu_club.domain.dto.response;

import com.server.gnu_club.domain.BulletinBoard.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class NoticePreviewDto {

    private Long noticePk;
    private String title;
    private String imageUrl;

    public NoticePreviewDto(Notice notice) {
        this.noticePk = notice.getPk();
        this.title = notice.getTitle();
        this.imageUrl = notice.getImgUrl();
    }
}
