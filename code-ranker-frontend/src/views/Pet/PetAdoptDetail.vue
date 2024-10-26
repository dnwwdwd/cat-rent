<template>
  <div style="max-width: 80%; display: flex; justify-content: center; align-items: center; margin: auto; padding: 18px">
    <div style="width: 200px">
      <a-image
          :width="200"
          :height="270"
          :src="petVO.avatarUrl"
          style="border-radius: 6%"
      />
    </div>
    <div style="width: 60%;height: 300px; margin: 18px; border: 1px solid #e8e8e8;
    border-radius: 5px; background-color: #f9f9f9; box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);">
        <div style="padding: 12px">
          <span style="font-size: 27px">姓名：{{petVO.petName}}</span>
          <span style="margin-left: 10px; font-size: 15px">{{petVO.sex}}</span>
          <span style="margin-left: 10px; font-size: 15px">{{petVO.age}} 周岁</span>
          <span style="margin-left: 10px; font-size: 15px">捐赠人：{{petVO.donateUser.nickname}}</span>
        </div>
        <a-divider />
      <div style="padding: 10px">
        <h3 style="font-size: 18px">状态：{{petVO.state}}</h3>
        <h3 style="font-size: 18px">描述：{{petVO.description}}</h3>
        <h3 style="font-size: 18px">品种：{{petVO.variety}}</h3>
        <h3 style="font-size: 18px">是否绝育：{{petVO.isSterilized}}</h3>
        <h3 style="font-size: 18px">是否接种疫苗：{{petVO.isVaccination}}</h3>
        <div style="display: flex; justify-content: space-between; align-items: center;">
          <h3 style="font-size: 18px">领养人：{{petVO.adoptUser.nickname}}</h3>
          <a-button style="color: lightgreen">已领养</a-button>
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
  donateUser: {},
  adoptUser: {}
});

const formModal = ref({
  reason: '',
  id: id,
  advantage: '',
});

onMounted(async () => {
  const res = await myAxios.get(`/adopt/detail/info/${id}`);
  if (res.data) {
    petVO.value = res.data;
  }
});

const showModal = () => {
  open.value = true;
};

const handleOk = async () => {
  alert(formModal.value)
  console.log(formModal.value)
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