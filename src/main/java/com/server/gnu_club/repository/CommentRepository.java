package com.server.gnu_club.repository;

import com.server.gnu_club.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public Comment findOne(Long commentPk) {
        return em.find(Comment.class, commentPk);
    }

    public void save(Comment comment) {
        em.persist(comment);
    }

    public void remove(Long commentPk) {
        Comment comment = em.find(Comment.class, commentPk);
        em.remove(comment);
    }
}
