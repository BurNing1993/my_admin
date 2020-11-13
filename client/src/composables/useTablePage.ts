import { onMounted, reactive, ref, toRaw, toRef } from 'vue';
import { useForm } from '@ant-design-vue/use';

import { BasePageParams, Result, Props } from '../utils/types';

export default function usePage<T>(getData: (pageParams: BasePageParams) => Promise<Result<T>>, modelRef: Props) {
  const loading = ref(false);

  const pageParams: BasePageParams = reactive({
    page: 1,
    size: 10,
  });

  const result: Result<T> = reactive({
    list: [],
    total: 0,
  });

  // TODO rulesRef 为null报错
  const { resetFields } = useForm(modelRef, {});

  const getList = async () => {
    try {
      loading.value = true;
      const { list, total } = await getData({ ...toRaw(modelRef), ...toRaw(pageParams) });
      result.list = list;
      result.total = total;
    } catch (error) {
      console.error(error);
    } finally {
      loading.value = false;
    }
  };
  // 先执行一次
  onMounted(() => {
    getList();
  });

  const onShowSizeChange = (_current: number, pageSize: number) => {
    pageParams.size = pageSize;
    getList();
  };

  const onChange = (page: number, _pageSize: number) => {
    pageParams.page = page;
    getList();
  };

  const onSearch = () => {
    pageParams.page = 1;
    getList();
  };

  const onReset = () => {
    resetFields();
    onSearch();
  };

  return {
    list: toRef(result, 'list'),
    loading,
    onSearch,
    onReset,
    paginationProps: {
      current: pageParams.page,
      total: toRef(result, 'total'),
      defaultPageSize: 10,
      showTotal: (total: number) => `共 ${total} 条数据`,
      pageSizeOptions: ['5', '10', '15', '20'],
      showSizeChanger: true,
      onShowSizeChange,
      onChange,
    },
  };
}
