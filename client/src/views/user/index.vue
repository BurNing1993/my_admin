<template>
  <div>
    <!-- search -->
    <div class="search">
      <a-form layout="inline">
        <a-form-item label="Nickname">
          <a-input v-model:value="searchModelRef.nickname" />
        </a-form-item>
        <a-form-item>
          <a-button type="primary" @click="onSearch"> search </a-button>
          <a-button style="margin-left: 10px" @click="onReset">
            Reset
          </a-button>
        </a-form-item>
      </a-form>
    </div>
    <div class="toolbar">
      <a-button type="primary" @click="onAddClick"> ADD </a-button>
    </div>
    <!-- table -->
    <a-table
      :columns="columns"
      :data-source="list"
      :loading="loading"
      row-key="id"
      :pagination="paginationProps"
      bordered
    >
      <template v-slot:roles="{ text: roles }">
        <a-tag v-for="role in roles" :key="role.id" color="blue">
          {{ role.roleName }}
        </a-tag>
      </template>
      <template v-slot:action="{ record }">
        <span>
          <a>Delete {{ record.username }}</a>
        </span>
      </template>
    </a-table>

    <UserModal v-model:visible="visible"  :title="title" :op="op"/>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, toRefs } from 'vue';
import { getUserPage, User } from '@/api/user';
import useTablePage from '@/composables/useTablePage';
import { ColumnProps } from 'ant-design-vue/types/table/column';
import { BasePageParams, Props } from '@/utils/types';
import UserModal from './UserModal.vue';

export default defineComponent({
  name: 'User',
  components: {
    UserModal,
  },
  setup() {
    const columns: ColumnProps<User>[] = [
      {
        title: 'Username',
        dataIndex: 'username',
        key: 'username',
      },
      {
        title: 'NickName',
        dataIndex: 'nickname',
        key: 'nickname',
      },
      {
        title: 'Roles',
        dataIndex: 'roles',
        key: 'roles',
        slots: { customRender: 'roles' },
      },
      {
        title: 'Action',
        key: 'action',
        slots: { customRender: 'action' },
      },
    ];

    const searchModelRef = reactive<Props>({
      nickname: undefined,
    });

    const getPage = async (query: BasePageParams & Props) => {
      const {
        data: { content, totalElements },
      } = await getUserPage(query);
      return {
        list: content,
        total: totalElements,
      };
    };
    const { list, loading, paginationProps, onSearch, onReset } = useTablePage(
      getPage,
      searchModelRef,
    );

    const state = reactive({
      visible: false,
      title: 'User',
      op: 'add',
    });

    const onAddClick = () => {
      state.visible = true;
    };
    return {
      ...toRefs(state),
      onAddClick,
      list,
      columns,
      loading,
      paginationProps,
      searchModelRef,
      onReset,
      onSearch,
    };
  },
});
</script>

<style lang="scss" scoped></style>
