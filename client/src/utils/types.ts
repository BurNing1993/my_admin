
// 表单验证type
// export interface FormRules {
//   [key: string]: ValidationRule[];
// }

// 分页基本参数
export interface BasePageParams {
  page: number;
  size: number;
  // [key: string]: any;
}

// 分页响应
export interface PageResponse<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
}

// Result
export interface Result<T> {
  total: number;
  list: T[];
}

// Props
export interface Props {
  [key: string]: any;
}
