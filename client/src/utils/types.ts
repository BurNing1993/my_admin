import { ValidationRule } from 'ant-design-vue/types/form/form';

// 表单验证type
export interface FormRules {
  [key: string]: ValidationRule[];
}
