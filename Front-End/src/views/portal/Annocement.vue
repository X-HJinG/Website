<template>
  <div class="container">
    <el-carousel :interval="5000" type="card" height="220px">
      <el-carousel-item @click.native="dialogVisible=true">
        <img src="../../assets/images/通知.png">
      </el-carousel-item>
      <el-carousel-item>
        <img src="../../assets/images/活动.png">
      </el-carousel-item>
      <el-carousel-item>
        <img src="../../assets/images/更多信息.png">
      </el-carousel-item>
    </el-carousel>
    <div class="recruit">
      <waterfall :line-gap="250" :watch="items" class="waterfall">
        <waterfall-slot v-for="(item, index) in items" :width="item.width" :height="item.height"
          :order="index" :key="item.id" class="item" :fix-height="true">
          <el-card>
            <div>{{item.content}}</div>
            <div>
              <span>发布者: {{item.author}}</span>
              <span>联系方式</span>
              <span>{{item.contact}}</span>
            </div>
          </el-card>
        </waterfall-slot>
      </waterfall>
    </div>
    <el-dialog title="课程通知" :visible.sync="dialogVisible">
      <div class="annocement">
        <ul>
          <!-- <li v-for="item in contents" :key="item.id">{{item.content}}</li> -->
          <li v-for="item in contents" :key="item.depart">
            <span v-for="(value,key,index) in item" :key="index">{{value}}</span>
          </li>
        </ul>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import Waterfall from 'vue-waterfall/lib/waterfall'
import WaterfallSlot from 'vue-waterfall/lib/waterfall-slot'
let _ = require('lodash')
export default {
  created () {
    this.doPost();
  },
  mounted () {
    window.addEventListener('scroll', this.getList, true)
  },
  destroyed () {
    window.removeEventListener('scroll', this.getList, true)
  },
  data () {
    return {
      items: [],
      curPage: 1,
      total: 999999,
      dialogVisible: false,
      contents: [{
        depart: '前端部',
        addr: '1号楼305',
        workDay: '周日',
        time: '晚6:30~9:00',
      }, {
        depart: '后端部',
        addr: '1号楼305',
        workDay: '周日',
        time: '晚6:30~9:00',
      }, {
        depart: '网安部',
        addr: '1号楼305',
        workDay: '周日',
        time: '晚6:30~9:00',
      }, {
        depart: '游戏部',
        addr: '1号楼305',
        workDay: '周日',
        time: '晚6:30~9:00',
      }, {
        depart: '小程序',
        addr: '1号楼305',
        workDay: '周日',
        time: '晚6:30~9:00',
      }, {
        depart: '人工智能',
        addr: '1号楼305',
        workDay: '周日',
        time: '晚6:30~9:00',
      }],
    };
  },
  methods: {
    getList: _.throttle(async function () {
      if (this.isMoveBottom() && this.items.length < this.total) {
        await this.doPost();
      }
      if (this.isMoveBottom() && this.items.length >= this.total) {
        this.$notify.info({
          message: '全部公告已加载完毕',
          position: 'bottom-right'
        })
      }
    }, 200),

    isMoveBottom () {
      let scrollTop = Math.ceil(document.querySelector(".el-main").scrollTop);
      let scrollHeight = document.querySelector(".el-main").scrollHeight;
      let clientHeight = document.querySelector(".el-main").clientHeight;
      return (scrollTop + clientHeight) == scrollHeight
    },
    async doPost () {
      let url = '/api/getNotice?pageNum=' + this.curPage;
      let { data: res } = await this.$http.get(url);
      let list = res.data.list;
      this.total = res.data.total;
      if (list.length == 0) return null
      else {
        list.forEach(info => {
          let item = {};
          item.id = info.nid;
          item.content = info.mainContext;
          item.width = 1;
          item.height = 2.1 - (200 - info.mainContext.length) / 50 * 0.3
          item.author = info.userInfo.userName
          item.contact = info.remark;
          this.items.push(item)
        })
        this.curPage++;
      }
    },
  },
  components: {
    Waterfall,
    WaterfallSlot
  },
};

</script>

<style lang="less" scoped>
[v-cloak] {
  display: none;
}
.el-carousel__item {
  img {
    width: 100%;
    height: 100%;
  }
}
.container {
  margin: 0 auto;
  width: 85%;
  min-width: 1000px;
  padding: 30px 40px;
  .annocement {
    max-width: 1000px;
    margin: 0 auto;
    background-color: #fff;
    box-sizing: border-box;
    padding: 20px;
    box-shadow: 0 2px 20px 0 rgba(0, 0, 0, 0.1);
    .text {
      width: 100%;
    }
    ul {
      padding: 0;
      li {
        list-style-type: none;
        margin: 10px 0px;
        display: flex;
        justify-content: space-between;
        border-bottom: 0.5px solid rgba(0, 0, 0, 0.1);
        line-height: 2;
        span {
          width: 20%;
        }
      }
    }
    .line {
      border-left: 3px solid rgba(0, 0, 0, 0.1);
      border-right: 3px solid rgba(0, 0, 0, 0.1);
      display: inline-block;
      width: 10px;
    }
  }
  .recruit {
    max-width: 1000px;
    margin: 0px auto;
    .item {
      padding: 10px 0;
      .el-card {
        position: relative;
        display: flex;
        margin: 0 5px;
        height: 100%;
        /deep/.el-card__body {
          display: flex;
          flex-direction: column;
          justify-content: space-between;
        }
        div {
          box-sizing: border-box;
          word-break: break-all;
          left: 20px;
          right: 20px;
          bottom: 10px;
          span {
            display: block;
            margin: 8px 0;
          }
        }
      }
    }
  }
}
/deep/.el-dialog__body {
  background-image: url('../../assets/images/通知背景2.png');
  background-size: 100% 100%;
}
</style>
