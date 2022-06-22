<template>
  <div class="login_container">
    <div class="login_box">
      <div class="avatar_box" @click="navTo">
        <img :src="require('@/assets/images/logo.png')" alt="" />
      </div>
      <el-form ref="loginFormRef" :model="loginForm" label-width="0px" class="login_form"
        :rules="loginFormRules">
        <el-form-item prop="userId">
          <el-input prefix-icon="iconfont icon-user" v-model="loginForm.userId"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input prefix-icon="iconfont icon-password" v-model="loginForm.password"
            type="password"></el-input>
        </el-form-item>
        <el-form-item class="btns">
          <el-button type="primary" @click="login">登陆</el-button>
          <el-button type="info" @click="reset">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  created () {
    let activePath;
    if (window.sessionStorage.getItem('token') != null) {
      if ((activePath = window.sessionStorage.getItem('activePath')) != null) {
        this.$router.push("/cms/" + activePath)
      }
      else this.$router.push("/cms/home")
    }
  },
  data () {
    return {
      loginForm: {
        userId: '',
        password: ''
      },
      loginFormRules: {
        userId: [
          { required: true, message: '请输入账号', trigger: 'blur' },
          { min: 3, max: 10, message: '格式错误', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 3, max: 18, message: '格式错误', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    reset () {
      this.$refs.loginFormRef.resetFields()
    },
    login () {
      this.$refs.loginFormRef.validate(async vaild => {
        if (!vaild) return
        const { data: res } = await this.$http.post('/api/login', this.loginForm)
        if (res.meta.code != 200) return this.$message.error('登陆失败!');
        this.$message.success('登陆成功!');
        window.sessionStorage.setItem("token", res.meta.token);
        window.sessionStorage.setItem("userName", res.data.userName);
        window.sessionStorage.setItem("userId", res.data.userId);
        window.sessionStorage.setItem("dNo", res.data.dno)
        window.sessionStorage.setItem("mNo", res.data.mno)
        window.sessionStorage.setItem("isAdmin", res.data.type)
        window.sessionStorage.setItem("userTel", res.data.userTel);
        window.sessionStorage.setItem("userNo", res.data.userNo);
        this.$router.push("/cms/home");
      })
    },
    navTo () {
      this.$router.push("/");
    }
  }
}
</script>

<style lang="less" scoped>
/deep/.el-input__inner:focus {
  border-color: #c0c0c0;
}

.login_container {
  background-color: #ececec;
  height: 100%;
}

.login_box {
  height: 300px;
  width: 450px;
  background-color: #fff;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.3);
  border-radius: 3px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  .avatar_box {
    height: 130px;
    width: 130px;
    border: 3px solid #eee;
    border-radius: 50%;
    padding: 10px;
    box-shadow: 0 0 10px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;
    cursor: pointer;
    img {
      height: 100%;
      border-radius: 50%;
      background-color: #eee;
    }
  }

  .login_form {
    position: absolute;
    bottom: 0px;
    width: 100%;
    padding: 0 20px;
    box-sizing: border-box;
  }

  .btns {
    display: flex;
    justify-content: space-around;
  }
}
</style>
