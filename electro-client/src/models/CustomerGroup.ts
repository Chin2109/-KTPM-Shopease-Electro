import BaseResponse from "models/BaseResponse";

export interface CustomerGroupResponse extends BaseResponse {
  code: string;
  name: string;
  description: string;
  color: string;
  status: number;
  minRewardPoint?: number; // Tương ứng với Integer
  discountPercent?: number; // Tương ứng với BigDecimal
}

export interface CustomerGroupRequest {
  code: string;
  name: string;
  description: string;
  color: string;
  status: number;
  minRewardPoint?: number; // Tương ứng với Integer
  discountPercent?: number; // Tương ứng với BigDecimal
}
