<template>
  <div style="max-width: 80%; display: flex; justify-content: center; align-items: center; margin: auto; padding: 20px">
    <div style="width: 270px; ">
      <a-image
          :width="240"
          :height="300"
          :src="petVO.avatarUrl"
          style="border-radius: 6%"
      />
    </div>
    <div style="width: 60%;height: 300px; margin: 20px; border: 1px solid #e8e8e8;
    border-radius: 5px; background-color: #f9f9f9; box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);">
        <div style="padding: 12px">
          <span style="font-size: 27px">姓名：{{petVO.petName}}</span>
          <span style="margin-left: 10px; font-size: 15px">{{petVO.sex}}</span>
          <span style="margin-left: 10px; font-size: 15px">{{petVO.age}} 周岁</span>
        </div>
        <a-divider />
      <div style="padding: 10px">
        <h3 style="font-size: 20px">状态：{{petVO.state}}</h3>
        <h3 style="font-size: 20px">描述：{{petVO.description}}</h3>
        <h3 style="font-size: 20px">品种：{{petVO.variety}}</h3>
        <h3 style="font-size: 20px">是否绝育：{{petVO.isSterilized}}</h3>
        <h3 style="font-size: 20px">是否接种疫苗：{{petVO.isVaccination}}</h3>
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

const petVO = ref({
  user: {}
});

onMounted(async () => {
  const res = await myAxios.get(`/pet/detail/${id}`);
  if (res.data) {
    petVO.value = res.data;
  }
});
</script>

<style scoped>

</style>