package com.server.gnu_club.domain.BulletinBoard;

import com.server.gnu_club.domain.Club;
import com.server.gnu_club.domain.Comment;
import lombok.*;

import javax.persistence.*;

import java.time.ZonedDateTime;

import static javax.persistence.FetchType.LAZY;

@Getter
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseForm {

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "club_pk")
    protected Club club;

    protected ZonedDateTime postingTime;

    protected String title;
    protected String content;
    protected String imgUrl;
    protected int likeCount;

    public BaseForm(Club club, String title, String content, String imgUrl) {
        this.club = club;
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
    }

    public void plusCount() {
        likeCount++;
    }

    public void minusCount() {
        likeCount--;
    }

    @PrePersist
    private void registerTime() {
        this.postingTime = ZonedDateTime.now();
    }
}
