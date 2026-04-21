import {
  Box,
  Group,
  Stack,
  Table,
  Title,
  useMantineTheme,
} from "@mantine/core";
import React, { useMemo } from "react";
import { Apps } from "tabler-icons-react";
import {
  ClientProductResponse,
  ClientProductResponse_ClientVariantResponse,
} from "types";

interface ClientProductSpecificationProps {
  product: ClientProductResponse;
  selectedVariant: ClientProductResponse_ClientVariantResponse | null;
}

function ClientProductSpecification({
  product,
  selectedVariant,
}: ClientProductSpecificationProps) {
  const theme = useMantineTheme();

  // Logic gộp thông số: Ưu tiên variant specs ghi đè product specs nếu trùng code
  const displaySpecs = useMemo(() => {
    const specsMap = new Map<string, any>();

    // 1. Lấy thông số chung của sản phẩm (nếu có)
    product.productSpecifications?.content.forEach((item) => {
      specsMap.set(item.code, item);
    });

    // 2. Lấy thông số riêng của variant (ghi đè lên thông số chung nếu trùng mã)
    selectedVariant?.specifications?.content.forEach((item) => {
      specsMap.set(item.code, item);
    });

    return Array.from(specsMap.values());
  }, [product, selectedVariant]);

  return (
    <Stack id="specifications">
      <Group spacing="xs">
        <Apps />
        <Title order={2}>Thông số kỹ thuật</Title>
      </Group>
      <Box
        sx={{
          border: `1px solid ${theme.colorScheme === "dark" ? theme.colors.dark[4] : theme.colors.gray[3]}`,
          borderRadius: theme.radius.md,
          overflow: "hidden",
          [theme.fn.largerThan("md")]: { width: 600 },
        }}
      >
        <Table verticalSpacing="sm" horizontalSpacing="md" striped>
          <thead>
            <tr>
              <th style={{ width: "40%" }}>Thông số</th>
              <th>Giá trị</th>
            </tr>
          </thead>
          <tbody>
            {displaySpecs.map((spec) => (
              <tr key={spec.code}>
                <td style={{ fontWeight: 600 }}>{spec.name}</td>
                <td>{spec.value}</td>
              </tr>
            ))}
          </tbody>
        </Table>
      </Box>
    </Stack>
  );
}

export default ClientProductSpecification;
