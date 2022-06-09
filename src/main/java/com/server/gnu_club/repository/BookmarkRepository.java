package com.server.gnu_club.repository;

import com.server.gnu_club.domain.Bookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

@Repository
@RequiredArgsConstructor
public class BookmarkRepository {

    private final EntityManager em;

    public Bookmark findOne(Long memberPk, Long clubPk) throws PersistenceException {
        try {
            return em.createQuery("select m from Bookmark m where m.member.pk=:memberPk and m.club.pk=:clubPk", Bookmark.class)
                    .setParameter("memberPk", memberPk)
                    .setParameter("clubPk", clubPk)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(Bookmark bookmark) {
        em.persist(bookmark);
    }

    public void remove(Long bookmarkPk) {
        Bookmark bookmark = em.find(Bookmark.class, bookmarkPk);
        em.remove(bookmark);
    }

    public void remove(Bookmark bookmark) {
        em.remove(bookmark);
    }
}
