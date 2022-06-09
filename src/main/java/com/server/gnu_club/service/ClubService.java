package com.server.gnu_club.service;

import com.server.gnu_club.S3Service;
import com.server.gnu_club.domain.BulletinBoard.Notice;
import com.server.gnu_club.domain.BulletinBoard.Timeline;
import com.server.gnu_club.domain.Category;
import com.server.gnu_club.domain.Club;
import com.server.gnu_club.domain.Likes;
import com.server.gnu_club.domain.dto.request.BulletinBoardRequestDto;
import com.server.gnu_club.domain.dto.response.RankingResponseDto;
import com.server.gnu_club.domain.user.Manager;
import com.server.gnu_club.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.NoResultException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClubService {

    private final S3Service s3Service;
    private final ClubRepository clubRepository;
    private final CategoryRepository categoryRepository;
    private final BulletinBoardRepository bulletinBoardRepository;
    private final ManagerRepository managerRepository;
    private final CheckInRepository checkInRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public Long registerCategory(String name) {
        Category category = new Category(name);
        categoryRepository.save(category);
        return category.getPk();
    }

    @Transactional
    public Long registerClub(Long managerPk, Long categoryPk, String clubName, String intro) {
        Manager manager = managerRepository.findOne(managerPk);
        Category category = categoryRepository.findOne(categoryPk);
        Club club = new Club(manager, clubName, intro, category);
        clubRepository.save(club);
        return club.getPk();
    }

    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    public List<Club> findAllClubByCategoryPk(Long categoryPk) {
        return clubRepository.findAllByCategoryPk(categoryPk);
    }

    @Transactional
    public Long registerNotice(Long clubPk, BulletinBoardRequestDto dto, MultipartFile image) {

        Club club = clubRepository.findOne(clubPk);
        String imageUrl = null;

        if (image != null) {
            String newImageKey = clubPk + "notice-image" + System.currentTimeMillis();
            imageUrl = s3Service.upload(image, newImageKey);
        }
        Notice notice = new Notice(club, dto.getTitle(), dto.getContent(), imageUrl);
        bulletinBoardRepository.saveNotice(notice);
        return notice.getPk();
    }

    @Transactional
    public void removeNotice(Long noticePk) {
        bulletinBoardRepository.removeNotice(noticePk);
    }

    @Transactional
    public Long registerTimeline(Long clubPk, BulletinBoardRequestDto dto, MultipartFile image) {

        Club club = clubRepository.findOne(clubPk);
        String imageUrl = null;

        if (image != null) {
            String newImageKey = clubPk + "timeline-image" + System.currentTimeMillis();
            imageUrl = s3Service.upload(image, newImageKey);
        }
        Timeline timeline = new Timeline(club, dto.getTitle(), dto.getContent(), imageUrl);
        bulletinBoardRepository.saveTimeline(timeline);
        return timeline.getPk();
    }

    @Transactional
    public void removeTimeline(Long timelinePk) {
        bulletinBoardRepository.removeTimeline(timelinePk);
    }

    public Club findClub(Long clubPk) {
        return clubRepository.findOne(clubPk);
    }

    public Boolean isCheckIn(Long clubPk, Long memberPk) {
        try {
            checkInRepository.findOne(clubPk, memberPk);
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    public Notice findNoticeWithComments(Long noticePk) {
        return bulletinBoardRepository.findOneNoticeWithComments(noticePk);
    }

    public Timeline findTimelineWithComments(Long timelinePk) {
        return bulletinBoardRepository.findOneTimelineWithComments(timelinePk);
    }

    public Boolean isLikedNotice(Long memberPk, Long noticePk) {

        Likes like = likeRepository.findOneByNotice(memberPk, noticePk);
        return like != null;
    }

    public Boolean isLikedTimeline(Long memberPk, Long timelinePk) {

        Likes like = likeRepository.findOneByTimeline(memberPk, timelinePk);
        return like != null;
    }

    public long getCurrentNumber(Long clubPk) {
        return checkInRepository.findCurrentNumber(clubPk);
    }

    public List<Notice> findAllNotice(Long clubPk) {
        return bulletinBoardRepository.findAllNotice(clubPk);
    }

    public List<Timeline> findAllTimeline(Long clubPk) {
        return bulletinBoardRepository.findAllTimeline(clubPk);
    }

    public List<RankingResponseDto> findTotalRanking() {
        return bulletinBoardRepository.findTimelineTotalRanking();
    }

    public List<RankingResponseDto> findWeekRanking() {
        return bulletinBoardRepository.findTimelineWeekRanking();
    }

    @Transactional
    public void putBackgroundImage(Long clubPk, MultipartFile image) {

        if (image == null) {
            return;
        }

        Club club = clubRepository.findOne(clubPk);

        String newImageKey = clubPk + "club-image" + System.currentTimeMillis();
        String imageUrl = s3Service.upload(image, newImageKey);

        club.putBackgroundImage(imageUrl);
    }
}
