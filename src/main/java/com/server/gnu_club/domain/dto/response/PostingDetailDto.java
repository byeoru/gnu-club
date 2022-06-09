package com.server.gnu_club.domain.dto.response;

import com.server.gnu_club.domain.BulletinBoard.Notice;
import com.server.gnu_club.domain.BulletinBoard.Timeline;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PROTECTED;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class PostingDetailDto {

    private String title;
    private String postingTime;
    private String imageUrl;
    private String content;
    private int likeCount;
    private Boolean bLike;
    private List<CommentResponseDto> comments;

    public PostingDetailDto(Notice notice, Boolean bLike) {
        this.title = notice.getTitle();
        this.postingTime = notice.getPostingTime().format(DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss"));
        this.imageUrl = notice.getImgUrl();
        this.content = notice.getContent();
        this.likeCount = notice.getLikeCount();
        this.bLike = bLike;
        this.comments = notice.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }

    public PostingDetailDto(Timeline timeline, Boolean bLike) {
        this.title = timeline.getTitle();
        this.postingTime = timeline.getPostingTime().format(DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss"));
        this.imageUrl = timeline.getImgUrl();
        this.content = timeline.getContent();
        this.likeCount = timeline.getLikeCount();
        this.bLike = bLike;
        this.comments = timeline.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }
}