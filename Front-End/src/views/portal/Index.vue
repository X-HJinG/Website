<template>
  <el-container class="container">
    <el-header class="header">
      <div class="logo">
        <img :src="require('@/assets/images/logo2.png')">
      </div>
      <el-menu :default-active="activeIndex" mode="horizontal" @select="handleSelect" router>
        <el-menu-item v-for="item in navList" :index="item.index" :key="item.name">
          <b>{{item.name}}</b>
        </el-menu-item>
      </el-menu>
    </el-header>
    <el-main class="main">
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>
let navList = [{
  name: 'About us',
  index: '/index/about-us'
}, {
  name: 'Learn more',
  index: '/index/learn-more'
}, {
  name: 'Annoncement',
  index: '/index/annoncement'
}, {
  name: 'CMS',
  index: '/cms/login'
}]
export default {
  updated () {
    this.activeIndex = this.$route.fullPath;
    document.querySelector(".el-main").scrollTop = 0;
  },
  data () {
    return {
      navList: navList,
      activeIndex: '/index/about-us'
    };
  },
  methods: {
    handleSelect (key, keyPath) {
      this.activeIndex = key;
    }
  }
};
</script>

<style lang="less" scoped>
.container {
  height: 100%;
  width: 100%;
}
.header {
  background-color: #fff;
  width: 100%;
  min-width: 1280px;
  height: 100px !important;
  display: flex;
  align-items: center;
  * {
    font-size: 20px;
  }
  .logo {
    display: flex;
    align-items: center;
    justify-content: flex-start;
    box-sizing: content-box;
    margin-right: 20px;
    height: 80%;
    img {
      height: 100%;
    }
  }
  .el-menu {
    * {
      height: 100px;
      line-height: 100px;
      font-size: 25px;
      padding: 0px 30px;
    }
  }
}

/deep/.el-menu--horizontal > .el-menu-item.is-active {
  border: transparent;
}

.main {
  background-color: #ececec;
  width: 100%;
  min-width: 1280px;
}
</style>
