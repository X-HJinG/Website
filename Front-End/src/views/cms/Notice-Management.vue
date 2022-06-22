<template>
  <div>
    <el-card>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/cms/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>信息专栏</el-breadcrumb-item>
        <el-breadcrumb-item>公告管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-card>
    <el-card class="info">
      <el-table :data="curData" stripe style="width: 100%" :key="time" v-loading="loading">
        <el-table-column prop="uploadtime" label="日期" width="180">
          <template scope="scope">
            {{DateFormat(scope.row.uploadtime)}}
          </template>
        </el-table-column>
        <el-table-column prop="mainContext" label="信息" show-overflow-tooltip>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">
              删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination layout="prev, pager, next" :page-size="pageSize" :total="totalSize"
        class="page" @current-change="handleCurrentChange">
      </el-pagination>
    </el-card>
    <el-dialog :visible.sync="dialogFormVisible" center>
      <el-form ref="form" :rules="rules" :model="form">
        <el-form-item prop="curContext">
          <el-input type="textarea" placeholder="请输入内容" maxlength="200" show-word-limit
            resize="none" rows="8" class="textarea" v-model="form.curContext">
          </el-input>
        </el-form-item>
        <el-form-item prop="remark">
          <el-input v-model="form.remark.text">
            <el-select v-model="form.remark.type" slot="prepend" placeholder="请选择">
              <el-option label="WX" value="WX"></el-option>
              <el-option label="QQ" value="QQ"></el-option>
              <el-option label="Tel" value="Tel"></el-option>
            </el-select>
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="reset">取 消</el-button>
        <el-button type="primary" @click="updateNotice">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  async created () {
    let account = {};
    account.userId = window.sessionStorage.getItem("userId");
    this.getInit(account);
  },
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
        curContext: '',
        curNid: '',
        remark: {
          type: '',
          text: '',
        }
      },
      rules: {
        curContext: [
          { validator: validateContext, trigger: 'blur' }
        ],
        remark: [
          { validator: validateRemark, trigger: 'blur' }
        ]
      },
      totalData: [],
      curData: [],
      pageSize: 7,
      totalSize: 0,
      currentPage: 0,
      time: 0,
      curIndex: -1,
      dialogFormVisible: false,
      loading: true
    };
  },
  methods: {
    DateFormat (date) {
      return new Date(date).format("yyyy-MM-dd")
    },
    async getInit (account) {
      let res = await this.$http.post("/api/notice/output", account);
      const result = res.data.data;
      if (result.length != 0) {
        this.totalData = result;
        this.totalSize = result.length;
        this.currentPage = 1;
        this.getData(this.currentPage);
      }
      this.loading = false;
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
      }
    },
    async deleteData (nid) {
      let notice = {};
      notice.nid = nid;
      let { data: res } = await this.$http.post("/api/notice/delete", notice);
      // return res;
      if (res.data.meta.code == 200) {
        this.$message.success("删除成功");
      }
    },

    updatePage (index) {
      console.log("update")
      const page = this.currentPage - 1;
      this.curData.splice(index, 1)
      this.totalData.splice(page * this.pageSize + index, 1)
      this.totalSize--;
      if (this.totalData.length == 0) this.currentPage = 0;
      if (this.totalData.length <= (this.currentPage - 1) * this.pageSize && this.currentPage > 0)
        this.currentPage--;
      this.getData(this.currentPage);
    },

    handleCurrentChange (page) {
      this.currentPage = page;
      this.getData(page);
    },
    handleEdit (index, row) {
      this.dialogFormVisible = true;
      this.form.curContext = row.mainContext;
      this.curNid = row.nid;
      this.form.remark.type = row.remark.split(' : ')[0];
      this.form.remark.text = row.remark.split(' : ')[1];
      this.curIndex = index;
    },
    handleDelete (index, row) {
      this.$confirm('此操作将永久删除该信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: 'true'
      }).then(() => {
        let nid = row.nid;
        this.deleteData(nid);
        this.updatePage(index);
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    },
    updateNotice () {
      this.dialogFormVisible = false;
      this.$refs['form'].validate(async valid => {
        if (valid) {
          let date = new Date();
          let notice = {}
          notice.mainContext = this.form.curContext;
          notice.uploadtime = date.getTime();
          notice.nid = this.curNid;
          notice.remark = this.form.remark.type + " : " + this.form.remark.text;
          let res = await this.$http.post("/api/notice/update", notice);
          if (res.data.meta.code == 200) {
            this.curData[this.curIndex].mainContext = this.form.curContext;
            console.log(this.curData[this.curIndex]);
            this.curData[this.curIndex].remark = notice.remark;
            this.$message.success("上传成功");
          }
          else this.$message.error("出错了");
        }
      })
    },
    reset () {
      this.$refs['form'].resetFields();
      this.dialogFormVisible = false;
    }
  }
};
</script>

<style lang="less" scoped>
.info {
  margin-top: 10px;
}
.page {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}
.el-select {
  width: 100px;
}
</style>
