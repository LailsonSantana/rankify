package com.example.rankify.mapper;

import com.example.rankify.dto.ItemDTO;
import com.example.rankify.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemMapper {

    ItemDTO toItemDTO(Item item);
    Item toItem(ItemDTO itemDTO);

    List<ItemDTO> toItemsDTO(List<Item> items);
    List<Item> toItems(List<ItemDTO> itemDTOs);
}
