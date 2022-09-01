package com.internship.site.repository;

import com.internship.site.entity.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepo extends CrudRepository<Token, Integer> {
}
