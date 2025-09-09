package com.example.rankify.mapper;

import com.example.rankify.dto.ItemDTO;
import com.example.rankify.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemMapper {

    ItemDTO toDTO(Item rankedEntity);
    Item toItem(ItemDTO rankedEntityDTO);
}
