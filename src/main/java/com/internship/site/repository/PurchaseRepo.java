package com.internship.site.repository;

import com.internship.site.entity.Purchase;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepo extends CrudRepository<Purchase, Integer> {
}
