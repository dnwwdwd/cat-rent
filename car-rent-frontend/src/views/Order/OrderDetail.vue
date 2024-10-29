<template>
  <div style="max-width: 80%; display: flex; justify-content: center; align-items: center; margin: auto; padding: 20px">
    <div style="width: 360px;">
      <a-image
          :width="320"
          :height="400"
          :src="`https://tse3-mm.cn.bing.net/th/id/OIP-C.ZNLoND95Na42tec9yPmyIQHaFj?rs=1&pid=ImgDetMain`"
          style="border-radius: 6%"
      />
    </div>
    <div id="watermark" :data-content="watermarkContent" style="width: 60%; height: 420px; margin: 20px; border: 1px solid #e8e8e8;
    border-radius: 5px; background-color: #f9f9f9;
    box-shadow: 8px 8px 8px rgba(255, 192, 203, 0.5);">
      <div style="padding: 20px; display: flex; align-content: center; justify-content: space-between">
        <div style="display: flex; align-items: center; justify-content: center">
          <h3 style="font-size: 20px">品牌：{{ orderVO.carVO.brand }}</h3>
          <h3 style="font-size: 20px">车型：{{ orderVO.carVO.type }}</h3>
        </div>
        <div style="display: flex; justify-content: center; align-items: center;">
          <a-avatar :src="orderVO.user.userAvatar"/>
          <span style="font-size: 27px; margin-left: 10px">{{ orderVO.user.userName }}</span>
        </div>
      </div>
      <div style="padding: 10px">
        <h3 style="font-size: 20px">租期：{{ orderVO.rentalStartDate }} ~ {{ orderVO.rentalEndDate }}</h3>
        <h3 style="font-size: 20px">总价：{{ orderVO.totalPrice }}</h3>
        <h3 style="font-size: 20px">车牌号：{{ orderVO.carVO.carNumber }}</h3>
        <h3 style="font-size: 20px">成色：{{ orderVO.carVO.quality }}</h3>
        <h3 style="font-size: 20px">里程数：{{ orderVO.carVO.mileage }}</h3>
        <h3 style="font-size: 20px">颜色：{{ orderVO.carVO.color }}</h3>
      </div>
      <a-progress :stroke-color="{'0%': '#108ee9','100%': '#87d068',}" status="active" style="margin-top: 12px"
                  type="line" :percent="percent" :format="percent => `${percent} %`"/>
      <div style="display: flex; justify-content: flex-end; margin-top: 10px">
        <a-button v-if="orderVO.status === 1" type="primary">结束租赁</a-button>
      </div>
    </div>
  </div>
</template>

<script setup>
import {onMounted, ref} from "vue";
import myAxios from "../../plugins/myAxios.js";
import {useRoute} from "vue-router";

const route = useRoute();
const id = route.params.id;

const orderVO = ref({
  user: {},
  carVO: {},
});

const watermarkContent = ref('');

const percent = ref(0);

onMounted(async () => {
  console.log('发送请求了');
  const res = await myAxios.get(`/order/detail/${id}`);
  if (res.data) {
    orderVO.value = res.data;
    watermarkContent.value = orderVO.value.status === 0 ? '等待商家交接' : '订单进行中';
    switch (res.data.status) {
      case 0:
        percent.value = 0;
        watermarkContent.value = '等待商家交接';
        break;
      case 1:
        percent.value = 75;
        watermarkContent.value = '订单进行中';
        break;
      case 2:
        percent.value = 100;
        watermarkContent.value = '订单已结束';
        break;
      default:
        percent.value = 0;
        watermarkContent.value = '等待商家交接';
    }
  }
});
</script>

<style scoped>
#watermark {
  position: relative;
  padding: 20px;
  border: 1px solid #e8e8e8;
  height: 200px; /* 设置适当高度 */
}

/* 使用 data-content 属性设置动态水印内容 */
#watermark::before {
  content: attr(data-content); /* 动态显示 data-content 属性的值 */
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-size: 30px;
  color: rgba(0, 0, 0, 0.1);
  pointer-events: none;
  white-space: nowrap;
  z-index: 1;
}
</style>
