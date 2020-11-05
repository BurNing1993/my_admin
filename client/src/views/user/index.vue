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

    <UserModal :op="op" :formData="formData" v-model:visible="visible" :title="title" />
  </div>
</template>

<script lang="ts">
import { defineComponent, nextTick, onMounted, reactive, ref, toRefs } from 'vue';
import { getUserPage } from '@/api/user';
import useTablePage from '@/composables/useTablePage';
import { ColumnProps } from 'ant-design-vue/es/table/interface';
import { BasePageParams, Props } from '@/utils/types';
import UserModal from './UserModal.vue';

export default defineComponent({
  name: 'User',
  components: {
    UserModal,
  },
  setup() {
    const columns: Array<ColumnProps & Props> = [
      {
        title: 'Username',
        dataIndex: 'username',
      },
      {
        title: 'NickName',
        dataIndex: 'nickname',
      },
      {
        title: 'Roles',
        dataIndex: 'roles',
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
      formData: undefined,
    });

    const userModal = ref(null);
    const onAddClick = () => {
      state.visible = true;
      state.formData = undefined;
    };
    return {
      ...toRefs(state),
      onAddClick,
      list,
      userModal,
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
