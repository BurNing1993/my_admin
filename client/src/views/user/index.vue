<template>
  <div>
    <!-- search -->
    <div class="search">search</div>
    <!-- table -->
    <a-table
      :columns="columns"
      :data-source="list"
      row-key="id"
      :pagination="paginationProps"
    >
    </a-table>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, ref } from 'vue';
import { getUserPage, User } from '@/api/user';
import useTablePage from '@/utils/useTablePage';
import { ColumnProps } from 'ant-design-vue/types/table/column';
import { BasePageParams } from '@/utils/types';

export default defineComponent({
  name: 'User',
  setup() {
    const columns: ColumnProps<User>[] = [
      {
        dataIndex: 'username',
        key: 'username',
      },
    ];

    const searchForm = reactive({
      name: undefined,
    });

    const getPage = async (pageParams: BasePageParams) => {
      const {
        data: { content, totalElements },
      } = await getUserPage({ ...searchForm, ...pageParams });
      return {
        list: content,
        total: totalElements,
      };
    };
    const { list, loading, paginationProps, getList } = useTablePage(getPage);
    getList();
    return {
      list,
      columns,
      loading,
      paginationProps,
    };
  },
});
</script>

<style lang="scss" scoped></style>
