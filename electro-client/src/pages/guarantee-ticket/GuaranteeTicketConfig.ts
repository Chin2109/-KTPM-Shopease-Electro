import ManagerPath from "constants/ManagerPath";
import ResourceURL from "constants/ResourceURL";
import PageConfigs from "pages/PageConfigs";
import ProductConfigs from "pages/product/ProductConfigs";
import {
  Configs,
  EntityPropertySchema,
  EntityPropertyType,
  TitleLink,
} from "types";
import MessageUtils from "utils/MessageUtils";
import { z } from "zod";

class GuaranteeTicketConfigs extends Configs {
  static managerPath = ManagerPath.GUARANTEE_TICKET;
  static resourceUrl = ResourceURL.GUARANTEE_TICKET;
  static resourceKey = "guarantees";
  static createTitle = "Thêm phiếu bảo hành";
  static updateTitle = "Cập nhật phiếu bảo hành";
  static manageTitle = "Quản lý phiếu bảo hành";

  static manageTitleLinks: TitleLink[] = ProductConfigs.manageTitleLinks;

  protected static _rawProperties = {
    ...PageConfigs.getProperties(true, true, true),
    name: {
      label: "Tên phiếu bảo hành",
      type: EntityPropertyType.STRING,
      isShowInTable: true,
    },
    description: {
      label: "Mô tả tình trạng bảo hành",
      type: EntityPropertyType.STRING,
    },
    status: {
      label: "Trạng thái bảo hành",
      type: EntityPropertyType.NUMBER,
      isShowInTable: true,
    },
  };

  static properties =
    GuaranteeTicketConfigs._rawProperties as EntityPropertySchema<
      typeof GuaranteeTicketConfigs._rawProperties &
        typeof PageConfigs.properties
    >;

  static initialCreateUpdateFormValues = {
    name: "",
    description: "",
    status: "1",
  };

  static createUpdateFormSchema = z.object({
    name: z
      .string()
      .min(
        2,
        MessageUtils.min(GuaranteeTicketConfigs.properties.name.label, 2),
      ),
    description: z.string(),
    status: z.string(),
  });
}

export default GuaranteeTicketConfigs;
