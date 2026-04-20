import { Box, Group, Stack, Table, Title, useMantineTheme } from '@mantine/core';
import { ShieldCheck } from 'tabler-icons-react';
import React from 'react';
import { ClientProductResponse } from 'types';

interface ClientProductGuaranteeProps {
  product: ClientProductResponse;
}

function ClientProductGuarantee({ product }: ClientProductGuaranteeProps) {
  const theme = useMantineTheme();
  console.log(product);

  return (
    <Stack>
      <Group spacing="xs">
       <ShieldCheck/>
        <Title order={2}>Thông tin bảo hành</Title>
      </Group>
      <Box
        sx={{
          border: `1px solid ${theme.colorScheme === 'dark' ? theme.colors.dark[4] : theme.colors.gray[3]}`,
          borderRadius: theme.radius.md,
          [theme.fn.largerThan('md')]: { width: 500 },
        }}
      >
        <Table>
          <thead>
            <tr>
              <th>Tên bảo hành</th>
              <th>Mô tả chi tiết</th>
            </tr>
          </thead>
          <tbody>
              <tr >
                <td>{product?.guaranteeName}</td>
                <td>{product?.guaranteeDescription}</td>
              </tr>
          </tbody>
        </Table>
      </Box>
    </Stack>
  );
}

export default ClientProductGuarantee;
