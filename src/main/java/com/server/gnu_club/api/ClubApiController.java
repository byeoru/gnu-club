package com.server.gnu_club.api;

import com.server.gnu_club.domain.BulletinBoard.Notice;
import com.server.gnu_club.domain.BulletinBoard.Timeline;
import com.server.gnu_club.domain.Club;
import com.server.gnu_club.domain.dto.ResultDto;
import com.server.gnu_club.domain.dto.request.BulletinBoardRequestDto;
import com.server.gnu_club.domain.dto.response.*;
import com.server.gnu_club.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class ClubApiController {

    private final ClubService clubService;

    /**
     * 공지사항 등록
     */
    @PostMapping("/api/club/{clubPk}/bulletin_board/notice")
    public ResultDto<Long> postNotice(@PathVariable Long clubPk,
                                      @RequestPart BulletinBoardRequestDto dto,
                                      @RequestPart(required = false) MultipartFile image) {
        Long noticePk = clubService.registerNotice(clubPk, dto, image);
        return new ResultDto<>(1, noticePk);
    }

    /**
     * 공지사항 삭제
     */
    @DeleteMapping("/api/club/bulletin_board/notice/{noticePk}")
    public ResultDto<Boolean> deleteNotice(@PathVariable Long noticePk) {
        clubService.removeNotice(noticePk);
        return new ResultDto<>(1, true);
    }

    /**
     * 타임라인 등록
     */
    @PostMapping("/api/club/{clubPk}/bulletin_board/timeline")
    public ResultDto<Long> postTimeline(@PathVariable Long clubPk,
                                        @RequestPart BulletinBoardRequestDto dto,
                                        @RequestPart(required = false) MultipartFile image) {
        Long timelinePk = clubService.registerTimeline(clubPk, dto, image);
        return new ResultDto<>(1, timelinePk);
    }

    /**
     * 타임라인 삭제
     */
    @DeleteMapping("/api/club/bulletin_board/timeline/{timelinePk}")
    public ResultDto<Boolean> deleteTimeline(@PathVariable Long timelinePk) {
        clubService.removeTimeline(timelinePk);
        return new ResultDto<>(1, true);
    }

    /**
     * 동아리 상세 조회
     */
    @GetMapping("/api/club/{clubPk}")
    public ResultDto<ClubDetailDto> getClubDetail(@PathVariable Long clubPk,
                                                  @RequestParam(name = "member_pk") Long memberPk) {
        Club club = clubService.findClub(clubPk);
        Boolean bCheckIn = clubService.isCheckIn(clubPk, memberPk);
        int currentNumber = (int) clubService.getCurrentNumber(clubPk);
        ClubDetailDto clubDetailDto = new ClubDetailDto(club, bCheckIn, currentNumber);
        return new ResultDto<>(1, clubDetailDto);
    }

    /**
     * 공지사항 리스트 조회
     */
    @GetMapping("/api/club/{clubPk}/notices")
    public ResultDto<List<NoticePreviewDto>> getNoticeList(@PathVariable Long clubPk) {
        List<Notice> notices = clubService.findAllNotice(clubPk);
        List<NoticePreviewDto> previewDtos = notices.stream().map(NoticePreviewDto::new).collect(Collectors.toList());
        return new ResultDto<>(previewDtos.size(), previewDtos);
    }

    /**
     * 타임라인 리스트 조회
     */
    @GetMapping("/api/club/{clubPk}/timelines")
    public ResultDto<List<TimelinePreviewDto>> getTimelineList(@PathVariable Long clubPk) {
        List<Timeline> timelines = clubService.findAllTimeline(clubPk);
        List<TimelinePreviewDto> previewDtos = timelines.stream().map(TimelinePreviewDto::new).collect(Collectors.toList());
        return new ResultDto<>(previewDtos.size(), previewDtos);
    }

    /**
     * 공지사항 상세
     */
    @GetMapping("/api/club/notice/{noticePk}")
    public ResultDto<PostingDetailDto> getNoticeDetail(@PathVariable Long noticePk,
                                                       @RequestParam(name = "member_pk") Long memberPk) {
        Notice notice = clubService.findNoticeWithComments(noticePk);
        Boolean bLikedNotice = clubService.isLikedNotice(memberPk, noticePk);
        PostingDetailDto postingDetailDto = new PostingDetailDto(notice, bLikedNotice);
        return new ResultDto<>(1, postingDetailDto);
    }

    /**
     * 타임라인 상세
     */
    @GetMapping("/api/club/timeline/{timelinePk}")
    public ResultDto<PostingDetailDto> getTimelineDetail(@PathVariable Long timelinePk,
                                                         @RequestParam(name = "member_pk") Long memberPk) {
        Timeline timeline = clubService.findTimelineWithComments(timelinePk);
        Boolean bLikedTimeline = clubService.isLikedTimeline(memberPk, timelinePk);
        PostingDetailDto postingDetailDto = new PostingDetailDto(timeline, bLikedTimeline);
        return new ResultDto<>(1, postingDetailDto);
    }

    /**
     * 전체(누적) 랭킹 조회
     */
    @GetMapping("/api/clubs/ranking/total")
    public ResultDto<List<RankingResponseDto>> getTotalRanking() {
        List<RankingResponseDto> totalRankings = clubService.findTotalRanking();
        return new ResultDto<>(totalRankings.size(), totalRankings);
    }

    /**
     * 이번 주 랭킹 조회
     */
    @GetMapping("/api/clubs/ranking/week")
    public ResultDto<List<RankingResponseDto>> getWeekRanking() {
        List<RankingResponseDto> weekRankings = clubService.findWeekRanking();
        return new ResultDto<>(weekRankings.size(), weekRankings);
    }

    /**
     * 동아리 background image 등록/수정
     */
    @PutMapping("/api/club/{clubPk}/background_image")
    public ResultDto<Boolean> putBackgroundImage(@PathVariable Long clubPk,
                                                 @RequestPart(required = false) MultipartFile image) {
        clubService.putBackgroundImage(clubPk, image);
        return new ResultDto<>(1, true);
    }
}
