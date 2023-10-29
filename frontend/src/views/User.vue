<template>
  <div class="user-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>用户</span>
        </div>
      </template>
      <div class="user-body">
        <el-button @click="getUser">获取用户信息</el-button>
      </div>

    </el-card>

  </div>
</template>

<script setup>
import {userApi} from "@/api/user-api.js";
import {ref, reactive} from "vue";

const userInfo = reactive({});

async function getUser() {
  try {
    let res = await userApi.getUser(101);
    console.log(res.data);
  } catch (error) {
    console.log(error);
  }
}

</script>

<style lang="scss">
.user-container {
  .user-body {
    display: flex;
  }
}

</style>
