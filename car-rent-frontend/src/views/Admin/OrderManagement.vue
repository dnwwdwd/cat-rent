<template>
  <a-button type="primary" class="editable-add-btn" style="margin-bottom: 8px; margin-top: 12px" @click="handleAdd">
    添加订单
  </a-button>
  <a-modal v-model:open="open" title="添加订单" @ok="handleOk" cancelText="取消" okText="确认添加">
    <div style="display: flex; flex-direction: column;">
      汽车 ID：
      <a-select
          v-model:value="formModal.carId"
          style="width: 200px"
          placeholder="请选择用户"
      >
        <a-select-option v-for="id in carIds" :key="id" :value="id">
          {{ id }}
        </a-select-option>
      </a-select>
      用户 ID：
      <a-select
          v-model:value="formModal.userId"
          style="width: 200px"
          placeholder="请选择用户"
      >
        <a-select-option v-for="id in userIds" :key="id" :value="id">
          {{ id }}
        </a-select-option>
      </a-select>
    </div>
    <div style="margin-top: 8px">
      租赁起始日期和结束日期；
      <a-range-picker
          v-model:value="date"
          :placeholder="['租赁起始日期', '租赁结束日期']"
      />
    </div>
  </a-modal>
  <a-table :columns="columns" :data-source="dataSource" bordered>
    <template #bodyCell="{ column, text, record }">
      <!-- 如果是可编辑字段，则显示编辑框 -->
      <template v-if="['carId', 'userId', 'totalPrice', 'rentalStartDate', 'rentalEndDate'].includes(column.dataIndex)">
        <div>
          <a-input
              v-if="editableData[record.key]"
              v-model:value="editableData[record.key][column.dataIndex]"
              style="margin: -5px 0"
          />
          <template v-else>
            <template v-if="column.dataIndex === 'userAvatar'">
              <a-image :src="text" :height="150" :width="150"/>
            </template>
            <template v-else>
              {{ text }}
            </template>
          </template>
        </div>
      </template>

      <!-- 如果是 operation 字段，显示编辑、删除、允许租赁、结束租赁等操作按钮 -->
      <template v-else-if="column.dataIndex === 'operation'">
        <div class="editable-row-operations">
        <span v-if="editableData[record.key]">
          <a-typography-link @click="save(record.key)">确定</a-typography-link>
          <a-popconfirm title="你确定取消吗？" @confirm="cancel(record.key)" ok-text="确定" cancel-text="取消">
            <a>取消</a>
          </a-popconfirm>
        </span>
          <span v-else>
          <a @click="edit(record.key)">修改</a>
          <a-popconfirm title="你确定删除吗？" @confirm="onDelete(record.key)" ok-text="确定" cancel-text="取消">
            <a>删除</a>
          </a-popconfirm>
            <!-- 新增的“允许租赁”按钮 -->
          <a-button v-if="record.status === 0" @click="allowRental(record)" type="primary" size="small" style="margin-left: 8px;">
            允许租赁
          </a-button>
            <!-- 新增的“结束租赁”按钮 -->
          <a-button v-if="record.status === 1" @click="endRental(record)" type="primary" size="small" style="margin-left: 8px;">
            结束租赁
          </a-button>
        </span>
        </div>
      </template>
    </template>
  </a-table>


</template>

<script setup>
import {cloneDeep} from 'lodash-es';
import {onMounted, reactive, ref} from 'vue';
import myAxios from "../../plugins/myAxios.js";
import {message} from "ant-design-vue";

// 表格列定义
const columns = [
  {
    title: '汽车 ID',
    dataIndex: 'carId',
    width: '10%',
  },
  {
    title: '用户 ID',
    dataIndex: 'userId',
    width: '10%',
  },
  {
    title: '总价',
    dataIndex: 'totalPrice',
    width: '10%',
  },
  {
    title: '租期开始日期',
    dataIndex: 'rentalStartDate',
    width: '10%',
  },
  {
    title: '租期结束日期',
    dataIndex: 'rentalEndDate',
    width: '10%',
  },
  {
    title: '操作',
    dataIndex: 'operation',
    width: '15%',
  },
];

const date = ref();

const carIds = ref([]);
const userIds = ref([]);

// 响应式数据源
const dataSource = ref([]);
const editableData = reactive({});

const open = ref(false);

// 编辑函数，点击编辑按钮时调用
const edit = (key) => {
  editableData[key] = cloneDeep(dataSource.value.find(item => item.key === key));
};

const save = async (key) => {
  // 编辑保存后的新值
  const editedData = editableData[key];
  // 请求后端更新数据
  const res = await myAxios.post('/order/update', editedData);
  if (res.code === 0) {
    loadData();
    message.success('修改成功');
  } else {
    message.error('修改失败');
  }
  delete editableData[key];
};

// 取消函数，点击取消按钮时调用
const cancel = (key) => {
  delete editableData[key];
};

// 删除函数，点击删除按钮时调用
const onDelete = async (key) => {
  console.log(key);
  const item = dataSource.value.find(item => item.key === key);
  console.log(item.id);
  // 请求后端删除数据
  const res = await myAxios.post('/order/delete', {
    id: item.id,
  });
  if (res.code === 0) {
    dataSource.value = dataSource.value.filter(item => item.key !== key);
    message.success('删除成功');
  } else {
    message.error('删除失败');
  }
};

const formModal = ref({
  carId: '',
  userId: '',
});

// 添加表格项
const handleAdd = () => {
  open.value = true;
};

const handleOk = async () => {
  console.log(formModal.value);
  console.log(date.value)
  // 请求后端，添加表格项
  const result = await myAxios.post('/order/add', {
    ...formModal.value,
    rentalStartDate: date.value[0],
    rentalEndDate: date.value[1],
  });
  if (result.code == 0) {
    message.success('添加成功');
    formModal.value.userId = '';
    formModal.value.carId = '';
    date.value = null;
    open.value = false;
  } else {
    message.error('添加失败');
  }
  // 重新加载表格数据
  loadData();
};

const loadData = async () => {
  const res1 = await myAxios.get('/order/list');
  if (res1.code === 0) {
    dataSource.value = res1.data.map((item, index) => ({
      ...item,
      key: index, // 添加key属性，值从0开始递增
    }));
  }
  const res2 = await myAxios.get('/car/list/id');
  if (res2.code === 0) {
    carIds.value = res2.data;
  }
  const res3 = await myAxios.get('/user/list/id');
  if (res3.code === 0) {
    userIds.value = res3.data;
  }
};


onMounted(async () => {
  loadData();
});

const allowRental = async (record) => {
  const res = await myAxios.post('/order/allowRental', {
    id: record.id,
  });
  if (res.code === 0) {
    loadData();
    message.success('操作成功');
  } else {
    message.error('操作失败');
  }
}

const endRental = async (record) => {
  const res = await myAxios.post('/order/endRental', {
    id: record.id,
  });
  if (res.code === 0) {
    loadData();
    message.success('操作成功');
  } else {
    message.error('操作失败');
  }
};

</script>

<style scoped>
.editable-row-operations a {
  margin-right: 8px;
}

.editable-add-btn {
  margin-bottom: 8px;
}
</style>
