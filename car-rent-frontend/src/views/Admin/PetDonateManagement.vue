<template>
  <a-button type="primary" class="editable-add-btn" style="margin-bottom: 8px; margin-top: 12px" @click="handleAdd">添加宠物捐赠</a-button>
  <a-modal v-model:open="open" title="添加宠物捐赠" @ok="handleOk" cancelText="取消" okText="确认添加">
    宠物名称：<a-input v-model:value="formModal.petName" class="a-input"/>
    年龄：<a-input v-model:value="formModal.age" class="a-input"/>
    性别：<a-input v-model:value="formModal.sex" class="a-input"/>
    描述：<a-input v-model:value="formModal.description" class="a-input"/>
    品种：<a-input v-model:value="formModal.variety" class="a-input"/>
    图片：<a-input v-model:value="formModal.avatarUrl" class="a-input"/>
    状态：<a-input v-model:value="formModal.state" class="a-input"/>
    <div style="margin-top: 10px; margin-bottom: 10px">
      是否绝育：
      <a-select
          v-model:value="formModal.isSterilized"
          style="width: 115px"
      >
        <a-select-option value="0">未绝育</a-select-option>
        <a-select-option value="1">已绝育</a-select-option>
      </a-select>
      是否接种疫苗：
      <a-select
          v-model:value="formModal.isVaccination"
          style="width: 115px"
      >
        <a-select-option value="0">未接种</a-select-option>
        <a-select-option value="1">已接种</a-select-option>
      </a-select>
    </div>
    宠物类型：
    <a-select
        v-model:value="formModal.type"
        style="width: 115px"
    >
      <a-select-option value="0">猫</a-select-option>
      <a-select-option value="1">狗</a-select-option>
      <a-select-option value="2">仓鼠</a-select-option>
    </a-select>
  </a-modal>
  <a-table :columns="columns" :data-source="dataSource" bordered>
    <template #bodyCell="{ column, text, record }">
      <template v-if="['petName', 'age', 'sex', 'state', 'variety', 'avatarUrl', 'isSterilized', 'isVaccination', 'description', 'type'].includes(column.dataIndex)">
        <div>
          <a-input
              v-if="editableData[record.key]"
              v-model:value="editableData[record.key][column.dataIndex]"
              style="margin: -5px 0"
          />
          <template v-else>
            <template v-if="column.dataIndex === 'avatarUrl'">
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
import { cloneDeep } from 'lodash-es';
import {reactive, ref, computed, onMounted} from 'vue';
import myAxios from "../../plugins/myAxios.js";
import {message} from "ant-design-vue";

// 表格列定义
const columns = [
  {
    title: '宠物名称',
    dataIndex: 'petName',
    width: '10%',
  },
  {
    title: '年龄',
    dataIndex: 'age',
    width: '5%',
  },
  {
    title: '性别',
    dataIndex: 'sex',
    width: '5%',
  },
  {
    title: '状态',
    dataIndex: 'state',
    width: '10%',
  },
  {
    title: '图片',
    dataIndex: 'avatarUrl',
    width: '10%',
  },
  {
    title: '品种',
    dataIndex: 'variety',
    width: '5%',
  },
  {
    title: '捐赠人 ID',
    dataIndex: 'userId',
    width: '10%',
  },
  {
    title: '是否绝育（0 - 未绝育 1 - 已绝育）',
    dataIndex: 'isSterilized',
    width: '10%',
  },
  {
    title: '是否接种疫苗（0 - 未接种 1 - 已接种）',
    dataIndex: 'isVaccination',
    width: '10%',
  },
  {
    title: '描述',
    dataIndex: 'description',
    width: '10%',
  },
  {
    title: '类型',
    dataIndex: 'type',
    width: '10%',
  },
  {
    title: '操作',
    dataIndex: 'operation',
    width: '10%'
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
  const res = await myAxios.post('/donate/update', editedData);
  if (res.code === 0) {
    Object.assign(dataSource.value.find(item => item.key === key), editedData);
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
  const item = dataSource.value.find(item => item.key === key);
  // 请求后端删除数据
  const res = await myAxios.post('/donate/delete', {
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
  petName: '',
  avatarUrl: '',
  age: '',
  sex: '',
  state: '',
  variety: '',
  isSterilized: '',
  isVaccination: '',
  description: '',
  type: '',
});

// 添加表格项
const handleAdd = () => {
  open.value = true;
};

const handleOk = async () => {
    // 请求后端，添加表格项
    const result = await myAxios.post('/donate/pet', formModal.value);
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
  const res = await myAxios.get('/donate/pet/list');
  if (res.code === 0) {
    dataSource.value = res.data.map((item, index) => ({
      ...item,
      key: index,
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
