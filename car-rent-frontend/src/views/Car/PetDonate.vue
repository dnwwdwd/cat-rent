<template>
  <div style="max-width: 80%">
    <a-flex wrap="wrap" gap="small">
      <a-card hoverable style="width: 220px" v-for="pet in petList" :key="pet.id"
              @click="() => router.push(`/donate/detail/${pet.id}`)">
        <template #cover>
          <img
              alt="喵喵"
              :src="pet.avatarUrl"
              style="height: 180px"
          />
        </template>
        <a-card-meta
            :title="pet.petName"
            v-html="`<span style='color: gray;'>${pet.petName} 年龄：${pet.age} <br> 性别：${pet.sex} <br> ${pet.isVaccination === 0 ? '未接种疫苗' : '已接种疫苗'} <br> ${pet.isSterilized === 0 ? '已绝育' : '未绝育'}</span>`"
        >
        </a-card-meta>
      </a-card>
    </a-flex>
  </div>
</template>

<script setup lang="js">
import {onMounted, ref} from "vue";
import {useRouter} from "vue-router";
import myAxios from "../../plugins/myAxios.js";

const router = useRouter();

const petName = ref('');

const petList = ref([]);


const loadData = async () => {
  const res = await myAxios.get('/donate/pet/list');
  if (res.code === 0) {
    petList.value = res.data;
  }
};

onMounted(async () => {
  loadData();
});

</script>

<style>

</style>
