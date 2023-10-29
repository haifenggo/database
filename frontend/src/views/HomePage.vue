<template>
  <div class="container">
    <div class="body">
      <div class="nav">
        <el-card>
          <el-menu default-active="2" class="el-menu-vertical-demo">
            <el-menu-item index="2">
              <span @click="router.push('/user')">用户</span>
            </el-menu-item>
            <el-menu-item index="4">
              <span @click="router.push('/article')">文章</span>
            </el-menu-item>
          </el-menu>
        </el-card>
      </div>
      <div class="page">
          <RouterView />
      </div>
    </div>
  </div>
</template>

<script setup>
import { RouterView } from "vue-router";
import router from "@/router";
</script>

<style lang="scss">
.container {
  margin: 80px 20px;
  .el-card__body {
    padding: 0;
  }
  .body {
    display: flex;
    .nav {
      .el-menu {
        border-right: 0;
      }
    }
    .page {
      margin-left: 20px;
      flex: 1;
    }
  }
}
.el-card__header{
  padding: 10px 15px;
  .card-header{
    font-size: 20px;
  }
}
</style>
