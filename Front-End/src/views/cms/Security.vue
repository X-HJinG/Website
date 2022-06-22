<template>
  <div>
    <el-card>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>个人中心</el-breadcrumb-item>
        <el-breadcrumb-item>修改密码</el-breadcrumb-item>
      </el-breadcrumb>
    </el-card>
    <el-card class="info">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" status-icon>
        <el-form-item prop="oldPassword">
          <el-input placeholder="请输入原密码" prefix-icon="iconfont icon-password" type="password"
            show-password v-model="ruleForm.oldPassword" @keyup.enter.native="enterBlur($event)">
          </el-input>
        </el-form-item>
        <el-form-item prop="newPassword">
          <el-input placeholder="请输入新密码" prefix-icon="iconfont icon-password" type="password"
            show-password v-model="ruleForm.newPassword" @keyup.enter.native="enterBlur($event)">
          </el-input>
        </el-form-item>
        <el-form-item prop="againPassword">
          <el-input placeholder="请再输入新密码" prefix-icon="iconfont icon-password" type="password"
            show-password v-model="ruleForm.againPassword" @keyup.enter.native="enterBlur($event)">
          </el-input>
        </el-form-item>
        <el-form-item>
          <div class="btns">
            <el-button type="primary" @click="submit('ruleForm')">提交</el-button>
            <el-button type="info" @click="resetForm('ruleForm')">重置</el-button>
          </div>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  data () {
    let vaildatePass = async (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        let res = await this.check();
        if (res.meta.code == 200) {
          callback();
        }
        callback(new Error('密码错误'));
      }
    };
    let vaildateNewPass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      }
      callback();
    }
    let vaildateAgain = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      }
      else if (value !== this.ruleForm.newPassword) {
        callback(new Error("两次密码不一致"))
      }
      else callback();
    }
    return {
      ruleForm: {
        oldPassword: '',
        newPassword: '',
        againPassword: '',
      },
      rules: {
        oldPassword: [
          { validator: vaildatePass, trigger: 'blur' }
        ],
        newPassword: [
          { validator: vaildateNewPass, trigger: 'blur' },
          { min: 6, max: 18, message: '格式错误', trigger: 'blur' }
        ],
        againPassword: [
          { validator: vaildateAgain, trigger: 'blur' }
        ]
      }
    };

  },
  methods: {
    async check () {
      let isAdmin = window.sessionStorage.getItem("isAdmin");
      let account = {}
      account.userId = window.sessionStorage.getItem("userId");
      account.password = this.ruleForm.oldPassword;
      if (isAdmin == 'false') {
        let res = await this.$http.post('/api/security/0/check', account)
        return res.data;
      }
      if (isAdmin == 'true') {
        let res = await this.$http.post('/api/security/1/check', account)
        return res.data;
      }
      return null
    },
    enterBlur (event) {
      event.target.blur();
    },
    resetForm (formName) {
      this.$refs[formName].resetFields();
    },
    submit (formName) {
      let vm = this;
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          let isAdmin = window.sessionStorage.getItem("isAdmin");
          let account = {}
          account.userId = window.sessionStorage.getItem("userId");
          account.password = this.ruleForm.newPassword;
          if (isAdmin == "false") {
            this.doPost('/api/security/0', account);
          }
          if (isAdmin == 'true') {
            this.doPost('/api/security/1', account)
          }
        } else {
          vm.$message.error("提交失败");
        }
      });
    },

    async doPost (path, data) {
      let res = await this.$http.post(path, data)
      if (res.data.meta.code == 200) {
        this.$message.success("修改成功");
        setTimeout(() => {
          window.sessionStorage.clear()
          this.$router.push("/cms/login")
        }, 2000)
      }
      else {
        this.$message.error("提交失败");
        return "fail";
      }
      return "success";
    }
  }
};
</script>

<style lang="less" scoped>
.info {
  margin-top: 10px;
}
.el-input {
  margin: 10px 0px;
}
.btns {
  margin-top: 20px;
  display: flex;
  justify-content: center;
}
</style>
