<template>
  <div style="max-width: 80%; display: flex; justify-content: center; align-items: center; margin: auto; padding: 20px">
    <div style="width: 320px; ">
      <a-image
          :width="290"
          :height="400"
          :src="newsVO.imgUrl"
          style="border-radius: 6%"
      />
    </div>
    <div style="width: 60%; height: 450px; margin: 20px; border: 1px solid #e8e8e8;
    border-radius: 5px; background-color: #f9f9f9; box-shadow: 8px 8px 8px rgba(173, 216, 230, 0.5);">
      <div style="padding: 20px; display: flex;">
        <a-avatar :src="newsVO.user.userAvatar"/>
        <span style="font-size: 24px; margin-left: 10px">{{ newsVO.title }}</span>
      </div>
      <div style="padding: 10px">
        <h3 style="font-size: 20px;">描述：{{ newsVO.description }}</h3>
        <span style="font-size: 20px;margin-top: 20px;">内容：</span>
        <span style="font-size: 20px; color: black">{{ newsVO.content }}</span>
      </div>
    </div>
  </div>
</template>

<script setup lang="js">
import {onMounted, ref} from "vue";
import myAxios from "../../plugins/myAxios.js";
import {useRoute} from "vue-router";

const route = useRoute();

const id = route.params.id;

const newsVO = ref({
  user: {}
});

onMounted(async () => {
  const res = await myAxios.get(`/news/detail/${id}`);
  if (res.data) {
    newsVO.value = res.data;
  }
});

</script>

<style scoped>
.a-input {
  margin-bottom: 10px;
}
</style>