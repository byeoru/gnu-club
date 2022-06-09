package com.server.gnu_club.repository;

import com.server.gnu_club.domain.Likes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

@Repository
@RequiredArgsConstructor
public class LikeRepository {

    private final EntityManager em;

    public void save(Likes likes) {
        em.persist(likes);
    }

    public void remove(Likes likes) {
        em.remove(likes);
    }

    public Likes findOneByNotice(Long memberPk, Long noticePk) throws PersistenceException {
        try {
            return em.createQuery("select m from Likes m where m.member.pk =:memberPk and m.notice.pk =:noticePk", Likes.class)
                    .setParameter("memberPk", memberPk)
                    .setParameter("noticePk", noticePk)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Likes findOneByTimeline(Long memberPk, Long timelinePk) throws PersistenceException {
        try {
            return em.createQuery("select m from Likes m where m.member.pk =:memberPk and m.timeline.pk =:timelinePk", Likes.class)
                    .setParameter("memberPk", memberPk)
                    .setParameter("timelinePk", timelinePk)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
