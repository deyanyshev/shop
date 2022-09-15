package com.internship.site.repository;

import com.internship.site.entity.Product;
import com.internship.site.entity.Purchase;
import com.internship.site.entity.user.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface PurchaseRepo extends CrudRepository<Purchase, Integer> {
    List<Purchase> findPurchasesByUser(User user);
    @Transactional
    void deleteByUserAndProduct(User user, Product product);
}
