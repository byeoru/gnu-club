package com.server.gnu_club.repository;

import com.server.gnu_club.domain.user.Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

@Repository
@RequiredArgsConstructor
public class ManagerRepository implements UserBaseRepository<Manager> {

    private final EntityManager em;

    @Override
    public void save(Manager user) throws PersistenceException {
        em.persist(user);
    }

    @Override
    public Manager findOne(Long userId) throws PersistenceException {
        return em.find(Manager.class, userId);
    }

    @Override
    public Manager findById(String signInId) throws PersistenceException {
        return em.createQuery("select m from Manager m where m.signInId=:id", Manager.class)
                .setParameter("id", signInId)
                .getSingleResult();
    }
}
