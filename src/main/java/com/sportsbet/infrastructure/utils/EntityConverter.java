package com.sportsbet.infrastructure.utils;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * A utility class to perform object conversion.
 */
@Component
@RequiredArgsConstructor
public class EntityConverter {

    private final ModelMapper modelMapper;

    /**
     * Converts the source object to the target type using ModelMapper.
     *
     * @param source     The source object to be converted.
     * @param targetType The target type to which the source object will be converted.
     * @param <T>        The type of the target entity.
     * @return The converted object of the specified target type.
     */
    public <T> T convert(Object source, Class<T> targetType) {
        return modelMapper.map(source, targetType);
    }

}
