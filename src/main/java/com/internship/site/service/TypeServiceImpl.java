package com.internship.site.service;

import com.internship.site.dto.TypeDto;
import com.internship.site.entity.Type;
import com.internship.site.repository.TypeRepo;
import com.internship.site.utils.MappingUtils;
import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private MappingUtils mappingUtils;

    @Autowired
    private TypeRepo typeRepo;

    @Override
    public List<TypeDto> getTypes() {
        List<Type> types = typeRepo.findAll();
        List<TypeDto> typesDto = new ArrayList<>();

        for (Type type: types) {
            typesDto.add(mappingUtils.mapToTypeDto(type));
        }

        return typesDto;
    }
}
