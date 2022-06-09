package com.server.gnu_club.domain;

import com.server.gnu_club.domain.user.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity @Getter
@NoArgsConstructor(access = PROTECTED)
public class JoinedClub {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "joined_club_pk")
    private Long pk;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_pk")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "club_pk")
    private Club club;
}
