<template>
  <div class="web-header">
    <el-card class="header-card"> 数据库项目 </el-card>
  </div>
  <div class="web-body">
    <router-view></router-view>
  </div>
  <el-backtop :right="10" :bottom="10" />
</template>
<script setup></script>
<style lang="scss">
body {
  background: #f0f0f0;
}
.web-header {
  position: fixed;
  z-index: 10;
  top: 0;
  left: 0;
  right: 0;
  height: 60px;
  .header-card {
    border-top: 0;
    display: flex;
    justify-content: center;
    font-size: 18px;
    height: 100%;
  }
}
.web-body {
  margin: 60px auto;
}
</style>
