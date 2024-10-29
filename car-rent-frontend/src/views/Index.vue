<template>
  <div style="display: flex; justify-content: center; align-items: center; margin-bottom: 12px">
    <a-input-search
        v-model:value="searchText"
        placeholder="请输入品牌搜索车辆"
        style="width: 400px;"
        @search="onSearch"/>
    <span style="margin-left: 12px">日租价格：</span>
    <a-input-number
        v-model:value="minPrice"
        :min="0"
        :max="100000"
        :formatter="value => `RMB ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')"
        :parser="value => value.replace(/\$\s?|(,*)/g, '')"
    />
    <div style="margin-left: 10px; margin-right: 10px">~</div>
    <a-input-number
        v-model:value="maxPrice"
        :formatter="value => `RMB ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')"
        :parser="value => value.replace(/\$\s?|(,*)/g, '')"
    />
  </div>
  <div style="max-width: 80%">
    <a-flex wrap="wrap" gap="large">
      <a-card hoverable style="width: 220px;" v-for="carCategory in carCategoryList"
              @click="router.push(`/car/category/${carCategory.id}`)">
        <template #cover>
          <img
              alt="汽车"
              :src="carCategory.imgUrl"
              style="height: 180px"
          />
        </template>
        <a-card-meta :title="carCategory.brand">
          <template #description>
            <div v-html="`型号：${carCategory.type} <br/> 库存：${carCategory.stock} <br/> 均价：${carCategory.averagePrice}`"/>
          </template>
        </a-card-meta>
      </a-card>
    </a-flex>
  </div>
</template>

<script setup lang="js">
import {onMounted, ref} from "vue";
import {useRouter} from "vue-router";
import myAxios from "../plugins/myAxios.js";

const router = useRouter();

const minPrice = ref(null);
const maxPrice = ref(null);
const searchText = ref("");
const carCategoryList = ref([]);

const loadData = async () => {
  const res = await myAxios.post('/car_category/list', {
    searchText: searchText.value,
    minPrice: minPrice.value,
    maxPrice: maxPrice.value,
  });
  if (res.code === 0) {
    carCategoryList.value = res.data;
  }
}

onMounted(async () => {
  loadData();
});

const onSearch = async () => {
  loadData();
}

</script>

<style>

</style>