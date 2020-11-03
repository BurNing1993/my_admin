<template>
  <a-modal v-model:visible="visible" title="title" @ok="handleOk">
    <p>Some contents...</p>
    <p>Some contents...</p>
    <p>Some contents...</p>
  </a-modal>
</template>

<script lang="ts">
import { defineComponent, ref, toRef, toRefs } from 'vue';

class User {
  id?: string;
  username: string;
  nickname: string;
  email: string;
  cellphone: string;
  roleIds: number[];
  constructor() {
    this.username = '';
    this.nickname = '';
    this.email = '';
    this.cellphone = '';
    this.roleIds = [];
  }
}

const initForm = () => new User();

export default defineComponent({
  name: 'UserModal',
  props: {
    op: {
      type: String,
      default: 'detail',
    },
  },
  setup(props) {
    console.log(props);
    const visible = ref(false);

    const show = () => {
      console.log('UserModal show');
      visible.value = true;
    };

    const handleOk = () => {
      console.log('handleOk');
    };
    return {
      show,
      visible,
      handleOk,
    };
  },
});
</script>

<style lang="scss" scoped>
</style>
