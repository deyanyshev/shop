package com.internship.site.dto;

import lombok.Data;

@Data
public class ProductDto {
    int id;
    String name;
    String description;
    String img;
    int cost;
    TypeDto type;
    CountryDto country;
}
