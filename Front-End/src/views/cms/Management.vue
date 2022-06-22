<template>
  <div>
    <el-card>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/cms/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>管理权限</el-breadcrumb-item>
        <el-breadcrumb-item>成员管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-card>
    <el-card class="info">
      <el-table :data="curData" stripe style="width: 100%" :key="time" v-loading="loading">
        <el-table-column prop="userNo" label="学号" width="120">
        </el-table-column>
        <el-table-column prop="userName" label="姓名" width="140">
        </el-table-column>
        <el-table-column label="专业" width="220">
          <template scope="scope">
            {{list[scope.row.mno]}}
          </template>
        </el-table-column>
        <el-table-column prop="userId" label="账号" width="140">
        </el-table-column>
        <el-table-column prop="userTel" label="手机号" width="160">
        </el-table-column>
        <el-table-column label="操作" min-width="200">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">重置</el-button>
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">
              删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination layout="prev, pager, next" :page-size="pageSize" :total="totalSize"
        class="page" @current-change="handleCurrentChange">
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
export default {
  mounted () {
    let userInfo = {};
    userInfo.dno = parseInt(window.sessionStorage.getItem("dNo"));
    userInfo.userId = window.sessionStorage.getItem("userId");
    console.log(userInfo)
    this.getInit(userInfo);
  },
  data () {
    return {
      totalData: [],
      curData: [],
      pageSize: 7,
      totalSize: 0,
      currentPage: 0,
      time: 0,
      loading: true,
      list: ['网络工程', '网络空间安全', '数据科学与大数据技术', '数据科学与大数据技术 (人工智能方向)']
    };
  },
  methods: {
    getInit (userInfo) {
      this.$http({
        method: 'post',
        url: '/api/management/getList',
        data: userInfo
      }).then(res => {
        const result = res.data.data;
        if (result.length != 0) {
          this.totalData = result;
          this.totalSize = result.length;
          this.currentPage = 1;
        }
        this.getData(this.currentPage);
        this.loading = false;
      })
    },

    getData (currentPage) {
      if (currentPage > 0) {
        this.time = new Date().getTime();
        let output = this.curData;
        let start = (currentPage - 1) * this.pageSize;
        output.length = 0;
        for (let i = 0; i < this.pageSize; i++) {
          output[i] = this.totalData[start++];
          if (start > this.totalSize - 1) break;
        }
        console.log(output)
      }
    },

    deleteData (userId) {
      let form = new FormData();
      form.append("userId", userId)
      this.$http({
        headers: {
          'Content-Type': 'multipart/form-data'
        },
        method: 'post',
        url: '/api/management/delete',
        data: form
      }).then(res => {
        console.log(res);
      })
    },

    async resetPassword (userId) {
      let form = new FormData();
      form.append("userId", userId)
      let res = await this.$http({
        headers: {
          'Content-Type': 'multipart/form-data'
        },
        method: 'post',
        url: '/api/management/reset',
        data: form
      }).then(res => {
        return res.data;
      })
      return res;
    },

    handleCurrentChange (page) {
      this.currentPage = page;
      this.getData(page);
    },

    handleDelete (index, row) {
      this.$confirm('此操作将永久删除该成员, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: 'true'
      }).then(() => {
        let userId = row.userId;
        const page = this.currentPage - 1;
        this.deleteData(userId);
        this.curData.splice(index, 1)
        this.totalData.splice(page * this.pageSize + index, 1)
        this.totalSize--;
        if (this.totalData.length == 0) this.currentPage = 0;
        if (this.totalData.length <= (this.currentPage - 1) * this.pageSize && this.currentPage > 0)
          this.currentPage--;
        this.getData(this.currentPage)
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },

    handleEdit (index, row) {
      var vm = this;
      this.$confirm('此操作将重置该成员的密码, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: 'true'
      }).then(async () => {
        let userId = row.userId;
        let res = await this.resetPassword(userId);
        if (res.meta.code == 200)
          vm.$message({
            type: 'success',
            message: '重置成功!'
          });
        else {
          vm.$message({
            type: 'error',
            message: '重置失败！'
          });
        }

      }).catch(() => {
        vm.$message({
          type: 'info',
          message: '已取消重置'
        });
      });
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
</style>
