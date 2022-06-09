package com.server.gnu_club.domain.user;

import com.server.gnu_club.domain.Bookmark;
import com.server.gnu_club.domain.CheckIn;
import com.server.gnu_club.domain.JoinedClub;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity @Getter
@NoArgsConstructor(access = PROTECTED)
public class Member extends UserBase {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_pk")
    private Long pk;

    @OneToMany(mappedBy = "member", cascade = REMOVE, orphanRemoval = true)
    private final Set<JoinedClub> joinedClubs = new HashSet<>();

    @OneToMany(mappedBy = "member", cascade = REMOVE, orphanRemoval = true)
    private final Set<Bookmark> bookmarks = new HashSet<>();

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY, cascade = REMOVE, orphanRemoval = true)
    private CheckIn checkIn;

    private Integer checkInClubId;

    public Member(String signInId, String password) {
        this.signInId = signInId;
        this.password = password;
    }

    public void checkOut() {
        this.checkIn = null;
    }
}
