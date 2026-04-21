package com.electro.mapper.product;

import com.electro.dto.client.ClientProductResponse;
import com.electro.entity.product.Variant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientVariantMapper {

    @Mapping(source = "specifications", target = "variantSpecifications")
    ClientProductResponse.ClientVariantResponse toResponse(Variant variant);

    List<ClientProductResponse.ClientVariantResponse> toResponses(List<Variant> variants);
}