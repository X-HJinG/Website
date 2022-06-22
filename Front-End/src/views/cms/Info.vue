<template>
  <div>
    <el-card>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/cms/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>个人中心</el-breadcrumb-item>
        <el-breadcrumb-item>个人资料</el-breadcrumb-item>
      </el-breadcrumb>
    </el-card>
    <el-card class="info">
      <span class="desc">姓名</span>
      <el-input v-model="userName" disabled></el-input>
      <span class="desc">学号</span>
      <el-input v-model="userNo"></el-input>
      <span class="desc">联系方式</span>
      <el-input v-model="userTel"></el-input>
      <span class="desc">所在专业</span>
      <el-select v-model="major" placeholder="请选择">
        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"
          @click.native="setOptions(item.value)">
        </el-option>
      </el-select>
      <div class="btns">
        <el-button type="primary" @click="submit()">提交</el-button>
        <el-button type="info" @click="reset()">重置</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
let list = [{
  value: 0,
  label: '网络工程'
}, {
  value: 1,
  label: '网络空间安全'
}, {
  value: 2,
  label: '数据科学与大数据技术'
}, {
  value: 3,
  label: '数据科学与大数据技术 (人工智能方向)'
}];
export default {
  created () {
    this.init();
  },
  data () {
    return {
      userName: '',
      userNo: '',
      userTel: '',
      options: list,
      mNo: '',
      major: '',
    };
  },
  methods: {
    init () {
      let mNo = window.sessionStorage.getItem("mNo");
      this.userTel = window.sessionStorage.getItem("userTel");
      this.userNo = window.sessionStorage.getItem("userNo")
      this.userName = window.sessionStorage.getItem("userName");
      this.mNo = mNo
      this.major = mNo != 'null' ? list[mNo].label : '';
    },
    setOptions (value) {
      this.mNo = value;
    },
    submit () {
      let userInfo = {};
      let userId = window.sessionStorage.getItem('userId');
      userInfo.userNo = this.userNo;
      userInfo.mno = this.mNo;
      userInfo.userTel = this.userTel;
      userInfo.userId = userId;
      this.doPost('/api/Info', userInfo)
    },

    doPost (path, data) {
      this.$http.post(path, data).then((res) => {
        if (res.data.meta.code == 200) {
          this.$message.success("修改成功");
          this.refreshStorage();
        }
      }).catch(err => {
        this.$message.error("服务器错误")
      })
    },

    reset () {
      this.init();
    },
    refreshStorage () {
      window.sessionStorage.setItem("userNo", this.userNo);
      window.sessionStorage.setItem("userTel", this.userTel);
      window.sessionStorage.setItem("mNo", this.mNo);
    }
  }
};
</script>

<style lang="less" scoped>
.info {
  margin-top: 10px;
}
.el-input {
  width: 85%;
  margin: 10px 0;
}
.el-select {
  width: 85%;
  margin-top: 10px;
}
.desc {
  display: inline-block;
  width: 8%;
  text-align: right;
  margin-right: 10px;
}
.btns {
  margin-top: 20px;
  display: flex;
  justify-content: center;
}
</style>
