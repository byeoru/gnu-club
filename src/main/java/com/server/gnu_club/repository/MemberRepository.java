package com.server.gnu_club.repository;

import com.server.gnu_club.domain.user.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

@Repository
@RequiredArgsConstructor
public class MemberRepository implements UserBaseRepository<Member> {

    private final EntityManager em;


    @Override
    public void save(Member user) throws PersistenceException {
        em.persist(user);
    }

    @Override
    public Member findOne(Long memberPk) throws PersistenceException {
        return em.createQuery("select m from Member m where m.pk=:memberPk", Member.class)
                .setParameter("memberPk", memberPk)
                .getSingleResult();
    }

    @Override
    public Member findById(String signInId) throws PersistenceException {
        return em.createQuery("select m from Member m where m.signInId=:id", Member.class)
                .setParameter("id", signInId)
                .getSingleResult();
    }

    public Member findOneWithCheckIn(Long memberPk) throws PersistenceException {
        return em.createQuery("select m from Member m left join fetch m.checkIn where m.pk=:memberPk", Member.class)
                .setParameter("memberPk", memberPk)
                .getSingleResult();
    }

    public Member findOneWithCheckInWithClub(Long memberPk) throws PersistenceException {
        return em.createQuery("select m from Member m" +
                " left join fetch m.checkIn c" +
                " left join fetch c.club where m.pk=:memberPk", Member.class)
                .setParameter("memberPk", memberPk)
                .getSingleResult();
    }

    public Member findOneWithClubBookmark(Long memberPk) {
        return em.createQuery("select m from Member m" +
                        " left join fetch m.joinedClubs c" +
                        " left join fetch c.club" +
                        " left join fetch m.bookmarks b" +
                        " left join fetch b.club" +
                        " where m.pk =:memberPk", Member.class)
                .setParameter("memberPk", memberPk)
                .getSingleResult();
    }
}
