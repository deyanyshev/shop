package com.internship.site.repository;

import com.internship.site.entity.Token;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TokenRepo extends CrudRepository<Token, Integer> {
    List<Token> findByToken(String token);

    @Override
    void delete(Token token);
}
