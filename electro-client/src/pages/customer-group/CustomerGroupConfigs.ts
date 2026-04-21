import ManagerPath from "constants/ManagerPath";
import ResourceURL from "constants/ResourceURL";
import CustomerConfigs from "pages/customer/CustomerConfigs";
import PageConfigs from "pages/PageConfigs";
import {
  Configs,
  EntityPropertySchema,
  EntityPropertyType,
  TitleLink,
} from "types";
import MessageUtils from "utils/MessageUtils";
import { z } from "zod";

class CustomerGroupConfigs extends Configs {
  static managerPath = ManagerPath.CUSTOMER_GROUP;
  static resourceUrl = ResourceURL.CUSTOMER_GROUP;
  static resourceKey = "customer-groups";
  static createTitle = "Thêm nhóm khách hàng";
  static updateTitle = "Cập nhật nhóm khách hàng";
  static manageTitle = "Quản lý nhóm khách hàng";

  static manageTitleLinks: TitleLink[] = CustomerConfigs.manageTitleLinks;

  protected static _rawProperties = {
    ...PageConfigs.getProperties(true),
    code: {
      label: "Mã nhóm khách hàng",
      type: EntityPropertyType.STRING,
      isShowInTable: true,
    },
    name: {
      label: "Tên nhóm khách hàng",
      type: EntityPropertyType.STRING,
      isShowInTable: true,
    },
    description: {
      label: "Mô tả nhóm khách hàng",
      type: EntityPropertyType.STRING,
    },
    color: {
      label: "Màu nhóm khách hàng",
      type: EntityPropertyType.STRING,
      isShowInTable: true,
    },
    status: {
      label: "Trạng thái nhóm khách hàng",
      type: EntityPropertyType.NUMBER,
      isShowInTable: true,
    },
    minRewardPoint: {
      label: "Điểm thưởng tối thiểu",
      type: EntityPropertyType.NUMBER,
      isShowInTable: false,
    },
    discountPercent: {
      label: "Phần trăm giảm giá",
      type: EntityPropertyType.NUMBER,
      isShowInTable: false,
    },
  };

  static properties =
    CustomerGroupConfigs._rawProperties as EntityPropertySchema<
      typeof CustomerGroupConfigs._rawProperties & typeof PageConfigs.properties
    >;

  static initialCreateUpdateFormValues = {
    code: "",
    name: "",
    description: "",
    color: "",
    status: "1",
    minRewardPoint: 0,
    discountPercent: 0,
  };

  static createUpdateFormSchema = z.object({
    code: z.string(),
    name: z
      .string()
      .min(2, MessageUtils.min(CustomerGroupConfigs.properties.name.label, 2)),
    description: z.string(),
    color: z.string(),
    status: z.string(),
    minRewardPoint: z.preprocess((val) => Number(val) || 0, z.number()),
    discountPercent: z.preprocess((val) => Number(val) || 0, z.number()),
  });
}

export default CustomerGroupConfigs;
