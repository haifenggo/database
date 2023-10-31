<template>
  <div class="article-container">

    <div class="user-container">
      <div class="user-body">
        <el-card>
          <template #header>
            <div class="card-header">
              <span>文章</span>
            </div>
          </template>
          <div class="main">

            <el-button text @click="dialogVisible = true">
              click to open the Dialog
            </el-button>

            <el-dialog
                v-model="dialogVisible"
                title="Tips"
                width="30%"
                :before-close="handleClose"
            >
              <span>This is a message</span>
              <template #footer>

      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button
            type="primary" @click="dialogVisible = false">
          Confirm
        </el-button>
      </span>
              </template>
            </el-dialog>


            <el-empty description="description"/>
          </div>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref} from 'vue'
import {ElMessageBox} from 'element-plus'

const dialogVisible = ref(false)
</script>

<style lang="scss">


</style>