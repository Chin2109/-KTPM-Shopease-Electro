import React from "react";
import {
  Button,
  Card,
  Grid,
  Group,
  Stack,
  Text,
  Title,
  useMantineTheme,
  Skeleton,
} from "@mantine/core";
import { List } from "tabler-icons-react";
import PageConfigs from "pages/PageConfigs";
import { Link } from "react-router-dom";
import { useQuery } from "react-query";
import FetchUtils, { ErrorMessage } from "utils/FetchUtils";
import { ClientCategoryResponse, CollectionWrapper } from "types";
import ResourceURL from "constants/ResourceURL";

function ClientHomeFeaturedCategories() {
  const theme = useMantineTheme();

  const {
    data: categoryResponses,
    isLoading,
    isError,
  } = useQuery<CollectionWrapper<ClientCategoryResponse>, ErrorMessage>(
    ["client-api", "categories", "getAllCategories"],
    () => FetchUtils.get(ResourceURL.CLIENT_CATEGORY),
    {
      refetchOnWindowFocus: false,
    },
  );

  return (
    <Stack>
      {/* Header */}
      <Group position="apart">
        <Title order={2}>
          <Text color="orange" inherit>
            Danh mục nổi bật
          </Text>
        </Title>

        <Button
          component={Link}
          to="/all-categories"
          variant="light"
          leftIcon={<List size={16} />}
          radius="md"
        >
          Xem tất cả
        </Button>
      </Group>

      {/* Content */}
      <Grid>
        {/* Loading */}
        {isLoading &&
          Array(8)
            .fill(0)
            .map((_, index) => (
              <Grid.Col key={index} span={6} sm={4} md={3}>
                <Skeleton height={80} radius="md" />
              </Grid.Col>
            ))}

        {/* Error */}
        {isError && <Text color="red">Không thể tải danh mục</Text>}

        {/* Data */}
        {categoryResponses?.content
          .slice(0, 8) // lấy 8 danh mục đầu
          .map((category) => {
            const CategoryIcon =
              PageConfigs.categorySlugIconMap[category.categorySlug];

            return (
              <Grid.Col key={category.categorySlug} span={6} sm={4} md={3}>
                <Card
                  radius="md"
                  shadow="sm"
                  p="lg"
                  component={Link}
                  to={"/category/" + category.categorySlug}
                  sx={{
                    transition: "0.2s",
                    "&:hover": {
                      transform: "translateY(-5px)",
                      backgroundColor:
                        theme.colorScheme === "dark"
                          ? theme.colors.dark[5]
                          : theme.colors.gray[0],
                    },
                  }}
                >
                  <Group>
                    {CategoryIcon && <CategoryIcon size={45} strokeWidth={1} />}
                    <Text weight={500}>{category.categoryName}</Text>
                  </Group>
                </Card>
              </Grid.Col>
            );
          })}
      </Grid>
    </Stack>
  );
}

export default ClientHomeFeaturedCategories;
