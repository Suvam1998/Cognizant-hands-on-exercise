package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Hands-on 6: Criteria Query.
 *
 * The WHERE clause is built dynamically from whichever filters the user chose,
 * using CriteriaBuilder / CriteriaQuery / Root / TypedQuery — no fixed HQL.
 */
@Service
public class ProductSearchService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Product> search(ProductFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> product = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        if (filter.getKeyword() != null) {
            predicates.add(cb.like(cb.lower(product.get("name")),
                    "%" + filter.getKeyword().toLowerCase() + "%"));
        }
        if (filter.getCategory() != null) {
            predicates.add(cb.equal(product.get("category"), filter.getCategory()));
        }
        if (filter.getMinRating() != null) {
            predicates.add(cb.greaterThanOrEqualTo(product.get("rating"), filter.getMinRating()));
        }
        if (filter.getMinRam() != null) {
            predicates.add(cb.greaterThanOrEqualTo(product.get("ram"), filter.getMinRam()));
        }
        if (filter.getMinCpuSpeed() != null) {
            predicates.add(cb.greaterThanOrEqualTo(product.get("cpuSpeed"), filter.getMinCpuSpeed()));
        }
        if (filter.getOs() != null) {
            predicates.add(cb.equal(product.get("os"), filter.getOs()));
        }

        // Combine all chosen filters with AND. No filters => match everything.
        cq.select(product).where(cb.and(predicates.toArray(new Predicate[0])));

        TypedQuery<Product> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
