<template>
  <div style="max-width: 80%">
    <a-flex wrap="wrap" gap="small">
      <a-card hoverable style="width: 220px;" v-for="car in carList" @click="router.push(`/car/rent/detail/${car.id}`)">
        <template #cover>
          <img
              alt="汽车"
              :src="car.imgUrl"
              style="height: 180px"
          />
        </template>
        <a-card-meta :title="`${car.brand} ${car.type}`">
          <template #description>
            <div v-html="`成色：${car.quality} <br/> 颜色：${car.color}
                <br/>日租价格：${car.rentPrice} <br/> 库存：${car.stock}`"/>
          </template>
        </a-card-meta>
      </a-card>
    </a-flex>
  </div>
</template>

<script setup lang="js">
import {onMounted, ref} from "vue";
import myAxios from "../../plugins/myAxios.js";
import {useRoute, useRouter} from "vue-router";

const route = useRoute();
const router = useRouter();

const id = route.params.id;

const carList = ref({});

onMounted(async () => {
  const res = await myAxios.get(`/car/list/${id}`);
  if (res.data) {
    carList.value = res.data;
  }
});

</script>

<style scoped>
.a-input {
  margin-bottom: 10px;
}
</style>