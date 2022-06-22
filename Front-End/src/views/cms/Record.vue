<template>
  <div>
    <el-card>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>考勤记录</el-breadcrumb-item>
        <el-breadcrumb-item>出勤查询</el-breadcrumb-item>
      </el-breadcrumb>
    </el-card>
    <el-card class="info">
      <el-table :data="recordData" style="width: 100%" v-loading="loading">
        <el-table-column prop="historyDate" label="日期" width="180">
          <template scope="scope">
            {{DateFormat(scope.row.historyDate)}}
          </template>
        </el-table-column>
        <el-table-column prop="address" label="地址" width="180">
        </el-table-column>
        <el-table-column prop="remark" label="备注"> </el-table-column>
        <el-table-column prop="radio" label="状态">
          <template slot-scope="scope">
            <el-radio-group v-model="scope.row.status" disabled>
              <el-radio :label="1" class="normal">正常</el-radio>
              <el-radio :label="2" class="check">请假</el-radio>
              <el-radio :label="3" class="leave">缺勤</el-radio>
            </el-radio-group>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination layout="prev, pager, next" :page-size="8" :total="12" class="page">
      </el-pagination>
    </el-card>

  </div>
</template>

<script>
export default {
  async created () {
    let userId = window.sessionStorage.getItem("userId")
    let form = new FormData();
    form.append("userId", userId)
    let res = await this.$http.post("/api/record", form)
    if (res.data.meta.code == 200) this.loading = false;
    this.recordData = res.data.data
  },
  data () {
    return {
      recordData: [],
      loading: true
    }
  },
  methods: {
    DateFormat (date) {
      return new Date(date).format("yyyy-MM-dd")
    },
  }
}
</script>

<style lang="less" scoped>
.info {
  margin-top: 10px;
}
/deep/.normal .el-radio__input.is-checked + .el-radio__label {
  color: #32cd32 !important;
}
/deep/.normal .el-radio__input.is-checked .el-radio__inner {
  background: #32cd32 !important;
  border-color: #32cd32 !important;
}

/deep/.check .el-radio__input.is-checked + .el-radio__label {
  color: #fd7624 !important;
}
/deep/.check .el-radio__input.is-checked .el-radio__inner {
  background: #fd7624 !important;
  border-color: #fd7624 !important;
}

/deep/.leave .el-radio__input.is-checked + .el-radio__label {
  color: #ff0000 !important;
}
/deep/.leave .el-radio__input.is-checked .el-radio__inner {
  background: #ff0000 !important;
  border-color: #ff0000 !important;
}
/deep/.el-radio__inner::after {
  position: relative;
}
.page {
  display: flex;
  justify-content: center;
  margin-top: 15px;
}
</style>
