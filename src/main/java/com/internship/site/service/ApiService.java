package com.internship.site.service;

import org.springframework.stereotype.Service;

@Service
public interface ApiService {
    public String generateToken(int len);
}
