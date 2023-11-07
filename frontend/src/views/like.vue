<template>
  <div class="like-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>点赞信息</span>
        </div>
      </template>
      <div class="like-body">
        <div class="button-list">
          <el-button type="primary" @click="getLikeList"
            >获取点赞信息</el-button
          >
        </div>

        <div class="pagination">
          <el-pagination
            background
            :hide-on-single-page="true"
            layout="prev, pager, next"
            :total="total"
            :page-size="likeListForm.pageSize"
            :page-count="pages"
            :current-page="likeListForm.pageNum"
            @current-change="handleCurrentChange"
            class="mt-4"
          />
        </div>

        <el-table :data="likeList" style="width: 100%" stripe border fit>
          <el-table-column prop="id" label="点赞ID" />
          <el-table-column prop="userId" label="用户ID" />
          <el-table-column prop="postId" label="文章ID" />
          <el-table-column prop="createTime" label="创建日期" />
          <el-table-column fixed="right" label="操作">
            <template #default="{ row }">
              <el-button type="danger" @click="deleteLike(row)"
                >删除点赞</el-button
              >
              <el-button type="primary" @click="likeDetail(row)"
                >查看文章</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { likeApi } from "@/api/like-api.js";
import { toRefs, ref, reactive, onMounted } from "vue";
import { ElMessage } from "element-plus";
import router from "@/router";

const likeList = ref([]);
const total = ref();
const pages = ref();
const likeListForm = reactive({
  pageNum: 1,
  pageSize: 20,
  userId: "",
  postId: "",
  sortItemList: [
    { isAsc: false, column: "id" },
    { isAsc: true, column: "create_time" },
  ],
});



async function getLikeList() {
  try {
    let res = await likeApi.getLikeList(likeListForm);
    likeList.value = res.data.list;
    total.value = res.data.total;
    pages.value = res.data.pages;
    console.log(likeList);
  } catch (error) {
    console.log(error);
  }
}
async function handleCurrentChange(newPage) {
  likeListForm.pageNum = newPage;
  getLikeList();
}
async function deleteLike(row) {
  let res = await likeApi.deleteLike(row.id);
  if (res.ok) {
    ElMessage.success("删除点赞成功！");
    getLikeList();
  }
}
async function likeDetail(row) {
  router.push(`post/${row.postId}`);
}

onMounted(() => {
  getLikeList();
});
</script>

<style lang="scss">
.like-container {
  .like-body {
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
