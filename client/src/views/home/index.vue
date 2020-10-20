<template>
  <ul>
    <li>name:{{ name }}</li>
    <li>roles:{{ roles }}</li>
  </ul>
</template>

<script lang="ts">
import { computed, defineComponent } from 'vue';
import { useStore } from 'vuex';

export default defineComponent({
  name: 'Home',
  setup() {
    const store = useStore();
    return {
      name: computed(() => store.state.user.name),
      roles: computed(() => store.state.user.roles),
    };
  },
});
</script>

<style lang="scss" scoped></style>
