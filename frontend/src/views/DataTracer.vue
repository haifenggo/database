<template>
  <div class="data-tracer-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>数据跟踪</span>
        </div>
      </template>
      <div class="data-tracer-body">
        <div class="button-list">
          <el-button type="primary" @click="getDataTracerList"
            >获取数据跟踪</el-button
          >
        </div>

        <div class="pagination">
          <el-pagination
            background
            :hide-on-single-page="true"
            layout="prev, pager, next"
            :total="dataTracerTotal"
            :page-size="dataTracerListForm.pageSize"
            :page-count="dataTracerPages"
            :current-page="dataTracerListForm.pageNum"
            @current-change="handleCurrentChange"
            class="mt-4"
          />
        </div>

        <el-table :data="dataTracerList" style="width: 100%" stripe border fit>
          <el-table-column prop="id" label="数据跟踪ID" />
          <el-table-column prop="userId" label="用户ID" />
          <el-table-column prop="type" label="类型" :formatter="formatType" />
          <el-table-column label="内容">
            <template #default="{ row }">
              <div v-html="formatContent(row)"></div>
            </template>
          </el-table-column>
          <el-table-column prop="createTime" label="创建时间" />
        </el-table>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { dataTracerApi } from "@/api/data-tracer-api.js";
import { toRefs, ref, reactive, onMounted } from "vue";
import { ElMessage } from "element-plus";
import router from "@/router";
function formatContent(row) {
  console.log(row.content);
  return row.content.replace(/\r\n/g, "<br>");
}
const tracerEnum = {
  1: "INSERT",
  2: "UPDATE",
  3: "DELETE",
  4: "SELECT",
  5: "URI",
};

const formatType = (row) => {
  return tracerEnum[row.type] || "";
};
const dataTracerList = ref([]);
const dataTracerTotal = ref();
const dataTracerPages = ref();
const dataTracerListForm = reactive({
  pageNum: 1,
  pageSize: 20,
  userId: "",
  sortItemList: [
    { isAsc: false, column: "id" },
    { isAsc: true, column: "create_time" },
  ],
});

async function getDataTracerList() {
  try {
    let res = await dataTracerApi.getDataTracerList(dataTracerListForm);
    dataTracerList.value = res.data.list;
    dataTracerTotal.value = res.data.total;
    dataTracerPages.value = res.data.pages;
    console.log(dataTracerList);
  } catch (error) {
    console.log(error);
  }
}
async function handleCurrentChange(newPage) {
  dataTracerListForm.pageNum = newPage;
  getDataTracerList();
}

onMounted(() => {
  getDataTracerList();
});
</script>

<style lang="scss">
.data-tracer-container {
  .data-tracer-body {
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
