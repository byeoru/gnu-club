package com.server.gnu_club.service;

import com.server.gnu_club.S3Service;
import com.server.gnu_club.domain.*;
import com.server.gnu_club.domain.BulletinBoard.Notice;
import com.server.gnu_club.domain.BulletinBoard.Timeline;
import com.server.gnu_club.domain.dto.request.AccountDto;
import com.server.gnu_club.domain.dto.request.CommentRequestDto;
import com.server.gnu_club.domain.user.Member;
import com.server.gnu_club.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.NoResultException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserBaseService<Member> {

    private final MemberRepository memberRepository;
    private final BookmarkRepository bookmarkRepository;
    private final ClubRepository clubRepository;
    private final CheckInRepository checkInRepository;
    private final BulletinBoardRepository bulletinBoardRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final S3Service s3Service;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Boolean isIdDuplicated(String signInId) {
        try {
            memberRepository.findById(signInId);
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    @Transactional
    public Long signup(AccountDto accountDto) {

        String encodedPassword = passwordEncoder.encode(accountDto.getPassword());
        Member member = new Member(accountDto.getSignInId(), encodedPassword);
        memberRepository.save(member);
        return member.getPk();
    }

    @Override
    public Long signIn(AccountDto accountDto) {
        try {
            Member member = memberRepository.findById(accountDto.getSignInId());
            boolean bMatch = passwordEncoder.matches(accountDto.getPassword(), member.getPassword());

            if (bMatch) {
                return member.getPk();
            }

            return null;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Member findUser(Long userId) {
        return memberRepository.findOne(userId);
    }

    @Override
    public Boolean isCorrectPassword(Long userId, String inputPassword) {
        Member member = memberRepository.findOne(userId);
        return passwordEncoder.matches(inputPassword, member.getPassword());
    }

    @Override
    @Transactional
    public void putPassword(Long userId, String newPassword) {
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        Member member = memberRepository.findOne(userId);
        member.putPassword(encodedNewPassword);
    }

    public Member findMemberMyPage(Long memberId) {
        return memberRepository.findOneWithClubBookmark(memberId);
    }

    @Transactional
    public boolean changeBookmarkStatus(Long memberPk, Long clubPk) {

        Bookmark bookmark = bookmarkRepository.findOne(memberPk, clubPk);

        // 북마크가 안되어 있으면
        if (bookmark == null) {
            Member member = memberRepository.findOne(memberPk);
            Club club = clubRepository.findOne(clubPk);
            Bookmark newBookmark = new Bookmark(member, club);
            bookmarkRepository.save(newBookmark);
            return true;
        }

        // 북마크가 되어 있으면
        bookmarkRepository.remove(bookmark);
        return false;
    }

    @Transactional
    public Boolean changeCheckStatus(Long memberPk, Long clubPk) {

        Member member = memberRepository.findOneWithCheckIn(memberPk);
        CheckIn checkIn = member.getCheckIn();
        Club club = clubRepository.findOne(clubPk);

        // 체크인 없음
        if (checkIn == null) {
            checkInRepository.save(new CheckIn(member, club));
            return true;
        }

        // 현재 동아리방에 체크인 되어 있음
        if (Objects.equals(checkIn.getClub().getPk(), clubPk)) {
            member.checkOut();
            return false;
        }

        // 다른 동아리방에 체크인 되어 있음
        return null;
    }

    @Transactional
    public boolean changeLikeNotice(Long memberPk, Long noticePk) {

        // 현재 좋아요 체크된 상태인지
        Likes like = likeRepository.findOneByNotice(memberPk, noticePk);
        Notice notice = bulletinBoardRepository.findOneNotice(noticePk);

        // 체크 되어 있으면
        if (like != null) {
            likeRepository.remove(like);
            notice.minusCount();
            return false;
        }

        // 체크 안 되어 있으면
        Member member = memberRepository.findOne(memberPk);

        Likes newLike = new Likes(member, notice);
        likeRepository.save(newLike);
        notice.plusCount();
        return true;
    }

    @Transactional
    public boolean changeLikeTimeline(Long memberPk, Long timelinePk) {

        // 현재 좋아요 체크된 상태인지
        Likes like = likeRepository.findOneByTimeline(memberPk, timelinePk);
        Timeline timeline = bulletinBoardRepository.findOneTimeline(timelinePk);

        // 체크 되어 있으면
        if (like != null) {
            likeRepository.remove(like);
            timeline.minusCount();
            return false;
        }

        // 체크 안 되어 있으면
        Member member = memberRepository.findOne(memberPk);

        Likes newLike = new Likes(member, timeline);
        likeRepository.save(newLike);
        timeline.plusCount();
        return true;
    }

    @Transactional
    public Long commentNotice(Long memberPk, Long noticePk, CommentRequestDto dto) {

        Member member = memberRepository.findOne(memberPk);
        Notice notice = bulletinBoardRepository.findOneNotice(noticePk);

        Comment comment = new Comment(dto.getComment(), member, notice);
        commentRepository.save(comment);
        return comment.getPk();
    }

    @Transactional
    public Long commentTimeline(Long memberPk, Long timelinePk, CommentRequestDto dto) {

        Member member = memberRepository.findOne(memberPk);
        Timeline timeline = bulletinBoardRepository.findOneTimeline(timelinePk);

        Comment comment = new Comment(dto.getComment(), member, timeline);
        commentRepository.save(comment);
        return comment.getPk();
    }

    @Transactional
    public void deleteComment(Long commentPk) {
        commentRepository.remove(commentPk);
    }

    @Transactional
    public void putProfileImage(Long memberPk, MultipartFile image) {
        Member member = memberRepository.findOne(memberPk);

        String newImageKey = memberPk + "member-image" + System.currentTimeMillis();
        String imageUrl = s3Service.upload(image, newImageKey);

        member.putProfileImageUrl(imageUrl);
    }

    public Long findCheckInClubPk(Long memberPk) {
        Member member = memberRepository.findOneWithCheckInWithClub(memberPk);
        return member.getCheckIn() == null ? null : member.getCheckIn().getClub().getPk();
    }
}
