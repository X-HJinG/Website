<template>
  <div class="container">
    <div class="nav" :style="{marginTop:top}">
      <el-timeline>
        <el-timeline-item v-for="(item, index) in navList" :key="index" class="nav-li"
          :class="{active:curIndex==index}" @click.native="navTo(index)">
          {{item.content}}
        </el-timeline-item>
      </el-timeline>
    </div>
    <div class="content">
      <div class="awards" v-for="(item,index) in navList" :key="index">
        <div class="desc">
          <div>
            <div class="title">{{item.content}}</div>
            <div class="itemDesc">
              <span>{{item.desc}}</span>
              <div><a :href="item.url" target="_blank">{{item.url}}</a></div>
            </div>
          </div>
          <div id="btns">
            <span class="iconfont icon-up" @click="toTop(index)"></span>
            <span class="iconfont icon-down" @click="toBottom(index)"></span>
          </div>
        </div>
        <div class="info">
          <div class="card" v-for="card in 20" :key="card">
            <div class="text">
              <div>第一届挑战杯省赛银奖</div>
              <div class="time">————&emsp;张阿森&emsp;&emsp;2018.08.21</div>
            </div>
            <div class="detail" @click="drawer = true">
              <span>详情</span>
              <span class="iconfont icon-zuo left"></span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <el-drawer title="详情页" :size="'40%'" :visible.sync="drawer" :direction="direction">
      <div class="details">
        <div class="title">第1届挑战杯</div>
        <div class="team">
          <div>
            <span>负责人:</span>
            <div>
              李四
            </div>
          </div>
          <div>
            <span>团队成员:</span>
            <div>王八、张三、六六、六六、六六、六六、六六、六六、六六、六六、六六</div>
          </div>
          <div>
            <span>指导老师:</span>
            <div>老李、老李、老李、老李</div>
          </div>
        </div>
        <img src="../../assets/images/logo2.png" alt="" srcset="">
      </div>

    </el-drawer>
  </div>
</template>

<script>
let timer = null;
export default {
  mounted () {
    window.addEventListener('scroll', this.fix, true)
    $(".content").velocity("transition.slideUpIn", { duration: 2000 })
  },
  destroyed () {
    window.removeEventListener('scroll', this.fix, true)
  },
  data () {
    return {
      drawer: false,
      direction: 'rtl',
      top: '0',
      curIndex: 0,
      isActive: false,
      navList: [{
        content: '挑战杯',
        desc: '“挑战杯”全国大学生系列科技学术竞赛的简称，是由共青团中央、中国科协、教育部和全国学联共同主办的全国性的大学生课外学术实践竞赛。“挑战杯”竞赛在中国共有两个并列项目，一个是“挑战杯”中国大学生创业计划竞赛，另一个则是“挑战杯”全国大学生课外学术科技作品竞赛。这两个项目的全国竞赛交叉轮流开展，每个项目每两年举办一届。',
        url: 'http://www.tiaozhanbei.net'
      }, {
        content: '互联网+',
        desc: '“互联网+”大学生创新创业大赛，由教育部与政府、各高校共同主办。大赛旨在深化高等教育综合改革，激发大学生的创造力，培养造就“大众创业、万众创新”的主力军；推动赛事成果转化，促进“互联网+”新业态形成，服务经济提质增效升级；以创新引领创业、创业带动就业，推动高校毕业生更高质量创业就业。',
        url: 'https://cy.ncss.cn/index.html'
      }, {
        content: '大创项目',
        desc: '通过实施国家级大学生创新创业训练计划，促进高等学校转变教育思想观念，改革人才培养模式，强化创新创业能力训练，增强高校学生的创新能力和在创新基础上的创业能力，培养适应创新型国家建设需要的高水平创新人才。国家级大学生创新创业训练计划内容包括创新训练项目、创业训练项目和创业实践项目三类。',
        url: 'http://gjcxcy.bjtu.edu.cn/Index.aspx'
      }, {
        content: '蓝桥杯',
        desc: '为促进软件和信息领域专业技术人才培养，提升高校毕业生的就业竞争力，由教育部就业指导中心支持，工业和信息化部人才交流中心举办蓝桥杯大赛。十二年来，包括北大、清华在内的超过 1500 余所院校，累计50万余名学子报名参赛，IBM、百度等知名企业全程参与，成为国内始终领跑的人才培养选拔模式并获得行业深度认可的IT类科技竞赛。',
        url: 'https://dasai.lanqiao.cn'
      }, {
        content: '计算机设计大赛',
        desc: '中国大学生计算机设计大赛（下面简称“大赛”）是由教育部高等学校计算机类专业教学指导委员会、教育部高等学校软件工程专业教学指导委员会、教育部高等学校大学计算机课程教学指导委员会、教育部高等学校文科计算机基础教学指导分委员会、中国教育电视台联合主办。',
        url: 'http://jsjds.ruc.edu.cn'
      }, {
        content: '其他赛事'
      }]
    };
  },
  methods: {
    fix () {
      let height = document.querySelector(".main").scrollTop;
      this.top = height + "px";
    },
    navTo (index) {
      this.curIndex = index
      document.querySelectorAll(".awards")[index].scrollIntoView({ behavior: "smooth" })
    },
    toTop (index) {
      let el = document.querySelectorAll(".info")[index];
      if (timer != null) clearInterval(timer);
      timer = setInterval(() => {
        el.scrollTop -= 50;
        if (el.scrollTop == 0) {
          clearInterval(timer);
        }
      }, 1)
    },
    toBottom (index) {
      let el = document.querySelectorAll(".info")[index];
      let oldscrollTop = -1000;
      if (timer != null) clearInterval(timer)
      timer = setInterval(() => {
        oldscrollTop = el.scrollTop;
        el.scrollTop += 50;
        if (oldscrollTop == el.scrollTop) {
          clearInterval(timer);
        }
      }, 1)
    }
  }
};
</script>

