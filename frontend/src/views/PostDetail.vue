<template>
  <div class="post-detail-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>文章详情</span>
        </div>
      </template>
      <div class="button-list">
        <el-button type="primary" @click="updatePost">修改文章</el-button>
      </div>

      <div class="info">
        <div class="title">{{ postInfo.title }}</div>
        <div class="sub-info">
          <div class="postId">文章ID: {{ postInfo.postId }}</div>
          <div class="username">作者ID: {{ postInfo.userId }}</div>
          <div class="username">作者: {{ postInfo.username }}</div>
          <div class="time">发表时间: {{ postInfo.createTime }}</div>
          <div class="likeCount">点赞数量: {{ postInfo.likeCount }}</div>
        </div>
      </div>
      <div class="content">内容：{{ postInfo.content }}</div>

      <div class="info">
        <div class="like" @click="likePost">
          <el-tag
            class="liked"
            round
            :type="postInfo.liked ? 'success' : 'info'"
            >{{ postInfo.liked ? "已点赞" : "点赞" }}</el-tag
          >
        </div>
      </div>
    </el-card>
  </div>

  <el-dialog v-model="dialogFormVisible" :title="title" width="70%">
    <el-form :model="postUpdateInfo">
      <el-form-item label="标题">
        <el-input v-model="postUpdateInfo.title" />
      </el-form-item>
      <el-form-item label="内容">
        <el-input
          height="300px"
          type="textarea"
          v-model="postUpdateInfo.content"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="postPost(postUpdateInfo)"
          >提交</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { postApi } from "@/api/post-api.js";
import { toRefs, ref, reactive, onMounted } from "vue";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";
import { likeApi } from "../api/like-api";
const router = useRouter();
const postInfo = ref({});
const postUpdateInfo = ref({
  postId: undefined,
  title: "",
  content: "",
});
async function getPostDetail(postId) {
  try {
    let res = await postApi.getPostDetail(postId);
    postInfo.value = res.data;
    // console.log(res.data)
  } catch (error) {
    console.log(error);
  }
}
onMounted(() => {
  getPostDetail(router.currentRoute.value.params.postId);
  //   ElMessage.success(router.currentRoute.value.params.postId);
});
const title = ref("");
const dialogFormVisible = ref(false);

async function updatePost() {
  postUpdateInfo.value.postId = postInfo.value.postId;
  postUpdateInfo.value.title = postInfo.value.title;
  postUpdateInfo.value.content = postInfo.value.content;
  dialogFormVisible.value = true;
  title.value = "修改文章";
}

async function postPost(post) {
  let res = await postApi.updatePost(post);
  dialogFormVisible.value = false;
  if (res.ok) {
    ElMessage.success(title.value + "成功!");
    getPostDetail(router.currentRoute.value.params.postId);
  } else {
    ElMessage.error(title.value + "失败!");
  }
}

async function likePost() {
  let res = await likeApi.likePost(router.currentRoute.value.params.postId);
  if (res.ok) {
    postInfo.value.liked = !postInfo.value.liked;
    // console.log(res.data);
    // console.log(postInfo.liked);
  }
}
</script>

<style lang="scss">
.post-detail-container {
  .el-card__body {
    min-height: 500px;
  }
  .button-list {
    display: flex;
    padding: 5px 5px;
  }
  .info {
    display: flex;
    flex-direction: column;
    align-items: center;
    .title {
      font-size: 20px;
    }
    .sub-info {
      display: flex;
      gap: 20px;
      margin: 10px;
      font-size: 10px;
    }
    .content {
      font-size: 16px;
    }
    .like {
      cursor: pointer;
      .liked {
        margin-top: 20px;
      }
    }
  }
}
</style>
