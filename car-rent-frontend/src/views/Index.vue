<template>
  <div style="max-width: 80%">
    <a-flex wrap="wrap" gap="small">
      <a-card hoverable style="width: 220px;" v-for="pet in petList" @click="router.push(`/pet/detail/${pet.id}`)">
        <template #cover>
          <img
              alt="喵喵"
              :src="pet.avatarUrl"
              style="height: 180px"
          />
        </template>
        <a-card-meta :title="pet.petName" :description="`年龄：${pet.age} 性别：${pet.sex} 品种：${pet.variety}`">
        </a-card-meta>
      </a-card>
    </a-flex>
  </div>
</template>

<script setup lang="js">
import {onMounted, ref} from "vue";
import myAxios from "../plugins/myAxios.js";
import {useRouter} from "vue-router";

const router = useRouter();

const petList = ref([]);

onMounted(async () => {
  const res = await myAxios.get('/pet/list');
  if (res.code === 0) {
    petList.value = res.data;
  }
});

</script>

<style>

</style>