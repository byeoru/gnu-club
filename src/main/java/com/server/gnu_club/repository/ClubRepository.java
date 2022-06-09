package com.server.gnu_club.repository;

import com.server.gnu_club.domain.CheckIn;
import com.server.gnu_club.domain.Club;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClubRepository {

    private final EntityManager em;

    public void save(Club club) {
        em.persist(club);
    }

    public Club findOne(Long clubPk) {
        return em.find(Club.class, clubPk);
    }

    public List<Club> findAllByCategoryPk(Long categoryPk) {
        return em.createQuery("select m from Club m where m.category.pk =:categoryPk", Club.class)
                .setParameter("categoryPk", categoryPk)
                .getResultList();
    }
}
