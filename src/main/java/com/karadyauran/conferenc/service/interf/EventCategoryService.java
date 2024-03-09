package com.karadyauran.conferenc.service.interf;

import com.karadyauran.conferenc.dto.normal.EventCategoryDto;

import java.util.UUID;

public interface EventCategoryService
{
    void create(EventCategoryDto category);

    EventCategoryDto findById(UUID id);

    void changeTitle(UUID id, String newTitle);

    void delete(UUID id);
}
