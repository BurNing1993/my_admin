<template>
  <a-layout id="layout">
    <Sidebar />
    <a-layout>
      <Navbar />
      <AppMain />
    </a-layout>
  </a-layout>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import Sidebar from './Sidebar.vue';
import Navbar from './Navbar.vue';
import AppMain from './AppMain.vue';

export default defineComponent({
  name: 'Layout',
  components: {
    Sidebar,
    Navbar,
    AppMain,
  },
});
</script>

<style lang="scss" scoped>
#layout{
  min-height: 100vh;
}
</style>
