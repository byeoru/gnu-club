package com.server.gnu_club.domain.dto.response;

import com.server.gnu_club.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class CommentResponseDto {

    private Long commentPk;
    private Long memberPk;
    private String userId;
    private String comment;
    private String userProfileImgUrl;

    public CommentResponseDto(Comment comment) {
        this.commentPk = comment.getPk();
        this.memberPk = comment.getMember().getPk();
        this.userId = comment.getMember().getSignInId();
        this.comment = comment.getText();
        this.userProfileImgUrl = comment.getMember().getProfileImgUrl();
    }
}
