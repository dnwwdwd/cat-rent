<template>
  <div style="max-width: 80%; display: flex; justify-content: center; align-items: center; margin: auto; padding: 18px">
    <div style="width: 200px">
      <a-image
          :width="210"
          :height="320"
          :src="petVO.avatarUrl"
          style="border-radius: 6%"
      />
    </div>
    <div style="width: 60%;height: 340px; margin: 18px; border: 1px solid #e8e8e8;
    border-radius: 5px; background-color: #f9f9f9; box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);">
        <div style="padding: 12px">
          <span style="font-size: 27px">姓名：{{petVO.petName}}</span>
          <span style="margin-left: 10px; font-size: 15px">{{petVO.sex}}</span>
          <span style="margin-left: 10px; font-size: 15px">{{petVO.age}} 周岁</span>
        </div>
        <a-divider />
      <div style="padding: 10px">
        <h3 style="font-size: 18px">状态：{{petVO.state}}</h3>
        <h3 style="font-size: 18px">捐赠人：{{petVO.user.nickname}}</h3>
        <h3 style="font-size: 18px">描述：{{petVO.description}}</h3>
        <h3 style="font-size: 18px">品种：{{petVO.variety}}</h3>
        <h3 style="font-size: 18px">是否绝育：{{petVO.isSterilized === 0 ? '否' : '是'}}</h3>
        <h3 style="font-size: 18px">是否接种疫苗：{{petVO.isVaccination === 0 ? '否' : '是'}}</h3>
        <div style="display: flex; justify-content: space-between; align-items: center;">
          <h3 style="font-size: 18px">联系电话：{{petVO.user.phone}}</h3>
          <a-button style="color: lightgreen" @click="showModal">申请领养</a-button>
          <a-modal v-model:open="open" title="申请领养" :confirm-loading="confirmLoading" @ok="handleOk">
            申请理由：<a-input v-model:value="formModal.reason" class="a-input"/>
            你觉得自己的优势是什么：<a-input v-model:value="formModal.advantage"  class="a-input"/>
          </a-modal>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="js">
import {onMounted, ref} from "vue";
import myAxios from "../../plugins/myAxios.js";
import {useRoute} from "vue-router";

const open = ref(false);
const confirmLoading = ref(false);

const route = useRoute();

const id = route.params.id;

const petVO = ref({
  user: {}
});

const formModal = ref({
  reason: '',
  id: id,
  advantage: '',
});

onMounted(async () => {
  const res = await myAxios.get(`/donate/detail/${id}`);
  if (res.data) {
    petVO.value = res.data;
  }
});

const showModal = () => {
  open.value = true;
};

const handleOk = async () => {
  const res = await myAxios.post('/adopt/pet', {
    id: formModal.value.id,
  });
  if (res.code === 0) {
    open.value = false;
    formModal.value.reason = '';
    formModal.value.advantage = '';
  }
};
</script>

<style scoped>
.a-input {
  margin-bottom: 10px;
}
</style>