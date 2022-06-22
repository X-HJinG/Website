<template>
  <el-container class="home-container">
    <el-header :height="'80px'">
      <div @click="toIndex">
        <img src="../../assets/images/logo.png" alt="" class="logo" />
        <span>后台管理系统</span>
      </div>
      <div>
        <span>欢迎 {{userName}} 登陆系统</span>
        <el-button class="btn" @click="logout()">退出</el-button>
      </div>
    </el-header>
    <el-container>
      <el-aside :width="isCollapse ? '64px' : '200px'">
        <div class="toggle-button" @click="toggleCollapse()">|||</div>
        <el-menu :default-active="activePath" class="el-menu-vertical-demo" background-color="#fff"
          text-color="#4a5064" active-text-color="#409EFF" unique-opened :collapse="isCollapse"
          :collapse-transition="false" router>
          <el-submenu :index="item.id + ''" v-for="item in menuList" :key="item.id">
            <template slot="title">
              <i :class="item.iconClass"></i>
              <span>{{ item.title }}</span>
            </template>
            <el-menu-item :index="subItem.path" v-for="subItem in item.children" :key="subItem.id"
              @click="saveNavstate(subItem.path)">{{ subItem.detail }}</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template> 

<script>
export default {
  async created () {
    this.userName = window.sessionStorage.getItem("userName")
    this.activePath = window.sessionStorage.getItem('activePath')
    this.getMentList();
  },
  data () {
    return {
      menuList: [],
      userName: "",
      isCollapse: false,
      activePath: ''
    }
  },
  methods: {
    async getMentList () {
      this.$http.get('/api/resources').then(ret => {
        this.menuList = ret.data;
      })
    },
    toggleCollapse () {
      this.isCollapse = !this.isCollapse
    },
    saveNavstate (activePath) {
      window.sessionStorage.setItem('activePath', activePath)
      this.activePath = activePath
    },
    logout () {
      window.sessionStorage.clear();
      this.$router.push('/cms/login')
    },
    toIndex () {
      this.$router.push('/index')
    }
  }
}
</script>

<style lang="less" scoped>
.home-container {
  height: 100%;
}
.btn {
  display: flex;
  align-self: center;
  height: 70%;
  font-size: 15px;
  align-items: center;
  margin-left: 20px;
}
.el-header {
  // background-color: grey;
  background-color: #fff;
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  color: #000;
  font-size: 20px;
  > div {
    display: flex;
    align-items: center;
    span {
      margin-left: 15px;
    }
  }
  .logo {
    height: 80%;
    margin-left: 20px;
  }
}
.el-aside {
  background-color: rgba(144, 147, 153, 0.4);
  .el-menu {
    border-right: none;
  }
}
.el-main {
  background-color: #f5f5f5;
}

.toggle-button {
  background-color: rgba(74, 80, 100, 0.2);
  font-size: 10px;
  line-height: 24px;
  color: #fff;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;
}
</style>
