<template>
  <div class="container">
    <div class="leaderInfo">
      <el-card class="detail">
        <div class="avator">
          <el-avatar shape="square" fit="fill" :size="150"></el-avatar>
        </div>
        <div class="message">
          <ul>
            <li v-for="item in Info.msg" :key="item">{{item}}</li>
          </ul>
        </div>
      </el-card>
    </div>
    <div class="Info">
      <el-card class="skillCard">
        <span>Course</span>
        <ul>
          <li v-for="item in Info.Course" :key="item">{{item}}</li>
        </ul>
      </el-card>
      <el-card class="introduce">
        <span>Introduction</span>
        <div v-for="item in Info.Introduction" :key="item">&emsp;&emsp;{{item}}</div>
      </el-card>
    </div>
    <div class="footer">
      BUILD IN 2021
    </div>
  </div>
</template>

<script>
let Info = {
  imgSrc: '',
  msg: [
    '今天的天气真不错',
    '明天的天气也挺不错',
    '后天不行',
    '下周天天下雨',
    '有空调真好'
  ],
  Course: ['HTML', 'CSS', 'JavaScript', '正则表达式', 'Vue'],
  Introduction: [
    '中文中文中文中中文中文中文中文中文中文中文中文文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文',
    '第二段第二段'
  ]
}
export default {
  created () {
    let part = this.$route.query.part;
    if (part == 0)
      this.Info = Info;
  },
  mounted () {
    $('.detail').velocity("transition.slideDownIn", { duration: 500 });
    $('.skillCard').velocity("transition.slideLeftIn", { stagger: 450 });
    $('.introduce').velocity("transition.slideRightIn", { stagger: 450 });
  },
  data () {
    return {
      Info: {}
    };
  },
  methods: {

  }
};
</script>

<style lang="less" scoped>
.container {
  margin: 0 auto;
  width: 85%;
  min-width: 1000px;
  padding: 30px 40px;
  .leaderInfo {
    margin-bottom: 20px;
    .detail {
      width: 100%;
      height: 200px;
      display: flex;
      align-items: center;
      .avator {
        display: inline-block;
        vertical-align: top;
        height: 150px;
        width: 150px;
        width: 20%;
        margin: 10px;
      }
      .message {
        display: inline-flex;
        vertical-align: top;
        justify-content: space-between;
        box-sizing: border-box;
        // padding: 0px 50px;
        height: 150px;
        width: 70%;
        min-width: 400px;
        margin: 10px;
        li {
          line-height: 25px;
        }
      }
    }
  }
  .Info {
    display: inline-flex;
    width: 100%;
    min-width: 1000px;
    margin-bottom: 40px;
    .skillCard {
      height: 400px;
      width: 20%;
      margin-right: 20px;
      box-sizing: border-box;
      text-align: center;
      ul {
        text-align: left;
        line-height: 20px;
        word-break: break-all;
        li {
          margin-top: 25px;
        }
      }
    }
    .introduce {
      width: 80%;
      text-align: center;
      box-sizing: border-box;
      span {
        font-size: 40px;
      }
      div {
        text-align: left;
        padding: 0px 15px;
        word-break: break-all;
        font-size: 18px;
        line-height: 40px;
      }
    }
  }
  .footer {
    position: relative;
    display: flex;
    justify-content: space-around;
  }
}
</style>
