<template>
  <a-button type="primary" class="editable-add-btn" style="margin-bottom: 8px; margin-top: 12px" @click="handleAdd">
    添加用户
  </a-button>
  <a-modal v-model:open="open" title="添加用户" @ok="handleOk" cancelText="取消" okText="确认添加">
    账号：
    <a-input v-model:value="formModal.userAccount" class="a-input"/>
    密码：
    <a-input v-model:value="formModal.userPassword" class="a-input"/>
    昵称：
    <a-input v-model:value="formModal.userName" class="a-input"/>
    手机号：
    <a-input v-model:value="formModal.phone" class="a-input"/>
    家庭住址：
    <a-input v-model:value="formModal.address" class="a-input"/>
    头像：
    <a-input v-model:value="formModal.userAvatar" class="a-input"/>
  </a-modal>
  <a-table :columns="columns" :data-source="dataSource" bordered>
    <template #bodyCell="{ column, text, record }">
      <template v-if="['userAccount', 'userPassword' ,'userName', 'userAvatar', 'phone', 'address'].includes(column.dataIndex)">
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
      <!-- 对 operation 字段显示编辑/保存/取消/删除操作 -->
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
    title: '账号',
    dataIndex: 'userAccount',
    width: '10%',
  },
  {
    title: '密码',
    dataIndex: 'userPassword',
    width: '10%',
  },
  {
    title: '昵称',
    dataIndex: 'userName',
    width: '10%',
  },
  {
    title: '手机号',
    dataIndex: 'phone',
    width: '10%',
  },
  {
    title: '家庭住址',
    dataIndex: 'address',
    width: '10%',
  },
  {
    title: '头像',
    dataIndex: 'userAvatar',
    width: '10%',
  },
  {
    title: '操作',
    dataIndex: 'operation',
    width: '15%',
  },
];

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
  const res = await myAxios.post('/user/update', editedData);
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
  const res = await myAxios.post('/user/delete', {
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
  userAccount: '',
  userPassword: '',
  userName: '',
  userAvatar: '',
  phone: '',
  address: '',
});

// 添加表格项
const handleAdd = () => {
  open.value = true;
};

const handleOk = async () => {
  // 请求后端，添加表格项
  const result = await myAxios.post('/user/add', formModal.value);
  if (result.code == 0) {
    message.success('添加成功');
    open.value = false;
  } else {
    message.error('添加失败');
  }
  // 重新加载表格数据
  loadData();
};

const loadData = async () => {
  const res = await myAxios.get('/user/list');
  if (res.code === 0) {
    dataSource.value = res.data.map((item, index) => ({
      ...item,
      key: index, // 添加key属性，值从0开始递增
    }));
  }
};


onMounted(async () => {
  loadData();
});
</script>

<style scoped>
.editable-row-operations a {
  margin-right: 8px;
}

.editable-add-btn {
  margin-bottom: 8px;
}
</style>
