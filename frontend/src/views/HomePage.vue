<template>
  <div class="container">
    <div class="body">
      <div class="nav">
        <el-affix :offset="80">
          <el-card>
            <el-menu default-active="2" class="el-menu-vertical-demo">
              <span @click="router.push('/login')">
               <el-menu-item index="3">登录 </el-menu-item>
              </span>
              <span @click="router.push('/user')">
               <el-menu-item index="11">用户 </el-menu-item>
              </span>
              <span @click="router.push('/article')">
               <el-menu-item index="2">文章 </el-menu-item>
              </span>
        
            </el-menu>
          </el-card>
        </el-affix>

      </div>
      <div class="page">
        <router-view/>
      </div>
    </div>
  </div>
</template>

<script setup>
import {RouterView} from "vue-router";
import router from "@/router";
import {Position} from "@element-plus/icons-vue";
</script>

<style lang="scss">
.container {
  margin: 80px 20px;

  .el-card__body {
    padding: 0;
  }

  .body {
    display: flex;
    top: 180px;

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

.el-card__header {
  padding: 10px 15px;

  .card-header {
    font-size: 20px;
  }
}
</style>
