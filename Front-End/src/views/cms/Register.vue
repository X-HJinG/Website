<template>
  <div>
    <el-card>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>管理权限</el-breadcrumb-item>
        <el-breadcrumb-item>成员注册</el-breadcrumb-item>
      </el-breadcrumb>
    </el-card>
    <el-card>
      <el-table :data="input" stripe style="width: 100%" :key="currentPage">
        <el-table-column prop="userName" label="姓名" width="200">
        </el-table-column>
        <el-table-column prop="userId" label="账号" width="400">
        </el-table-column>
        <el-table-column prop="password" label="密码">
        </el-table-column>
      </el-table>
      <el-pagination layout="prev, pager, next" :page-size="pageSize" :total="totalSize"
        class="page" @current-change="handleCurrentChange">
      </el-pagination>
    </el-card>
    <el-card>
      <el-upload ref="uploadForm" class="upload" action="none" :show-file-list="false"
        :http-request="uploadFile">
        <el-button type="primary">上传文件</el-button>
      </el-upload>
      <el-button type="info" @click="uploadData">上传服务器</el-button>
      <el-button type="success" @click="downLoad" :disabled="flag">下载名单</el-button>
    </el-card>
  </div>
</template>

<script>
export default {
  mounted () {
    this.flag = true;
  },
  data () {
    return {
      flag: true,
      input: [],
      total: [],
      pageSize: 7,
      totalSize: 7,
      currentPage: 0
    };
  },
  methods: {
    handleCurrentChange (page) {
      this.currentPage = page;
      this.getData(page);
    },

    getData (currentPage) {
      let output = this.input;
      let start = (currentPage - 1) * this.pageSize;
      output.length = 0;
      for (let i = 0; i < this.pageSize; i++) {
        output[i] = this.total[start++];
        if (start > this.totalSize - 1) break;
      }
    },

    uploadFile (res) {
      let form = new FormData();
      form.append("file", res.file);
      this.input.length = 0;
      this.currentPage = 0;
      this.$http({
        headers: {
          'Content-Type': 'multipart/form-data'
        },
        method: 'post',
        url: '/api/register/output',
        data: form
      }).then(res => {
        const result = res.data.data;
        result.forEach(element => {
          element['dNo'] = 1;
        });
        console.log(result);
        this.total = result;
        this.totalSize = result.length;
        this.currentPage = 1;
        this.getData(this.currentPage);
      })
    },


    uploadData () {
      console.log(this.total)
      this.$http({
        method: 'post',
        url: '/api/register',
        data: this.total
      }).then(res => {
        console.log(res)
        if (res.data.meta.code == 200) {
          this.flag = false;
          this.$message.success("上传成功");
        }
        else {
          this.flag = true;
          this.$message.error("上传失败");
        }
      })
    },

    downLoad () {
      this.$http({
        method: 'post',
        url: '/api/register/getList',
        data: this.total,
        responseType: 'blob'
      }).then(res => {
        let url = window.URL.createObjectURL(new Blob([res.data]));
        let link = document.createElement("a");
        link.style.display = "none";
        link.href = url;
        document.body.appendChild(link);
        link.download = "output.txt"
        link.click();
        link.remove();
      }).catch(err => {
        console.log(err)
      })
    }
  }
};
</script>

<style lang="less" scoped>
.el-card {
  margin-bottom: 10px;
}
.page {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}
.upload {
  display: inline-block;
  margin-right: 20px;
}
/deep/.el-upload-list__item {
  transition: none !important;
}
.el-button {
  margin-right: 5px;
}
</style>
