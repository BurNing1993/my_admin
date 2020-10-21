<template>
  <a-layout-header class="navbar">
    <menu-unfold-outlined
      v-if="collapsed"
      class="trigger"
      @click="toggleCollapsed"
    />
    <menu-fold-outlined v-else class="trigger" @click="toggleCollapsed" />
    <div class="tools">
      <a-dropdown class="avatar">
        <a-avatar>
          <template v-slot:icon><UserOutlined /></template>
        </a-avatar>
        <template v-slot:overlay>
          <a-menu>
            <a-menu-item>
              <router-link to="/">Home</router-link>
            </a-menu-item>
            <a-menu-item @click="logout">
              logout
            </a-menu-item>
          </a-menu>
        </template>
      </a-dropdown>
    </div>
  </a-layout-header>
</template>

<script lang="ts">
import { computed, defineComponent } from 'vue';
import {
  MenuUnfoldOutlined,
  MenuFoldOutlined,
  UserOutlined,
} from '@ant-design/icons-vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
export default defineComponent({
  name: 'Navbar',
  components: {
    MenuUnfoldOutlined,
    MenuFoldOutlined,
    UserOutlined,
  },
  setup() {
    const store = useStore();
    const router = useRouter();

    const logout = () => {
      console.log('logout');
      router.replace('/login');
    };

    return {
      collapsed: computed(() => store.state.app.collapsed),
      toggleCollapsed: () => store.commit('TOGGLE_COLLAPSE'),
      logout,
    };
  },
});
</script>

<style lang="scss" scoped>
.navbar {
  background: #fff;
  padding: 0;
  display: flex;
  align-items: center;
  .trigger {
    font-size: 18px;
    line-height: 64px;
    padding: 0 24px;
    cursor: pointer;
    transition: color 0.3s;
    &::hover {
      color: #1890ff;
    }
  }
  .tools {
    margin-left: auto;
    .avatar {
      margin: 0 24px;
      cursor: pointer;
    }
  }
}
</style>
