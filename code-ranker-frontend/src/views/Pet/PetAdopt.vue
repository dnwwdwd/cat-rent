<template>
  <div style="max-width: 80%">
    <a-flex wrap="wrap" gap="small">
      <a-card hoverable style="width: 220px" v-for="pet in petList" @click="router.push(`/adopt/${pet.id}`)">
        <template #cover>
          <img
              alt="喵喵"
              :src="pet.avatarUrl"
              style="height: 180px"
          />
        </template>
        <a-card-meta
            :title="pet.petName"
            v-html="`<span style='color: gray;'>${pet.petName} 年龄：${pet.age} <br> 性别：${pet.sex} <br> 捐赠人：${pet.donateUser.nickname} <br> 领养人：${pet.adoptUser.nickname}</span>`">
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

const petList = ref([]);

const loadData = async () => {
  const res = await myAxios.get('/adopt/list/info');
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