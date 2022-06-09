package com.server.gnu_club.domain;

import com.server.gnu_club.domain.user.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity @Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class CheckIn {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_in_pk")
    private Long pk;

    @NonNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_pk")
    private Member member;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_pk")
    private Club club;
}
