<template>
  <a-modal v-bind="$attr" @ok="onSubmit">
    <a-form :label-col="{ span: 4 }" :wrapper-col="{ span: 20 }">
      <a-form-item label="username" v-bind="validateInfos.username">
        <a-input
          v-model:value.trim="modelRef.username"
          allowClear
          placeholder="username"
        />
      </a-form-item>
      <a-form-item label="nickname" v-bind="validateInfos.nickname">
        <a-input
          v-model:value.trim="modelRef.nickname"
          allowClear
          placeholder="nickname"
        />
      </a-form-item>
      <a-form-item label="email" v-bind="validateInfos.email">
        <a-input
          v-model:value.trim="modelRef.email"
          allowClear
          placeholder="email"
        />
      </a-form-item>
      <a-form-item label="cellphone" v-bind="validateInfos.cellphone">
        <a-input
          v-model:value.trim="modelRef.cellphone"
          allowClear
          placeholder="cellphone"
        />
      </a-form-item>
      <a-form-item label="roleIds" v-bind="validateInfos.roleIds">
        <a-input
          v-model:value.trim="modelRef.roleIds"
          allowClear
          placeholder="roleIds"
        />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script lang="ts">
import { defineComponent, PropType, reactive, ref, toRaw, watch } from 'vue';
import { useForm } from '@ant-design-vue/use';
import { message } from 'ant-design-vue';

import { addUser } from '@/api/user';

interface User {
  id?: string;
  username: string;
  nickname: string;
  email: string;
  cellphone: string;
  roleIds: number[];
}

export default defineComponent({
  name: 'UserModal',
  props: {
    op: {
      type: String,
      default: 'detail',
    },
    formData: {
      type: Object as PropType<User>,
      // type: Object,
      default: undefined,
    },
  },
  setup(props) {
    let modelRef = reactive<User>({
      username: '',
      nickname: '',
      email: '',
      cellphone: '',
      roleIds: [],
    });

    const rulesRef = reactive({
      username: [
        {
          required: true,
          message: 'Please input username',
        },
      ],
    });
    const { resetFields, validate, validateInfos } = useForm(
      modelRef,
      rulesRef,
    );

    watch(
      () => props.formData,
      (formData) => {
        if (formData) {
          modelRef = reactive({ ...formData });
        } else {
          resetFields();
        }
      },
    );

    const loading = ref(false);

    const onSubmit = async (e: MouseEvent) => {
      try {
        e.preventDefault();
        loading.value = true;
        await validate();
        await addUser(toRaw(modelRef));
        message.success('添加成功!');
      } catch (error) {
        console.log('error', error);
      } finally {
        loading.value = false;
      }
    };

    return {
      validateInfos,
      resetFields,
      modelRef,
      onSubmit,
      loading,
    };
  },
});
</script>

<style lang="scss" scoped>
</style>
