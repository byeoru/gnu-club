package com.server.gnu_club.repository;

import com.server.gnu_club.domain.CheckIn;
import com.server.gnu_club.domain.Club;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

@Repository
@RequiredArgsConstructor
public class CheckInRepository {

    private final EntityManager em;

    public void save(CheckIn checkIn) {
        em.persist(checkIn);
    }

    public void remove(Long checkInPk) {
        CheckIn checkIn = em.find(CheckIn.class, checkInPk);
        em.remove(checkIn);
    }

    public void remove(Long clubPk, Long memberPk) {
        CheckIn checkIn = findOne(clubPk, memberPk);
        em.remove(checkIn);
    }

    public CheckIn findOne(Long clubPk, Long memberPk) throws PersistenceException {
        return em.createQuery("select m from CheckIn m where m.club.pk =:clubPk and m.member.pk =:memberPk", CheckIn.class)
                .setParameter("clubPk", clubPk)
                .setParameter("memberPk", memberPk)
                .getSingleResult();
    }

    public long findCurrentNumber(Long clubPk) {
        return em.createQuery("select count(m) from CheckIn m where m.club.pk=:clubPk", Long.class)
                .setParameter("clubPk", clubPk)
                .getSingleResult();
    }
}
