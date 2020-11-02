<template>
  <a-row type="flex" justify="center" align="middle" id="login-container">
    <a-col :xs="24" :sm="14" :md="12" :lg="8" :xl="6">
      <a-card title="Login">
        <a-form>
          <a-form-item v-bind="validateInfos.username">
            <a-input v-model:value="loginRef.username" placeholder="Username">
              <template v-slot:prefix>
                <UserOutlined style="color:rgba(0,0,0,.25)" />
              </template>
            </a-input>
          </a-form-item>
          <a-form-item v-bind="validateInfos.password">
            <a-input
              v-model:value="loginRef.password"
              type="password"
              placeholder="Password"
            >
              <template v-slot:prefix>
                <LockOutlined style="color:rgba(0,0,0,.25)" />
              </template>
            </a-input>
          </a-form-item>
          <a-form-item>
            <a-button :loading="loading" type="primary" block @click="onSubmit">
              Login
            </a-button>
          </a-form-item>
        </a-form>
      </a-card>
    </a-col>
  </a-row>
</template>

<script lang="ts">
import { reactive, toRaw, defineComponent, ref } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

import { UserOutlined, LockOutlined } from '@ant-design/icons-vue';
import { useForm } from '@ant-design-vue/use';
import { message } from 'ant-design-vue';

import { FormRules } from '@/utils/types';
import { LoginRequest } from '@/api/login';

export default defineComponent({
  name: 'Login',
  components: {
    UserOutlined,
    LockOutlined,
  },
  setup() {
    const loading = ref(false);
    const loginRef: LoginRequest = reactive({
      username: 'joey1234',
      password: '123456',
    });
    const rulesRef: FormRules = reactive({
      username: [
        {
          required: true,
          min: 5,
          max: 20,
          message: 'Please  input correct name!',
        },
      ],
      password: [
        {
          required: true,
          min: 5,
          max: 20,
          message: 'Please input correct  password!',
        },
      ],
    });
    const { validate, validateInfos } = useForm(loginRef, rulesRef);
    const store = useStore();
    const router = useRouter();

    const onSubmit = async (e: MouseEvent) => {
      try {
        loading.value = true;
        e.preventDefault();
        await validate();
        const loginRequest = toRaw(loginRef);
        await store.dispatch('login', loginRequest);
        message.success('登录成功!');
        router.push('/');
      } catch (error) {
        console.error('login error', error);
      } finally {
        loading.value = false;
      }
    };
    return {
      validateInfos,
      loginRef,
      onSubmit,
      loading,
    };
  },
});
</script>

<style lang="scss" scoped>
#login-container {
  min-height: 100vh;
  padding-bottom: 20vh;
}
</style>
