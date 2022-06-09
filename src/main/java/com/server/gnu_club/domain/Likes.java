package com.server.gnu_club.domain;

import com.server.gnu_club.domain.BulletinBoard.Notice;
import com.server.gnu_club.domain.BulletinBoard.Timeline;
import com.server.gnu_club.domain.user.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity @Getter
@NoArgsConstructor(access = PROTECTED)
public class Likes {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "like_pk")
    private Long pk;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_pk")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "notice_pk")
    private Notice notice;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "timeline_pk")
    private Timeline timeline;

    public Likes(Member member, Notice notice) {
        this.member = member;
        this.notice = notice;
    }

    public Likes(Member member, Timeline timeline) {
        this.member = member;
        this.timeline = timeline;
    }
}
