import { onMounted, reactive, ref, toRefs } from 'vue';

import { BasePageParams, Result } from './types';

// TODO 泛型
export default function usePage(getData: (pageParams: BasePageParams) => Promise<Result<any>>) {
  const loading = ref(false);

  const pageParams: BasePageParams = reactive({
    page: 1,
    size: 10,
  });

  const result: Result<any> = reactive({
    list: [],
    total: 0,
  });

  const getList = async () => {
    try {
      loading.value = true;
      const { list, total } = await getData(pageParams);
      result.list = list;
      result.total = total;
    } catch (error) {
      console.error(error);
    } finally {
      loading.value = false;
    }
  };
  // 先执行一次
  onMounted(getList);

  const onShowSizeChange = (current: number, pageSize: number) => {
    pageParams.size = pageSize;
    getList();
  };

  const onSearch = () => {
    pageParams.page = 1;
    getList();
  };

  return {
    ...toRefs({
      list: result.list,
      loading,
      onSearch,
      onShowSizeChange,
      paginationProps: {
        current: pageParams.page,
        total: result.total,
        defaultPageSize: 10,
        showTotal: (total: number) => `共 ${total} 条数据`,
        pageSizeOptions: ['5', '10', '15', '20'],
        showSizeChanger: true,
        onShowSizeChange,
      },
    }),
  };
}
