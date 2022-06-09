package com.server.gnu_club.domain.dto.response;

import com.server.gnu_club.domain.BulletinBoard.Timeline;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class TimelinePreviewDto {

    private Long timelinePk;
    private String title;
    private String imageUrl;

    public TimelinePreviewDto(Timeline timeline) {
        this.timelinePk = timeline.getPk();
        this.title = timeline.getTitle();
        this.imageUrl = timeline.getImgUrl();
    }
}
