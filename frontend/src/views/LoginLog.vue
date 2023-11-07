<template>
  <div class="login-log-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>登录信息</span>
        </div>
      </template>
      <div class="login-log-body">
        <div class="button-list">
          <el-button type="primary" @click="getLoginLogList"
            >获取登录信息</el-button
          >
        </div>

        <div class="pagination">
          <el-pagination
            background
            :hide-on-single-page="true"
            layout="prev, pager, next"
            :total="total"
            :page-size="loginLogListForm.pageSize"
            :page-count="pages"
            :current-page="loginLogListForm.pageNum"
            @current-change="handleCurrentChange"
            class="mt-4"
          />
        </div>

        <el-table :data="loginLogList" style="width: 100%" stripe border fit>
          <el-table-column prop="loginLogId" label="登录ID" />
          <el-table-column prop="userId" label="用户ID" />
          <el-table-column prop="loginIp" label="IP地址" />
          <el-table-column prop="loginResult" label="结果" :formatter="formatLoginResult" />
          <el-table-column prop="createTime" label="登录时间" />
        </el-table>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { loginLogApi } from "@/api/login-log-api.js";
import { toRefs, ref, reactive, onMounted } from "vue";
import { ElMessage } from "element-plus";
import router from "@/router";

const loginLogList = ref([]);
const total = ref();
const pages = ref();
const loginLogListForm = reactive({
  pageNum: 1,
  pageSize: 20,
  userId: "",
  sortItemList: [
    { isAsc: false, column: "login_log_id" },
    { isAsc: true, column: "create_time" },
  ],
});

async function getLoginLogList() {
  try {
    let res = await loginLogApi.getLoginLogList(loginLogListForm);
    loginLogList.value = res.data.list;
    total.value = res.data.total;
    pages.value = res.data.pages;
    console.log(loginLogList);
  } catch (error) {
    console.log(error);
  }
}
async function handleCurrentChange(newPage) {
  loginLogListForm.pageNum = newPage;
  getLoginLogList();
}
const formatLoginResult = (row) => {
  switch (row.loginResult) {
    case 0:
      return "登录成功";
    case 1:
      return "登录失败";
    case 2:
      return "退出登录";
    default:
      return "";
  }
};
onMounted(() => {
  getLoginLogList();
});
</script>

<style lang="scss">
.login-log-container {
  .login-log-body {
    .button-list {
      display: flex;
      padding: 5px 5px;
    }

    .table {
    }

    .pagination {
      margin: 20px 0;
      display: flex;
      justify-content: center;
    }
  }
}
</style>
