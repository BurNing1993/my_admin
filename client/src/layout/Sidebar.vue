<template>
  <a-layout-sider v-model:collapsed="collapsed" :trigger="null" collapsible>
    <div class="logo" />
    <a-menu theme="dark" mode="inline" v-model:selectedKeys="selectedKeys">
      <a-menu-item key="/">
        <router-link to="/">
          <HomeOutlined />
          <span>Home</span>
        </router-link>
      </a-menu-item>
      <a-menu-item key="/user">
        <router-link to="/user">
          <user-outlined />
          <span>User</span>
        </router-link>
      </a-menu-item>
      <a-menu-item key="/role">
        <router-link to="/role">
          <GoldOutlined />
          <span>Role</span>
        </router-link>
      </a-menu-item>
    </a-menu>
  </a-layout-sider>
</template>

<script lang="ts">
import { computed, defineComponent, ref, watch } from 'vue';
import { UserOutlined, HomeOutlined, GoldOutlined } from '@ant-design/icons-vue';
import { useStore } from 'vuex';
import { useRoute } from 'vue-router';
export default defineComponent({
  name: 'Sidebar',
  components: {
    UserOutlined,
    HomeOutlined,
    GoldOutlined,
  },
  setup() {
    const store = useStore();
    const route = useRoute();
    const selectedKeys = ref([route.path]);
    watch(() => route.path, (path) => { selectedKeys.value = [path]; });
    return {
      selectedKeys,
      collapsed: computed(() => store.state.app.collapsed),
    };
  },
});
</script>

<style lang="scss" scoped>
.logo {
  height: 32px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px;
}
</style>
