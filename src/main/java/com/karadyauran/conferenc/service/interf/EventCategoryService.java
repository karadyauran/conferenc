package com.karadyauran.conferenc.service.interf;

import com.karadyauran.conferenc.dto.create.EventCategoryCreateDto;
import com.karadyauran.conferenc.dto.shorted.EventCategoryShortDto;

import java.util.UUID;

public interface EventCategoryService
{
    void create(EventCategoryCreateDto category);

    EventCategoryShortDto findById(UUID id);

    void changeName(UUID id, String newName);

    void changeDescription(UUID id, String newDescription);

    void delete(UUID id);

    boolean eventCategoryDoesNotExists(UUID id);
}
