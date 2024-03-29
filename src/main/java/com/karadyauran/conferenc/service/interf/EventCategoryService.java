package com.karadyauran.conferenc.service.interf;

import com.karadyauran.conferenc.dto.create.EventCategoryCreateDto;
import com.karadyauran.conferenc.dto.normal.EventCategoryDto;

import java.util.UUID;

public interface EventCategoryService
{
    void create(EventCategoryCreateDto category);

    EventCategoryDto findById(UUID id);

    void changeName(UUID id, String newName);

    void changeDescription(UUID id, String newDescription);

    void delete(UUID id);

    boolean eventCategoryDoesNotExists(UUID id);
}
