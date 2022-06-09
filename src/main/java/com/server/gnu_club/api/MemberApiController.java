package com.server.gnu_club.api;

import com.server.gnu_club.domain.dto.ResultDto;
import com.server.gnu_club.domain.dto.request.AccountDto;
import com.server.gnu_club.domain.dto.request.CommentRequestDto;
import com.server.gnu_club.domain.dto.request.PasswordChangeDto;
import com.server.gnu_club.domain.dto.response.BookmarkDto;
import com.server.gnu_club.domain.dto.response.JoinedClubDto;
import com.server.gnu_club.domain.dto.response.MyPageDto;
import com.server.gnu_club.domain.user.Member;
import com.server.gnu_club.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    /**
     * 일반 유저 회원가입
     */
    @PostMapping("/api/member/signup")
    public ResultDto<Long> signup(@RequestBody AccountDto accountDto) {
        Boolean bDuplicated = memberService.isIdDuplicated(accountDto.getSignInId());

        // ID 중복 체크
        if (bDuplicated) {
            return new ResultDto<>(1, null);
        }
        Long memberPk = memberService.signup(accountDto);
        return new ResultDto<>(1, memberPk);
    }

    /**
     * 일반 유저 로그인
     */
    @PostMapping("/api/member/signIn")
    public ResultDto<Long> signIn(@RequestBody AccountDto accountDto) {
        Long memberId = memberService.signIn(accountDto);
        return new ResultDto<>(1, memberId);
    }

    /**
     * 일반 유저 비밀번호 변경
     */
    @PutMapping("/api/member/{memberPk}/password")
    public ResultDto<Boolean> putPassword(@PathVariable Long memberPk, @RequestBody PasswordChangeDto passwordChangeDto) {
        Boolean bCorrectPassword = memberService.isCorrectPassword(memberPk, passwordChangeDto.getCurrentPassword());

        // 입력한 현재 비밀번호가 일치하면
        if (bCorrectPassword) {
            memberService.putPassword(memberPk, passwordChangeDto.getNewPassword());
            return new ResultDto<>(1, true);
        }

        return new ResultDto<>(1, false);
    }

    /**
     * 일반 유저 프로필사진 변경
     */
    @PutMapping("/api/member/{memberPk}/profile_image")
    public ResultDto<Boolean> putProfileImage(@PathVariable Long memberPk,
                                              @RequestPart MultipartFile image) {

        memberService.putProfileImage(memberPk, image);
        return new ResultDto<>(1, true);
    }

    /**
     * 일반 유저 마이페이지 조회
     */
    @GetMapping("/api/member/{memberPk}/my_page")
    public ResultDto<MyPageDto> myPage(@PathVariable Long memberPk) {
        Member member = memberService.findMemberMyPage(memberPk);
        List<BookmarkDto> bookmarkDtos = member.getBookmarks().stream().map(BookmarkDto::new).collect(Collectors.toList());
        List<JoinedClubDto> joinedClubDtos = member.getJoinedClubs().stream().map(JoinedClubDto::new).collect(Collectors.toList());
        MyPageDto myPageDto = new MyPageDto(member.getSignInId(), member.getProfileImgUrl(), joinedClubDtos, bookmarkDtos);
        return new ResultDto<>(1, myPageDto);
    }

    /**
     * 동아리 북마크/북마크 해제
     */
    @PutMapping("/api/member/{memberPk}/bookmark")
    public ResultDto<Boolean> clubBookmark(@PathVariable Long memberPk,
                                           @RequestParam(name = "club_pk") Long clubPk) {
        boolean bBookmark = memberService.changeBookmarkStatus(memberPk, clubPk);
        return new ResultDto<>(1, bBookmark);
    }

    /**
     * 동아리 체크인/체크아웃
     */
    @PutMapping("/api/member/{memberPk}/check_in")
    public ResultDto<Boolean> checkIn(@PathVariable Long memberPk,
                                      @RequestParam(name = "club_pk") Long clubPk) {
        Boolean bCheckIn = memberService.changeCheckStatus(memberPk, clubPk);

        if (bCheckIn == null) {
            return new ResultDto<>(1, null);
        }

        return new ResultDto<>(1, bCheckIn);
    }

    /**
     * 공지사항 좋아요/취소
     */
    @PutMapping("/api/like/notice/{noticePk}")
    public ResultDto<Boolean> noticeLike(@PathVariable Long noticePk,
                                         @RequestParam(name = "member_pk") Long memberPk) {
        boolean bLike = memberService.changeLikeNotice(memberPk, noticePk);
        return new ResultDto<>(1, bLike);
    }

    /**
     * 타임라인 좋아요/취소
     */
    @PutMapping("/api/like/timeline/{timelinePk}")
    public ResultDto<Boolean> timelineLike(@PathVariable Long timelinePk,
                                           @RequestParam(name = "member_pk") Long memberPk) {
        boolean bLike = memberService.changeLikeTimeline(memberPk, timelinePk);
        return new ResultDto<>(1, bLike);
    }

    /**
     * 공지사항 댓글 작성
     */
    @PostMapping("/api/comment/notice")
    public ResultDto<Long> noticeComment(@RequestParam(name = "member_pk") Long memberPk,
                                         @RequestParam(name = "notice_pk") Long noticePk,
                                         @RequestBody CommentRequestDto dto) {
        Long commentPk = memberService.commentNotice(memberPk, noticePk, dto);
        return new ResultDto<>(1, commentPk);
    }

    /**
     * 타임라인 댓글 작성
     */
    @PostMapping("/api/comment/timeline")
    public ResultDto<Long> timelineComment(@RequestParam(name = "member_pk") Long memberPk,
                                           @RequestParam(name = "timeline_pk") Long timelinePk,
                                           @RequestBody CommentRequestDto dto) {
        Long commentPk = memberService.commentTimeline(memberPk, timelinePk, dto);
        return new ResultDto<>(1, commentPk);
    }

    /**
     * 댓글 삭제
     */
    @DeleteMapping("/api/comment/{commentPk}")
    public ResultDto<Boolean> deleteComment(@PathVariable Long commentPk) {
        memberService.deleteComment(commentPk);
        return new ResultDto<>(1, true);
    }

    /**
     * 내가 체크인한 클럽 PK 조회
     */
    @GetMapping("/api/member/{memberPk}/check_in")
    public ResultDto<Long> getCheckInClubPk(@PathVariable Long memberPk) {
        Long clubPk = memberService.findCheckInClubPk(memberPk);
        return new ResultDto<>(1, clubPk);
    }
}
