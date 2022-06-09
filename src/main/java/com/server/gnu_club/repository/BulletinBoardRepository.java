package com.server.gnu_club.repository;

import com.server.gnu_club.domain.BulletinBoard.Notice;
import com.server.gnu_club.domain.BulletinBoard.Timeline;
import com.server.gnu_club.domain.dto.response.RankingResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BulletinBoardRepository {

    private final EntityManager em;
    private final JdbcTemplate jdbcTemplate;

    public void saveNotice(Notice notice) {
        em.persist(notice);
    }

    public void removeNotice(Long noticePk) {
        Notice notice = em.find(Notice.class, noticePk);
        em.remove(notice);
    }

    public void saveTimeline(Timeline timeline) {
        em.persist(timeline);
    }

    public void removeTimeline(Long timelinePk) {
        Timeline timeline = em.find(Timeline.class, timelinePk);
        em.remove(timeline);
    }

    public Notice findOneNoticeWithComments(Long noticePk) {
        return em.createQuery("select m from Notice m" +
                        " left join fetch m.comments c" +
                        " left join fetch c.member" +
                        " where m.pk =:noticePk", Notice.class)
                .setParameter("noticePk", noticePk)
                .getSingleResult();
    }

    public Timeline findOneTimelineWithComments(Long timelinePk) {
        return em.createQuery("select m from Timeline m" +
                        " left join fetch m.comments c" +
                        " left join fetch c.member" +
                        " where m.pk =:timelinePk", Timeline.class)
                .setParameter("timelinePk", timelinePk)
                .getSingleResult();
    }

    public List<Notice> findAllNotice(Long clubPk) {
        return em.createQuery("select m from Notice m where m.club.pk =:clubPk", Notice.class)
                .setParameter("clubPk", clubPk)
                .getResultList();
    }

    public List<Timeline> findAllTimeline(Long clubPk) {
        return em.createQuery("select m from Timeline m where m.club.pk =:clubPk", Timeline.class)
                .setParameter("clubPk", clubPk)
                .getResultList();
    }

    public Notice findOneNotice(Long noticePk) {
        return em.find(Notice.class, noticePk);
    }

    public Timeline findOneTimeline(Long timeline) {
        return em.find(Timeline.class, timeline);
    }

    public List<RankingResponseDto> findTimelineTotalRanking() {
        return jdbcTemplate.query("select a.club_pk as club_pk, club_name, like_count from (" +
                "select club_pk, sum(like_count) as like_count from timeline group by club_pk) a " +
                "left join club b on a.club_pk = b.club_pk order by like_count desc limit 5",
                (rs, rowNum) -> new RankingResponseDto(rs.getLong("club_pk"), rs.getString("club_name")));
    }

    public List<RankingResponseDto> findTimelineWeekRanking() {
        return jdbcTemplate.query("select a.club_pk, club_name, like_count from (" +
                "select club_pk, sum(like_count) as like_count from timeline " +
                "WHERE YEARWEEK(posting_time) = YEARWEEK(now()) group by club_pk) a " +
                "left join club b on a.club_pk = b.club_pk order by like_count desc limit 5",
                ((rs, rowNum) -> new RankingResponseDto(rs.getLong("club_pk"), rs.getString("club_name"))));
    }
}
