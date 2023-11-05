<template>
  <div class="login-container">
    <el-form :model="loginForm" :rules="loginRules" class="login-form">
      <div class="title-container">
        <h3 class="title">登录</h3>
      </div>

      <el-form-item prop="username">
        <el-input v-model="loginForm.username"
                  placeholder="Username" name="username" type="text"></el-input>
      </el-form-item>

      <el-form-item prop="password">
        <el-input type="password" v-model="loginForm.password"
                  placeholder="Password" name="password"></el-input>
      </el-form-item>

      <el-button :loading="loading" type="primary"
                 style="width: 100%; margin-bottom: 30px;"
                 @click="handleLogin">登录
      </el-button>
    </el-form>
  </div>
</template>

<script setup>
import {ref, nextTick, toRefs} from 'vue'
import router from "@/router";
import {validUsername} from '@/utils/validate.js'
import {login} from '@/api/user.js'
import {setToken} from '@/utils/auth.js'
import {ElMessage} from "element-plus";

const loginForm = ref({
  username: 'fqeGBMV9Mj4f',
  password: 'QkYIu'
})

function validateUsername(rule, value) {
  if (!validUsername(value)) {
    return Promise.reject('请输入正确的用户名')
  } else {
    return Promise.resolve()
  }
}

function validatePassword(rule, value) {
  if (value.length < 6) {
    return Promise.reject('密码长度至少为6位')
  } else {
    return Promise.resolve()
  }
}

const loginRules = ref({
  username: [{required: true, trigger: 'blur', validator: validateUsername}],
  password: [{required: true, trigger: 'blur', validator: validatePassword}]
})

const loading = ref(false)


const handleLogin = () => {
  loading.value = true
  // 调用登录后端接口
  login(loginForm.value).then(result => {
    console.log(result)
    if (result.data.ok) {
      setToken(result.data.data)
      console.log(result.data.data);
      console.log('login success')
      ElMessage.success("登录成功");
      router.push('/article')
    } else {
      ElMessage.error(result.data.msg);
      loading.value = false
    }
  })

}


</script>

<style lang="scss" scoped>
.login-container {
  min-height: 100%;
  width: 100%;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
      font-family: '楷体';
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    cursor: pointer;
    user-select: none;
  }
}
</style>
