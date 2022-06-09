package com.server.gnu_club.domain;

import com.server.gnu_club.domain.BulletinBoard.Notice;
import com.server.gnu_club.domain.BulletinBoard.Timeline;
import com.server.gnu_club.domain.user.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity @Getter
@NoArgsConstructor(access = PROTECTED)
public class Comment {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "comment_pk")
    private Long pk;


    private String text;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_pk")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "notice_pk")
    private Notice notice;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "timeline_pk")
    private Timeline timeline;

    public Comment(String text, Member member, Timeline timeline) {
        this.text = text;
        this.member = member;
        this.timeline = timeline;
    }

    public Comment(String text, Member member, Notice notice) {
        this.text = text;
        this.member = member;
        this.notice = notice;
    }
}
