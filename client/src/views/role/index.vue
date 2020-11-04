<template>
  <a-table
    row-key="id"
    :columns="columns"
    :data-source="list"
    :loading="loading"
    :pagination="false"
  >
  </a-table>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue';
import { getRoleList, Role } from '@/api/role';
import { ColumnProps } from 'ant-design-vue/es/table/interface';

export default defineComponent({
  name: 'Role',
  setup() {
    const columns: ColumnProps[] = [
      {
        title: 'RoleName',
        dataIndex: 'roleName',
        key: 'roleName',
      },
      {
        title: 'RoleDesc',
        dataIndex: 'roleDesc',
        key: 'roleDesc',
      },
    ];
    const loading = ref(false);
    const list = ref<Role[]>([]);

    const getRoleData = async (params?: unknown) => {
      try {
        loading.value = true;
        const { data: roleList } = await getRoleList(params);
        list.value = roleList;
      } catch (error) {
        console.error(error);
      } finally {
        loading.value = false;
      }
    };

    onMounted(() => {
      getRoleData();
    });
    return {
      loading,
      list,
      columns,
    };
  },
});
</script>

<style lang="scss" scoped>
</style>
