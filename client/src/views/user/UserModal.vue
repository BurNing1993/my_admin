<template>
  <a-modal v-bind="$attrs" @ok="handleOk">
    <p>Some contents...</p>
    <p>Some contents...</p>
    <p>Some contents...</p>
  </a-modal>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

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
    const handleOk = () => {
      console.log('handleOk');
    };
    return {
      handleOk,
    };
  },
});
</script>

<style lang="scss" scoped>
</style>
