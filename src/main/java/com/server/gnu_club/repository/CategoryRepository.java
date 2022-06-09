package com.server.gnu_club.repository;

import com.server.gnu_club.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final EntityManager em;

    public void save(Category category) {
        em.persist(category);
    }

    public Category findOne(Long pk) {
        return em.find(Category.class, pk);
    }

    public List<Category> findAll() {
        return em.createQuery("select m from Category m", Category.class)
                .getResultList();
    }
}
