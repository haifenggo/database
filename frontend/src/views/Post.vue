<template>
  <div class="post-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>文章</span>
        </div>
      </template>
      <div class="post-body">
        <div class="button-list">
          <el-button type="primary" @click="getPostList"
            >获取文章信息</el-button
          >
          <el-button type="primary" @click="insertPost">添加文章</el-button>
        </div>

        <div class="pagination">
          <el-pagination
            background
            :hide-on-single-page="true"
            layout="prev, pager, next"
            :total="total"
            :page-size="postListForm.pageSize"
            :page-count="pages"
            :current-page="postListForm.pageNum"
            @current-change="handleCurrentChange"
            class="mt-4"
          />
        </div>

        <el-table :data="postList" style="width: 100%" stripe border fit>
          <el-table-column prop="postId" label="文章ID" />
          <el-table-column prop="userId" label="用户ID" />
          <el-table-column prop="title" label="标题" />
          <el-table-column prop="createTime" label="创建日期" />
          <el-table-column fixed="right" label="操作">
            <template #default="{ row }">
              <el-button type="danger" @click="deletePost(row)">删除</el-button>
              <el-button type="primary" @click="postDetail(row)"
                >查看</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </div>
      <el-dialog v-model="dialogFormVisible" :title="title" width="70%">
        <el-form :model="postInfo">
          <el-form-item label="标题">
            <el-input v-model="postInfo.title" />
          </el-form-item>
          <el-form-item label="内容">
            <el-input
              height="300px"
              type="textarea"
              v-model="postInfo.content"
            />
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取消</el-button>
            <el-button type="primary" @click="postPost(postInfo)"
              >提交</el-button
            >
          </span>
        </template>
      </el-dialog>
    </el-card>
  </div>
</template>

<script setup>
import { postApi } from "@/api/post-api.js";
import { toRefs, ref, reactive, onMounted } from "vue";
import { ElMessage } from "element-plus";
import router from "@/router";

const postList = ref([]);
const total = ref();
const pages = ref();
const postListForm = reactive({
  pageNum: 1,
  pageSize: 20,
  userId: "",
  username: "",
  sortItemList: [
    { isAsc: false, column: "post_id" },
    { isAsc: true, column: "create_time" },
  ],
});

const dialogFormVisible = ref(false);
const postInfo = reactive({
  postId: "",
  userId: "",
  title: "",
  createTime: "",
});
const title = ref("");

async function getPostList() {
  try {
    let res = await postApi.getPostList(postListForm);
    postList.value = res.data.list;
    total.value = res.data.total;
    pages.value = res.data.pages;
    console.log(postList);
  } catch (error) {
    console.log(error);
  }
}
async function handleCurrentChange(newPage) {
  postListForm.pageNum = newPage;
  getPostList();
}
async function deletePost(row) {
  let res = await postApi.deleteByPostId(row.postId);
  if (res.ok) {
    ElMessage.success(row.title + "文章删除成功！");
    getPostList();
  }
}
async function postDetail(row) {
  ElMessage.success(String(row.postId));
  router.push(`post/${row.postId}`);
  // console.log(row.postId);
}
async function insertPost() {
  dialogFormVisible.value = true;
  Object.keys(postInfo).forEach((key) => {
    postInfo[key] = null;
  });
  title.value = "添加文章";
}

async function postPost(post) {
  let res = await postApi.insertPost(post);
  dialogFormVisible.value = false;
  if (res.ok) {
    ElMessage.success(title.value + "成功!");
    getPostList();
  } else {
    ElMessage.error(title.value + "失败!");
  }
}

onMounted(() => {
  getPostList();
});
</script>

<style lang="scss">
.post-container {
  .post-body {
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
