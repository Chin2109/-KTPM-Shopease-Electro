package com.electro.mapper.product;

import com.electro.dto.client.ClientProductResponse;
import com.electro.dto.product.ProductRequest;
import com.electro.dto.product.ProductResponse;
import com.electro.entity.product.Product;
import com.electro.mapper.GenericMapper;
import com.electro.mapper.general.ImageMapper;
import com.electro.utils.MapperUtils;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {MapperUtils.class,
                ImageMapper.class,
                BrandMapper.class,
                SupplierMapper.class,
                UnitMapper.class,
                GuaranteeMapper.class,
                ClientVariantMapper.class,
                VariantMapper.class})
public interface ProductMapper extends GenericMapper<Product, ProductRequest, ProductResponse> {

    @Override
    @BeanMapping(qualifiedByName = "attachProduct")
    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "brandId", target = "brand")
    @Mapping(source = "supplierId", target = "supplier")
    @Mapping(source = "unitId", target = "unit")
    @Mapping(source = "guaranteeId", target = "guarantee")
    @Mapping(source = "variants", target = "variants")
    Product requestToEntity(ProductRequest request);

    @Override
    @BeanMapping(qualifiedByName = "attachProduct")
    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "brandId", target = "brand")
    @Mapping(source = "supplierId", target = "supplier")
    @Mapping(source = "unitId", target = "unit")
    @Mapping(source = "guaranteeId", target = "guarantee")
    Product partialUpdate(@MappingTarget Product entity, ProductRequest request);

    @Mapping(source = "variants", target = "productVariants")
    ClientProductResponse toClientResponse(Product product);

}
