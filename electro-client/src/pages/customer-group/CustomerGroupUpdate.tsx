
import React from "react";
import {
  Button,
  ColorInput,
  Divider,
  Grid,
  Group,
  NumberInput,
  Paper,
  Select,
  Stack,
  TextInput,
} from "@mantine/core";
import { CreateUpdateTitle, DefaultPropertyPanel } from "components";
import CustomerGroupConfigs from "pages/customer-group/CustomerGroupConfigs";
import useCustomerGroupUpdateViewModel from "pages/customer-group/CustomerGroupUpdate.vm";
import { useParams } from "react-router-dom";

function CustomerGroupUpdate() {
  const { id } = useParams();
  const { customerGroup, form, handleFormSubmit, statusSelectList } =
    useCustomerGroupUpdateViewModel(Number(id));

  if (!customerGroup) {
    return null;
  }

  return (
    <Stack sx={{ maxWidth: 800 }}>
      <CreateUpdateTitle
        managerPath={CustomerGroupConfigs.managerPath}
        title={CustomerGroupConfigs.updateTitle}
      />

      <DefaultPropertyPanel
        id={customerGroup.id}
        createdAt={customerGroup.createdAt}
        updatedAt={customerGroup.updatedAt}
        createdBy="1"
        updatedBy="1"
      />

      <form onSubmit={handleFormSubmit}>
        <Paper shadow="xs">
          <Stack spacing={0}>
            <Grid p="sm">
              <Grid.Col xs={6}>
                <TextInput
                  required
                  label={CustomerGroupConfigs.properties.code.label}
                  {...form.getInputProps("code")}
                />
              </Grid.Col>
              <Grid.Col xs={6}>
                <TextInput
                  required
                  label={CustomerGroupConfigs.properties.name.label}
                  {...form.getInputProps("name")}
                />
              </Grid.Col>
              <Grid.Col>
                <TextInput
                  required
                  label={CustomerGroupConfigs.properties.description.label}
                  {...form.getInputProps("description")}
                />
              </Grid.Col>
              <Grid.Col xs={6}>
                <ColorInput
                  required
                  label={CustomerGroupConfigs.properties.color.label}
                  {...form.getInputProps("color")}
                  placeholder="Chọn màu"
                />
              </Grid.Col>
              <Grid.Col xs={6}>
                <Select
                  required
                  label={CustomerGroupConfigs.properties.status.label}
                  placeholder="--"
                  data={statusSelectList}
                  {...form.getInputProps("status")}
                />
              </Grid.Col>
              <Grid.Col xs={6}>
                <NumberInput
                  label={CustomerGroupConfigs.properties.minRewardPoint.label}
                  placeholder="0"
                  {...form.getInputProps("minRewardPoint")}
                />
              </Grid.Col>

              <Grid.Col xs={6}>
                <NumberInput
                  label={CustomerGroupConfigs.properties.discountPercent.label}
                  placeholder="0"
                  precision={2} // Cho phép nhập số thập phân (BigDecimal)
                  step={0.1}
                  {...form.getInputProps("discountPercent")}
                />
              </Grid.Col>
            </Grid>

            <Divider mt="xs" />

            <Group position="apart" p="sm">
              <Button variant="default" onClick={form.reset}>
                Mặc định
              </Button>
              <Button type="submit">Cập nhật</Button>
            </Group>
          </Stack>
        </Paper>
      </form>
    </Stack>
  );
}

export default CustomerGroupUpdate;
