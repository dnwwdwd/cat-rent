<template>
  <div style="max-width: 85%; display: flex; justify-content: center; align-items: center; margin: auto; padding: 18px">
    <div style="width: 240px">
      <a-image
          :width="240"
          :height="300"
          :src="carVO.imgUrl"
          style="border-radius: 6%"
      />
    </div>
    <div style="width: 40%;height: 310px; margin-left: 28px; border: 1px solid #e8e8e8;
    border-radius: 5px; background-color: #f9f9f9; box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);">
      <div style="padding: 12px">
        <span style="font-size: 27px">品牌：{{ carVO.brand }}</span>
        <span style="margin-left: 10px; font-size: 15px">型号：{{ carVO.type }}</span>
      </div>
      <a-divider/>
      <div style="padding: 10px">
        <h3 style="font-size: 18px">成色：{{ carVO.quality }}</h3>
        <h3 style="font-size: 18px">单日租车价格：{{ carVO.rentPrice }}</h3>
        <h3 style="font-size: 18px">车牌号：{{ carVO.carNumber }}</h3>
        <h3 style="font-size: 18px">里程数：{{ carVO.mileage }}</h3>
        <h3 style="font-size: 18px">颜色：{{ carVO.color }}</h3>
        <div style="display: flex; justify-content: space-between; align-items: center;">
          <h3 style="font-size: 18px">是否已出租：{{ carVO.status === 0 ? '是' : '否' }}</h3>
          <a-button style="color: #1E90FF" @click="showModal">租车</a-button>
          <a-modal v-model:open="open" title="申请领养" :confirm-loading="confirmLoading" @ok="handleOk" ok-text="确认"
                   cancel-text="取消">
            联系方式：
            <a-input v-model:value="formModal.phone" placeholder="请输入您的联系方式" class="a-input"/>
            家庭住址：
            <a-input v-model:value="formModal.address" placeholder="请输入你的家庭住址" class="a-input"/>
            租赁起始日期和结束日期；
            <a-range-picker
                v-model:value="date"
                :placeholder="['租赁起始日期', '租赁结束日期']"
            />
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

const date = ref();

const route = useRoute();

const id = route.params.id;

const carVO = ref({});

const formModal = ref({
  phone: '',
  address: '',
});

onMounted(async () => {
  const res = await myAxios.get(`/car/detail/${id}`);
  if (res.data) {
    carVO.value = res.data;
  }
});

const showModal = () => {
  open.value = true;
};

const handleOk = async () => {
  const res = await myAxios.post('/order/add', {
    phone: formModal.value.phone,
    address: formModal.value.address,
    carId: id,
    rentalStartDate: date.value[0],
    rentalEndDate: date.value[1],
  });
  if (res.code === 0) {
    open.value = false;
    formModal.value.address = '';
    formModal.value.phone = '';
    date.value = null;
  }
};
</script>

<style scoped>
.a-input {
  margin-bottom: 10px;
}
</style>