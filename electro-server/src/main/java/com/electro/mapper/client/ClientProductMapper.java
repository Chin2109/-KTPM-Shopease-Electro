package com.electro.mapper.client;

import com.electro.dto.client.ClientListedProductResponse;
import com.electro.dto.client.ClientProductResponse;
import com.electro.entity.general.Image;
import com.electro.entity.inventory.DocketVariant;
import com.electro.entity.product.Product;
import com.electro.entity.product.Variant;
import com.electro.mapper.general.ImageMapper;
import com.electro.mapper.promotion.PromotionMapper;
import com.electro.projection.inventory.SimpleProductInventory;
import com.electro.repository.inventory.DocketVariantRepository;
import com.electro.repository.promotion.PromotionRepository;
import com.electro.utils.InventoryUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ClientProductMapper {

    private ImageMapper imageMapper;
    private ClientCategoryMapper clientCategoryMapper;
    private DocketVariantRepository docketVariantRepository;
    private PromotionRepository promotionRepository;
    private PromotionMapper promotionMapper;

    public ClientListedProductResponse entityToListedResponse(Product product, List<SimpleProductInventory> productInventories) {
        ClientListedProductResponse clientListedProductResponse = new ClientListedProductResponse();

        clientListedProductResponse
                .setProductId(product.getId())
                .setProductStatus(product.getStatus())
                .setProductName(product.getName())
                .setProductSlug(product.getSlug())
                .setProductThumbnail(product.getImages().stream()
                        .filter(Image::getIsThumbnail)
                        .findAny()
                        .map(Image::getPath)
                        .orElse(null));

        List<Double> prices = product.getVariants().stream()
                .map(Variant::getPrice).distinct().sorted().collect(Collectors.toList());

        clientListedProductResponse.setProductPriceRange(
                prices.size() == 0
                        ? Collections.emptyList()
                        : prices.size() == 1
                        ? List.of(prices.get(0))
                        : List.of(prices.get(0), prices.get(prices.size() - 1))
        );

        product.getVariants().forEach(v -> {
            System.out.println("VARIANT ID = " + v.getId());
            System.out.println("SPEC RAW = " + v.getSpecifications());
        });

        clientListedProductResponse.setProductVariants(product.getVariants().stream()
                .map(variant -> new ClientListedProductResponse.ClientListedVariantResponse()
                        .setVariantId(variant.getId())
                        .setVariantPrice(variant.getPrice())
                        .setVariantProperties(variant.getProperties())
                        .setVariantSpecifications(variant.getSpecifications()))
                .collect(Collectors.toList()));

        clientListedProductResponse.setProductSaleable(productInventories.stream()
                .filter(productInventory -> productInventory.getProductId().equals(product.getId()))
                .findAny()
                .map(productInventory -> productInventory.getCanBeSold() > 0)
                .orElse(false));

        clientListedProductResponse.setProductPromotion(promotionRepository
                .findActivePromotionByProductId(product.getId())
                .stream()
                .findFirst()
                .map(promotionMapper::entityToClientResponse)
                .orElse(null));

        return clientListedProductResponse;
    }

    public ClientProductResponse entityToResponse(Product product,
                                                  List<SimpleProductInventory> productInventories,
                                                  int averageRatingScore,
                                                  int countReviews,
                                                  List<ClientListedProductResponse> relatedProductResponses) {
        ClientProductResponse clientProductResponse = new ClientProductResponse();

        clientProductResponse.setProductId(product.getId());
        clientProductResponse.setProductStatus(product.getStatus());
        clientProductResponse.setProductName(product.getName());
        clientProductResponse.setProductSlug(product.getSlug());
        clientProductResponse.setProductShortDescription(product.getShortDescription());
        clientProductResponse.setProductDescription(product.getDescription());
        clientProductResponse.setProductImages(imageMapper.entityToResponse(product.getImages()));
        clientProductResponse.setProductCategory(clientCategoryMapper.entityToResponse(product.getCategory(), false));
        clientProductResponse.setProductBrand(product.getBrand() == null ? null : new ClientProductResponse.ClientBrandResponse()
                .setBrandId(product.getBrand().getId())
                .setBrandName(product.getBrand().getName()));
        clientProductResponse.setProductSpecifications(product.getSpecifications());

        List<Long> variantIds = product.getVariants().stream()
                .map(Variant::getId)
                .collect(Collectors.toList()); // Standard Java 8+ approach
        List<DocketVariant> docketVariants = docketVariantRepository.findByVariantIds(variantIds);
        Map<Long, List<DocketVariant>> docketVariantMap = docketVariants.stream()
                .collect(Collectors.groupingBy(dv -> dv.getVariant().getId()));

        clientProductResponse.setProductVariants(product.getVariants().stream()
                .map(variant -> new ClientProductResponse.ClientVariantResponse()
                        .setVariantId(variant.getId())
                        .setVariantPrice(variant.getPrice())
                        .setVariantProperties(variant.getProperties())
                        .setVariantSpecifications(variant.getSpecifications())
                        .setVariantInventory(
                                InventoryUtils.calculateInventoryIndices(
                                        docketVariantMap.getOrDefault(variant.getId(), Collections.emptyList())
                                ).get("canBeSold")))
                .collect(Collectors.toList()));
        clientProductResponse.setProductSaleable(productInventories.stream()
                .filter(productInventory -> productInventory.getProductId().equals(product.getId()))
                .findAny()
                .map(productInventory -> productInventory.getCanBeSold() > 0)
                .orElse(false));
        clientProductResponse.setProductAverageRatingScore(averageRatingScore);
        clientProductResponse.setProductCountReviews(countReviews);
        clientProductResponse.setProductRelatedProducts(relatedProductResponses);
        clientProductResponse.setProductPromotion(promotionRepository
                .findActivePromotionByProductId(product.getId())
                .stream()
                .findFirst()
                .map(promotionMapper::entityToClientResponse)
                .orElse(null));
        clientProductResponse.setGuaranteeName(
                product.getGuarantee() != null ? product.getGuarantee().getName() : null
        );
        clientProductResponse.setGuaranteeDescription(
                product.getGuarantee() != null ? product.getGuarantee().getDescription() : null
        );

        return clientProductResponse;
    }

}