<style lang="less" scoped>
.container {
  position: relative;
  display: flex;
  width: 100%;
}
.nav {
  width: 20%;
  height: 100%;
  box-sizing: border-box;
  padding: 40px 5px;
  .nav-li {
    font-size: 16px;
    /deep/.el-timeline-item__tail {
      border-left: 4px solid #fff;
    }
    /deep/.el-timeline-item__node {
      width: 20px;
      height: 20px;
      left: -4px;
      background-color: #fff;
      box-sizing: border-box;
      cursor: pointer;
    }
  }
  .nav-li:hover {
    /deep/.el-timeline-item__node {
      background-color: #409eff;
      box-sizing: border-box;
      border: 6px solid #fff;
    }
    /deep/.el-timeline-item__content {
      color: #409eff;
    }
  }
  .active {
    /deep/.el-timeline-item__node {
      background-color: #fff;
      box-sizing: border-box;
      border: 6px solid #409eff;
    }
    /deep/.el-timeline-item__content {
      color: #409eff;
    }
  }
}
.content {
  width: 80%;
  height: 100%;
  overflow-y: hidden;
  position: relative;
  .awards {
    width: 100%;
    height: 100%;
    display: flex;
    .desc {
      color: rgba(0, 0, 0, 0.4);
      width: 30%;
      height: 100%;
      padding: 30px 0 30px 30px;
      padding-bottom: 15px;
      box-sizing: border-box;
      display: flex;
      flex-flow: wrap;
      align-content: space-between;
      .title {
        color: rgba(0, 0, 0, 0.6);
        font-size: 1.8rem;
        font-weight: bold;
        margin-bottom: 10px;
      }
      .itemDesc {
        line-height: 28px;
      }
      #btns {
        display: flex;
        width: 100%;
        justify-content: space-around;
        span {
          font-size: 32px;
          cursor: pointer;
        }
        span:hover {
          transition-duration: 0.5s;
          color: #fff;
        }
      }
    }
    .info {
      width: 70%;
      overflow-y: scroll;
      display: flex;
      justify-content: flex-end;
      align-content: flex-start;
      flex-wrap: wrap;
      .card {
        width: 90%;
        height: 25%;
        min-height: 120px;
        margin-top: 20px;
        display: flex;
        box-sizing: border-box;
        justify-content: space-between;
        background-color: #fff;
        .text {
          display: flex;
          flex-direction: column;
          justify-content: space-around;
          box-sizing: border-box;
          width: 90%;
          padding: 15px;
          font-size: 1.2rem;
          .time {
            font-size: 1rem;
            color: rgba(0, 0, 0, 0.5);
          }
        }
        .detail {
          width: 10%;
          display: flex;
          justify-content: space-around;
          align-items: center;
          background-color: #ffd700;
          -webkit-writing-mode: vertical-rl;
          writing-mode: vertical-rl;
          cursor: pointer;
        }
        .detail:hover {
          background-color: rgba(255, 215, 0, 0.5);
          transition-duration: 0.4s;
          .left {
            transition-duration: 0.6s;
            transform: translateX(-35%);
          }
        }
      }
      .card:last-child {
        margin-bottom: 20px;
      }
    }
  }
  .awards:nth-child(2n + 1) {
    background-color: rgba(192, 196, 204, 0.4);
  }
  .awards:nth-child(2n) {
    background-color: rgba(144, 147, 153, 0.4);
  }
}

/deep/.el-drawer__body {
  padding: 20px;
}

.details {
  width: 100%;
  img {
    width: 100%;
  }
  .title {
    margin-bottom: 25px;
    font-size: 1.2rem;
    font-weight: 600;
  }
}

.team {
  margin-bottom: 30px;
  word-break: break-all;
  > div {
    display: flex;
    margin-bottom: 25px;
  }
  span {
    width: 20%;
    flex-shrink: 0;
  }
}
</style>
