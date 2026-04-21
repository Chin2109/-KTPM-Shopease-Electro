import React, { useEffect, useState } from 'react';
import { Container, Skeleton, Stack, useMantineTheme } from '@mantine/core';
import { useQuery } from 'react-query';
import FetchUtils, { ErrorMessage } from 'utils/FetchUtils';
import ResourceURL from 'constants/ResourceURL';
import NotifyUtils from 'utils/NotifyUtils';
import { useParams } from 'react-router-dom';
import { ClientProductResponse, ClientProductResponse_ClientVariantResponse } from 'types';
import useTitle from 'hooks/use-title';
import { ClientError } from 'components';
import ClientProductIntro from 'pages/client-product/ClientProductIntro';
import ClientProductSpecification from 'pages/client-product/ClientProductSpecification';
import ClientProductDescription from 'pages/client-product/ClientProductDescription';
import ClientProductReviews from 'pages/client-product/ClientProductReviews';
import ClientProductRelatedProducts from 'pages/client-product/ClientProductRelatedProducts';
import ClientProductGuarantee from './ClientProductGuarantee';

function ClientProduct() {
  const theme = useMantineTheme();

  const { slug } = useParams();

  const { productResponse, isLoadingProductResponse, isErrorProductResponse } =
    useGetProductApi(slug as string);
  const product = productResponse as ClientProductResponse;

  // 1. Thêm State để lưu variant đang chọn
  const [selectedVariant, setSelectedVariant] =
    useState<ClientProductResponse_ClientVariantResponse | null>(null);

  // 2. Tự động chọn variant đầu tiên khi dữ liệu load xong
  useEffect(() => {
    if (product?.productVariants?.length > 0) {
      setSelectedVariant(product.productVariants[0]);
    }
  }, [product]);
  useTitle(product?.productName);

  if (isLoadingProductResponse) {
    return <ClientProductSkeleton />;
  }

  if (isErrorProductResponse) {
    return <ClientError />;
  }

  return (
    <main>
      <Container size="xl">
        <Stack spacing={theme.spacing.xl * 2}>
          <ClientProductIntro product={product} />

          {(product.productSpecifications ||
            selectedVariant?.specifications) && (
            <ClientProductSpecification
              product={product}
              selectedVariant={selectedVariant}
            />
          )}

          {product?.guaranteeName && (
            <ClientProductGuarantee product={product} />
          )}

          {product.productDescription && (
            <ClientProductDescription product={product} />
          )}

          <ClientProductReviews productSlug={slug as string} />

          {product.productRelatedProducts.length > 0 && (
            <ClientProductRelatedProducts product={product} />
          )}
        </Stack>
      </Container>
    </main>
  );
}

function ClientProductSkeleton() {
  return (
    <main>
      <Container size="xl">
        <Stack>
          {Array(5).fill(0).map((_, index) => (
            <Skeleton key={index} height={50} radius="md"/>
          ))}
        </Stack>
      </Container>
    </main>
  );
}

function useGetProductApi(productSlug: string) {
  const {
    data: productResponse,
    isLoading: isLoadingProductResponse,
    isError: isErrorProductResponse,
  } = useQuery<ClientProductResponse, ErrorMessage>(
    ['client-api', 'products', 'getProduct', productSlug],
    () => FetchUtils.get(ResourceURL.CLIENT_PRODUCT + '/' + productSlug),
    {
      onError: () => NotifyUtils.simpleFailed('Lấy dữ liệu không thành công'),
      refetchOnWindowFocus: false,
      keepPreviousData: true,
    }
  );

  return { productResponse, isLoadingProductResponse, isErrorProductResponse };
}

export default ClientProduct;
