<template>
  <div class="post-detail-container">
    <el-button type="primary" @click="updatePost">修改文章</el-button>
    <div class="title">{{ postInfo.title }}</div>
    <div class="username">{{ postInfo.username }}</div>
    <div class="time">{{ postInfo.createTime }}</div>
    <div class="content">内容：{{ postInfo.content }}</div>
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
</script>

<style lang="scss"></style>
