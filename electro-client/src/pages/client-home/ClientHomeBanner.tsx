import { Box, createStyles, Grid, Group, Stack, Text, useMantineTheme } from '@mantine/core';
import { ClientCarousel } from 'components';
import { Car, HeartHandshake, Stars } from 'tabler-icons-react';
import React from 'react';

const useStyles = createStyles((theme) => ({
  rightBanner: {
    flexWrap: 'unset',
    backgroundColor: theme.colorScheme === 'dark' ? theme.colors.dark[4] : theme.colors.gray[1],
    borderRadius: theme.radius.md,
  },
}));

function ClientHomeBanner() {
  const theme = useMantineTheme();
  const { classes } = useStyles();

  return (
    <Grid>
      <Grid.Col md={7} lg={8}>
        <ClientCarousel>
          <Box
            sx={{
              height: "100%",
              minHeight: 315,
              backgroundSize: "cover",
              backgroundPosition: "center",

              backgroundImage: `
      url("https://static.vecteezy.com/system/resources/previews/002/220/206/non_2x/computer-part-banner-vector.jpg")
    `,
            }}
          ></Box>
          <Box
            sx={{
              height: "100%",
              minHeight: 315,
              backgroundSize: "cover",
              backgroundPosition: "center",
              backgroundImage: `
      url("https://www.phucanh.vn/media/news/1502_laptop-ai-asus-gen2-3.jpg")
    `,
            }}
          ></Box>
        </ClientCarousel>
      </Grid.Col>
      <Grid.Col md={5} lg={4}>
        <Stack style={{ height: "100%" }} justify="space-between">
          {/* Item 1 */}
          <Group
            py="sm"
            style={{ flex: 1 }}
            px="md"
            className={classes.rightBanner}
            align="center"
            spacing="md"
          >
            <Car size={45} strokeWidth={1} />

            <Stack spacing={2}>
              <Text size="md" weight={600}>
                Miễn phí vận chuyển
              </Text>
              <Text size="sm" color="dimmed">
                100% đơn hàng đều được miễn phí vận chuyển.
              </Text>
            </Stack>
          </Group>

          {/* Item 2 */}
          <Group
            py="sm"
            style={{ flex: 1 }}
            px="md"
            className={classes.rightBanner}
            align="center"
            spacing="md"
          >
            <Stars size={45} strokeWidth={1} />

            <Stack spacing={2}>
              <Text size="md" weight={600}>
                Bảo hành tận tâm
              </Text>
              <Text size="sm" color="dimmed">
                Luôn hỗ trợ khách hàng nhanh chóng và tận tình.
              </Text>
            </Stack>
          </Group>

          {/* Item 3 */}
          <Group
            py="sm"
            px="md"
            style={{ flex: 1 }}
            className={classes.rightBanner}
            align="center"
            spacing="md"
          >
            <HeartHandshake size={45} strokeWidth={1} />

            <Stack spacing={2}>
              <Text size="md" weight={600}>
                Thương hiệu uy tín
              </Text>
              <Text size="sm" color="dimmed">
                Cam kết sản phẩm chính hãng, chất lượng hàng đầu.
              </Text>
            </Stack>
          </Group>
        </Stack>
      </Grid.Col>
    </Grid>
  );
}

export default ClientHomeBanner;
