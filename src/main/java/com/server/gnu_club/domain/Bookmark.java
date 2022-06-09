package com.server.gnu_club.domain;

import com.server.gnu_club.domain.user.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity @Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Bookmark {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "bookmark_pk")
    private Long pk;

    @NonNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_pk")
    private Member member;

    @NonNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "club_pk")
    private Club club;
}
