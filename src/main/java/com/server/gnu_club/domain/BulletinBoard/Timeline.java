package com.server.gnu_club.domain.BulletinBoard;

import com.server.gnu_club.domain.Club;
import com.server.gnu_club.domain.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity @Getter
@NoArgsConstructor(access = PROTECTED)
public class Timeline extends BaseForm {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "timeline_pk")
    private Long pk;

    @OneToMany(mappedBy = "timeline", cascade = CascadeType.REMOVE, orphanRemoval = true)
    protected List<Comment> comments;

    public Timeline(Club club, String title, String content, String imgUrl) {
        super(club, title, content, imgUrl);
    }
}
