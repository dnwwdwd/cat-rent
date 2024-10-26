<template>
  <div style="max-width: 80%; display: flex; justify-content: center; align-items: center; margin: auto; padding: 20px">
    <div style="width: 200px; ">
      <a-image
          :width="200"
          :height="270"
          :src="petForumVO.imgUrl"
          style="border-radius: 6%"
      />
    </div>
    <div style="width: 60%; height: 300px; margin: 20px; border: 1px solid #e8e8e8;
    border-radius: 5px; background-color: #f9f9f9; box-shadow: 8px 8px 8px rgba(255, 192, 203, 0.5);">
      <div style="padding: 20px; display: flex;">
        <a-avatar :src="petForumVO.user.avatarUrl" />
        <span style="font-size: 27px; margin-left: 10px">{{ petForumVO.name }}</span>
      </div>
      <a-divider />
      <div style="padding: 10px">
        <h3 style="font-size: 20px">描述：{{ petForumVO.description }}</h3>
        <h3 style="font-size: 20px">发帖人：{{ petForumVO.user.nickname }}</h3>
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

const petForumVO = ref({
  user: {}
});

onMounted(async () => {
  const res = await myAxios.get(`/pet/forum/detail/${id}`);
  if (res.data) {
    petForumVO.value = res.data;
  }
});

</script>

<style scoped>
.a-input {
  margin-bottom: 10px;
}
</style>