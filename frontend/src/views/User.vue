<template>
  <div class="user-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>用户</span>
        </div>
      </template>
      <div class="user-body">
        <div class="button-list">
          <el-button type="primary" @click="getUserList">获取用户信息</el-button>
          <el-button type="primary" @click="insertUser">添加用户</el-button>
        </div>
        <div class="pagination">
          <el-pagination
              background
              :hide-on-single-page="true"
              layout="prev, pager, next"
              :total="total"
              :page-size="userListForm.pageSize"
              :page-count="pages"
              :current-page="userListForm.pageNum"
              @current-change="handleCurrentChange"
              class="mt-4"
          />

        </div>
        <div class="table">

          <el-table :data="userList" style="width: 100%" stripe border fit>
            <el-table-column fixed prop="id" label="主键ID"/>
            <el-table-column prop="userId" label="用户ID"/>
            <el-table-column prop="username" label="用户名"/>
            <el-table-column prop="password" label="密码"/>
            <el-table-column prop="createTime" label="创建日期"/>
            <el-table-column fixed="right" label="操作">
              <template #default="{row}">
                <el-button type="danger" @click="deleteUser(row)">删除</el-button>
                <el-button type="primary" @click="updateUser(row)">修改</el-button>
              </template>
            </el-table-column>
          </el-table>

        </div>

      </div>


    </el-card>

  </div>

  <!-- Form -->
  <el-dialog v-model="dialogFormVisible" :title="title" width="30%">
    <el-form :model="userInfo">
      <el-form-item label="用户名">
        <el-input v-model="userInfo.username"/>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="userInfo.password"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="postUser">
                  提交
                </el-button>
      </span>
    </template>
  </el-dialog>

</template>

<script setup>
import {userApi} from "@/api/user-api.js";
import {toRefs, ref, reactive, onMounted} from "vue";
import {ElMessage} from "element-plus";


const userList = ref([]);
const total = ref();
const pages = ref();
const userListForm = reactive({
  pageNum: 1,
  pageSize: 20,
  sortItemList: [
    {isAsc: true, column: "create_time"},
    {isAsc: true, column: "user_id"},
  ]
});

async function getUserList() {
  try {
    let res = await userApi.getUserList(userListForm);
    userList.value = res.data.list;
    total.value = res.data.total;
    pages.value = res.data.pages;
    console.log(userList);
  } catch (error) {
    console.log(error);
  }
}

async function handleCurrentChange(newPage) {
  userListForm.pageNum = newPage;
  getUserList();
}

async function deleteUser(row) {
  ElMessage.success(row.username);
  // console.log(row);
}


const dialogFormVisible = ref(false);
const userInfo = reactive({
  id: "",
  userId: "",
  username: "",
  password: "",
  createTime: "",
});
const title = ref("");

async function updateUser(row) {
  dialogFormVisible.value = true;
  Object.assign(userInfo, toRefs(row));
  title.value = "修改用户";
}

async function insertUser() {
  dialogFormVisible.value = true;
  Object.keys(userInfo).forEach((key) => {
    userInfo[key] = null;
  });
  title.value = "添加用户";
}

async function postUser(user) {
  let res = await userApi.updateUser(user);
  dialogFormVisible.value = false;
  if (res.ok) {
    ElMessage.success(title.value + "成功!");
  } else {
    ElMessage.error(title.value + "失败!");
  }
}

async function getUser() {
  try {
    let res = await userApi.getUser(101);
    userInfo.value = res.data;
    console.log(res.data);
  } catch (error) {
    console.log(error);
  }
}

onMounted(() => {
  getUserList();
})
</script>

<style lang="scss">
.user-container {
  .user-body {
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
