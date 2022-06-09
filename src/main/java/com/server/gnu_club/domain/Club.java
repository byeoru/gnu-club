package com.server.gnu_club.domain;

import com.server.gnu_club.domain.Category;
import com.server.gnu_club.domain.user.Manager;
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Club_Type")
public class Club {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "club_pk")
    private Long pk;

    @NonNull
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "manager_pk")
    private Manager manager;

    @NonNull
    private String clubName;
    private int totalMemberCnt;

    private String backgroundImgUrl;
    @NonNull
    private String intro;

    @NonNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_pk")
    private Category category;

    public void putBackgroundImage(String imageUrl) {
        this.backgroundImgUrl = imageUrl;
    }
}
