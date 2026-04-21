package com.electro.mapper.product;

import com.electro.dto.product.VariantRequest;
import com.electro.dto.product.VariantResponse;
import com.electro.entity.product.Variant;
import com.electro.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VariantMapper extends GenericMapper<Variant, VariantRequest, VariantResponse> {
    @Override
    @Mapping(source = "specifications", target = "specifications")
    VariantResponse entityToResponse(Variant entity);

    @Override
    @Mapping(source = "specifications", target = "specifications")
    Variant requestToEntity(VariantRequest request);

}
