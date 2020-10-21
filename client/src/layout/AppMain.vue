<template>
  <a-layout-content class="main">
    <router-view v-slot="{ Component }">
      <transition>
        <keep-alive>
          <component :is="Component" />
        </keep-alive>
      </transition>
    </router-view>
  </a-layout-content>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'AppMain',
  data() {
    return {};
  },
});
</script>

<style lang="scss" scoped>
.main {
  margin: 16px 8px;
  padding: 16px;
  background: #fff;
}
</style>
