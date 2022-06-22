<template>
  <div>
    <el-card>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/cms/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>信息专栏</el-breadcrumb-item>
        <el-breadcrumb-item>公告发布</el-breadcrumb-item>
      </el-breadcrumb>
    </el-card>
    <el-card class="textarea">
      <el-form ref="form" :rules="rules" :model="form">
        <el-form-item prop="context">
          <el-input type="textarea" placeholder="请输入内容" maxlength="200" show-word-limit
            resize="none" rows="6" class="input" v-model="form.context">
          </el-input>
        </el-form-item>
        <el-form-item label="联系方式 ：" prop="remark">
          <el-input v-model="form.remark.text" class="input-with-select">
            <el-select v-model="form.remark.type" slot="prepend" placeholder="请选择">
              <el-option label="WX" value="WX"></el-option>
              <el-option label="QQ" value="QQ"></el-option>
              <el-option label="Tel" value="Tel"></el-option>
            </el-select>
          </el-input>
        </el-form-item>
      </el-form>
      <div class="btns">
        <el-row>
          <el-button type="primary" @click="submit()">提交</el-button>
          <el-button type="info" @click="reset()">重置</el-button>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data () {
    let validateContext = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("内容不能为空"))
      } else {
        callback();
      }
    };
    let validateRemark = (rule, value, callback) => {
      if (value.type === "") {
        callback(new Error("类型不能为空"))
      }
      if (value.text === "") {
        callback(new Error("内容不能为空"))
      }
      else {
        callback();
      }
    };
    return {
      form: {
        context: "",
        remark: {
          type: "",
          text: "",
        }
      },
      rules: {
        context: [
          { validator: validateContext, trigger: 'blur' }
        ],
        remark: [
          { validator: validateRemark, trigger: 'blur' }
        ],
      }
    };
  },
  methods: {
    async submit () {
      this.$refs["form"].validate(async (valid) => {
        if (valid) {
          let date = new Date();
          let notice = {}
          notice.uploadtime = date.getTime();
          notice.userId = window.sessionStorage.getItem("userId");
          notice.mainContext = this.form.context;
          notice.remark = this.form.remark.type + " : " + this.form.remark.text;
          let res = await this.$http.post('/api/notice/input', notice)
          if (res.data.meta.code == 200) {
            this.$message.success({
              message: "上传成功",
              duration: "1000"
            });
            this.resetForm();
            this.$refs["form"].resetFields();
          }
        }
      })

    },

    resetForm () {
      this.form.remark.type = "";
      this.form.remark.text = "";
    },
    reset () {
      this.resetForm();
      this.$refs["form"].resetFields();
    }
  },
};
</script>

<style lang="less" scoped>
/deep/.el-textarea__inner:focus {
  border-color: #c0c0c0;
}

.textarea {
  margin-top: 10px;
  .input {
    font-size: 20px;
  }
}
.el-select {
  width: 100px;
}
.input-with-select {
  width: 90%;
}

.btns {
  margin-top: 30px;
  display: flex;
  justify-content: center;
}
</style>