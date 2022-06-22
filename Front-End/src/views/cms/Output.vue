<template>
  <div>
    <el-card>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/cms/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>数据导出</el-breadcrumb-item>
        <el-breadcrumb-item>出勤数据</el-breadcrumb-item>
      </el-breadcrumb>
    </el-card>
    <el-card>
      <el-button type="success">部门名单导出</el-button>
    </el-card>
  </div>
</template>

<script>
export default {
  data () {
    return {

    };
  },
  methods: {

  }
};
</script>

<style lang="less" scoped>
.el-card {
  margin-bottom: 15px;
}
</style>
