<template>
  <div style="max-width: 80%; display: flex; justify-content: center; align-items: center; margin: auto; padding: 20px">
    <div style="width: 200px; ">
      <a-image
          :width="200"
          :height="270"
          :src="activityVO.imgUrl"
          style="border-radius: 6%"
      />
    </div>
    <div style="width: 60%; height: 300px; margin: 20px; border: 1px solid #e8e8e8;
    border-radius: 5px; background-color: #f9f9f9; box-shadow: 8px 8px 8px rgba(255, 192, 203, 0.5);">
      <div style="padding: 20px">
        <span style="font-size: 27px">{{ activityVO.name }}</span>
        <span style="margin-left: 10px; font-size: 15px">活动时间：{{ activityVO.startTime }} ~ {{ activityVO.endTime }}</span>
      </div>
      <a-divider/>
      <div style="padding: 10px">
        <h3 style="font-size: 20px">描述：{{ activityVO.description }}</h3>
        <h3 style="font-size: 20px">地点：{{ activityVO.address }}</h3>
        <h3 style="font-size: 20px">组织人：{{ activityVO.user.nickname }}</h3>
        <h3 style="font-size: 20px">联系电话：{{ activityVO.user.phone }}</h3>
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

const activityVO = ref({
  user: {}
});

onMounted(async () => {
  const res = await myAxios.get(`/activity/detail/${id}`);
  if (res.data) {
    activityVO.value = res.data;
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