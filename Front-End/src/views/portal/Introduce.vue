<template>
  <div>
    <div class="container">
      <el-card class="info" v-for="item in list" :key="item.id" shadow="always" v-show="show">
        <el-avatar shape="square" fit="fill" :size="180"></el-avatar>
        <div class="introduce">
          <ul>
            <li v-for="desc in item.desc" :key="desc">{{desc}}</li>
          </ul>
        </div>
        <div class="station">{{item.station}}</div>
      </el-card>
    </div>
    <div class="footer">
      BUILD IN 2021
    </div>
  </div>
</template>

<script>
export default {
  mounted () {
    this.show = true;
    $(".info").velocity("transition.slideLeftIn", { stagger: 500 })
  },
  data () {
    return {
      show: false,
      list: [{
        id: 1,
        desc: ['张三', '19级网络空间安全二班'],
        station: '主席'
      }, {
        id: 2,
        desc: ['李四', '19级数据科学与大数据技术二班'],
        station: '副主席'
      }, {
        id: 3,
        desc: ['王五', '19级网络工程一班'],
        station: '前端部'
      }, {
        id: 4,
        desc: ['赵六', '19级数据科学与大数据技术一班'],
        station: '后端部'
      }, {
        id: 5,
        desc: ['孔七', '19级网络空间安全一班'],
        station: '网安部'
      }, {
        id: 6,
        desc: ['刘八', '19级网络空间安全二班'],
        station: '小程序'
      }, {
        id: 7,
        desc: ['谢九', '19级网络空间安全一班'],
        station: '游戏部'
      }]
    };
  },
  methods: {

  }
};
</script>

<style lang="less" scoped>
.test {
  width: 200px;
  background-color: grey;
  height: 100px;
  margin: 10px 0px;
}
.container {
  width: 100%;
  height: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}
.info {
  // display: flex;
  // justify-content: flex-start;
  border-radius: 30px;
  margin: 15px 0;
  width: 80%;
  /deep/.el-card__body {
    padding: 30px;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
  .introduce {
    width: 70%;
    align-self: flex-start;
    ul {
      margin: 0;
      padding: 0;
      li {
        line-height: 1.2;
        margin: 5px;
        list-style-type: none;
      }
      li:first-child {
        font-size: 1.8rem;
      }
      li::before {
        content: '\226B';
        font-size: 1.2rem;
        margin-right: 5px;
      }
    }
  }
  .station {
    // height: 100%;
    box-sizing: content-box;
    text-align: center;
    -webkit-writing-mode: vertical-rl;
    writing-mode: vertical-rl;
    font-size: 2rem;
    font-weight: 200;
    letter-spacing: 8px;
  }
}
.footer {
  text-align: center;
}
</style>
