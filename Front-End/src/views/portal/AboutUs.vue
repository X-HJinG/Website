<template>
  <div class="container">
    <div id="animate-box">
      <div class="team" :body-style="{ padding: '0px' }">
        <div class="leftPart">
          <div class="abouTime">
            <div class="date">
              <div class="day">{{day}}</div>
              <div class="week">{{week}}</div>
              <div class="month">{{month}}</div>
            </div>
            <div class="blank"></div>
          </div>
          <div class="aboutTeam">
            <span>This website was built in 2021.</span>
          </div>
        </div>
        <div class="rightPart">
          <div class="imgContainer" @mouseover.stop="open(-1)" @mouseleave.stop="close">
            <img :src="require('@/assets/images/team.png')">
            <transition name="el-fade-in-linear">
              <div class="mask" v-if="curIndex == -1">
                <button @click="toDetail(-1)">See Detail</button>
              </div>
            </transition>
          </div>
          <div class=" times">
            <div class="stats">
              <div class="title">STATISTICS</div>
              <div class="statsSet">
                <div class="department">
                  <span>DEPARTMENT :</span>
                  <div>{{deptNum}}</div>
                </div>
                <div class="awards">
                  <span>AWARDS :</span>
                  <div>104</div>
                </div>
              </div>
            </div>
            <div class="btn">
              <transition name="el-fade-in-linear">
                <button @click="toDetail(-2)">
                  <span>See Case&emsp;</span>
                  <span class="iconfont icon-you"></span>
                </button>
              </transition>
            </div>
          </div>
        </div>
      </div>
      <div class="next">
        <span id="toNext" class="iconfont icon-xiangxia" @click="scrollList"></span>
      </div>
      <div class="list">
        <div class="leftList">
          <template v-for="(item,i) in list">
            <div class="item" v-if="item.id%2==0" :key="item.id">
              <div class="imgDesc">
                <div class="pName">{{item.name}}</div>
                <div class="imgContainer" @mouseover="open(i)" @mouseleave.stop="close">
                  <img :src="require('@/assets/images/'+item.name+'.jpg')">
                  <transition name="el-fade-in-linear">
                    <div class="mask" v-if="curIndex == i">
                      <button @click="toDetail(i)">See Detail</button>
                    </div>
                  </transition>
                </div>
              </div>
              <div class="desc">
                <div class="first">
                  <span>{{item.abbr}}</span>
                  <span>{{item.fullName}}</span>
                </div>
                <div class="second setRight">
                  <div>{{item.desc}}</div>
                </div>
              </div>
            </div>
          </template>
        </div>
        <div class="divider">
          <div class="decorate">
            <div id="left-divider" v-for="item in 4" :key="item"></div>
          </div>
          <div class="decorate">
            <div id="right-divider" v-for="item in 5" :key="item"></div>
          </div>
        </div>
        <div class="rightList">
          <template v-for="(item,i) in list">
            <div class="item" v-if="item.id%2!=0" :key="item.id">
              <div class="imgDesc">
                <div class="imgContainer" @mouseover="open(i)" @mouseleave.stop="close">
                  <img :src="require('@/assets/images/'+item.name+'.jpg')">
                  <transition name="el-fade-in-linear">
                    <div class="mask" v-if="curIndex == i">
                      <button @click="toDetail(i)">See Detail</button>
                    </div>
                  </transition>
                </div>
                <div class="pName">{{item.name}}</div>
              </div>
              <div class="desc">
                <div class="first">
                  <span>{{item.fullName}}</span>
                  <span>{{item.abbr}}</span>
                </div>
                <div class="second setLeft">
                  <div>{{item.desc}}</div>
                </div>
              </div>
            </div>
          </template>
        </div>
      </div>
    </div>
    <div class="footer">
      BUILD IN 2021
    </div>
  </div>
</template>

<script>
let months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
let weeks = ["Sun.", "Mon.", "Tues.", "Wed.", "Thur.", "Fri.", "Sat.", "Sun."];
export default {
  created () {
    let now = new Date();
    this.day = now.getDate();
    this.month = months[now.getMonth()] + " " + now.getFullYear();
    this.week = weeks[now.getDay()];
    this.deptNum = this.list.length;
  },
  mounted () {
    $('#animate-box').velocity("transition.slideUpIn", { stagger: 250 })
  },
  data () {
    return {
      day: '',
      month: '',
      week: '',
      curIndex: -100,
      deptNum: '',
      list: [{
        id: 1,
        name: '前端',
        abbr: 'FE',
        fullName: 'Front-End',
        desc: '前端技术一般分为前端设计和前端开发，前端设计一般可以理解为网站的视觉设计，前端开发则是网站的前台代码实现。前端开发主要职能就是把网站的界面更好地呈现给用户。'
      }, {
        id: 2,
        name: '后端',
        abbr: 'RD',
        fullName: 'Research and Development',
        desc: '前端技术一般分为前端设计和前端开发，前端设计一般可以理解为网站的视觉设计，前端开发则是网站的前台代码实现。前端开发主要职能就是把网站的界面更好地呈现给用户。'
      }, {
        id: 3,
        name: '网安',
        abbr: 'CS',
        fullName: 'Cyber Security',
        desc: '前端技术一般分为前端设计和前端开发，前端设计一般可以理解为网站的视觉设计，前端开发则是网站的前台代码实现。前端开发主要职能就是把网站的界面更好地呈现给用户。'
      }, {
        id: 4,
        name: '小程序',
        abbr: 'MP',
        fullName: 'Mini Programs',
        desc: '前端技术一般分为前端设计和前端开发，前端设计一般可以理解为网站的视觉设计，前端开发则是网站的前台代码实现。前端开发主要职能就是把网站的界面更好地呈现给用户。'
      }, {
        id: 5,
        name: '游戏',
        abbr: 'GD',
        fullName: 'Game Development',
        desc: '前端技术一般分为前端设计和前端开发，前端设计一般可以理解为网站的视觉设计，前端开发则是网站的前台代码实现。前端开发主要职能就是把网站的界面更好地呈现给用户。'
      }, {
        id: 6,
        name: '人工智能',
        abbr: 'AI',
        fullName: 'Artificial Intelligence',
        desc: '前端技术一般分为前端设计和前端开发，前端设计一般可以理解为网站的视觉设计，前端开发则是网站的前台代码实现。前端开发主要职能就是把网站的界面更好地呈现给用户。'
      }]
    };
  },
  methods: {
    toDetail (index) {
      if (index == -1) this.$router.push('/index/about-us/introduce');
      else if (index == -2) this.$router.push('/index/learn-more');
      else {
        let { href } = this.$router.resolve({ path: '/index/about-us/detail?part=' + index });
        window.open(href, '_blank');
      };
    },
    open (i) {
      this.curIndex = i;
    },
    close () {
      this.curIndex = -100;
    },
    scrollList () {
      document.querySelector(".rightList>.item").scrollIntoView({ behavior: "smooth" })
    }
  }
};
</script>

<style lang="less" scoped>
.container {
  height: 100vh;
  margin: 0 auto;
  width: 85%;
  min-width: 1000px;
  padding: 20px 0px;
}

.team {
  background-color: white;
  box-sizing: border-box;
  border: 1px solid #ececec;
  display: flex;
  height: 500px;
  width: 100%;
  min-width: 1000px;
  .leftPart {
    width: 40%;
    display: flex;
    flex-direction: column;
  }
  .abouTime {
    height: 50%;
    width: 100%;
    display: flex;
    .date {
      display: flex;
      flex-direction: column;
      align-items: flex-start;
      justify-content: space-evenly;
      width: 40%;
      padding: 0 5%;
      box-sizing: border-box;
      background-image: require('@/assets/images/pixel-pattern.png');
      background-repeat: repeat;
      background-size: 2px 2px;
      border: 1px solid #ececec;
      .day {
        font-size: 6rem;
        font-weight: 800;
        color: #9a9a9a;
      }
      .week {
        color: grey;
        font-size: 1rem;
      }
      .month {
        color: grey;
        font-size: 1.25rem;
      }
    }
    .blank {
      width: 60%;
      background-color: #fff;
      border-right: 1px solid #ececec;
      box-sizing: border-box;
    }
  }
  .aboutTeam {
    height: 50%;
    background-color: #fff;
    border-top: 1px solid #ececec;
    border-right: 1px solid #ececec;
    box-sizing: border-box;
    padding: 10%;
    font-size: 1.5rem;
  }
  .rightPart {
    position: relative;
    width: 60%;
    .imgContainer {
      position: relative;
      width: 100%;
      height: 75%;
      img {
        width: 100%;
        height: 100%;
      }
    }
  }
  .times {
    display: inline-flex;
    box-sizing: border-box;
    height: 25%;
    border-top: 1px solid #ececec;
    width: 100%;
    .stats {
      width: 60%;
      background-image: require('@/assets/images/pixel-pattern.png');
      background-repeat: repeat;
      background-size: 2px 2px;
      border: 1px solid #ececec;
      .title {
        height: 50%;
        border-bottom: 2px solid #ececec;
        box-sizing: border-box;
        padding: 15px;
        color: #9a9a9a;
        font-size: 1.2rem;
        font-weight: 500;
      }
      .statsSet {
        height: 50%;
        display: flex;
        justify-content: space-around;
        .department,
        .awards {
          display: flex;
          justify-content: space-between;
          width: 50%;
          height: 100%;
          box-sizing: border-box;
          padding: 12px;
          color: #9a9a9a;
          font-size: 0.8rem;
          font-weight: 200;
          div {
            display: inline-block;
            line-height: 100%;
            font-size: 2.5rem;
            font-weight: 400;
            color: black;
          }
        }
        .department {
          border-right: 2px solid #ececec;
        }
      }
    }
    .btn {
      width: 40%;
      display: flex;
      align-items: flex-end;
      justify-content: flex-end;
      box-sizing: border-box;
      padding: 15px;
      button {
        padding: 8px;
        border: none;
        border-radius: 5px;
        color: #fff;
        background-color: #404040;
        cursor: pointer;
      }
    }
  }
}

.next {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  box-sizing: border-box;
  height: 125px;
  width: 55%;
  padding: 20px;
  background-color: #fff;
  span {
    font-size: 30px;
    color: #9a9a9a;
    cursor: pointer;
  }
}

.mask {
  position: absolute;
  top: 0;
  background-color: rgba(63, 63, 63, 0.9);
  width: 100%;
  height: 100%;
  button {
    position: relative;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 100px;
    height: 40px;
    border-radius: 8px;
    border: none;
    font-size: 1rem;
  }
  button:hover {
    background-color: transparent;
    border: 1px solid white;
    color: white;
    transition: 0.2s linear;
    cursor: pointer;
  }
}

.item {
  background-color: white;
  .imgDesc {
    display: flex;
    height: 250px;
    .pName {
      width: 15%;
      -webkit-writing-mode: vertical-rl;
      writing-mode: vertical-rl;
      display: inline-flex;
      justify-content: center;
      align-items: center;
      padding: 20px 0;
      font-size: 1.25rem;
      background-image: require('@/assets/images/pixel-pattern.png');
      background-repeat: repeat;
      background-size: 2px 2px;
    }
    .imgContainer {
      position: relative;
      width: 100%;
      img {
        height: 100%;
        width: 100%;
      }
    }
  }
  .desc {
    height: 200px;
    display: flex;
    flex-direction: column;
    padding: 14px;
    padding-top: 0px;
    line-height: 30px;
    box-sizing: content-box;
    .first {
      display: inline-flex;
      justify-content: space-between;
      border-bottom: 1.5px solid #ececec;
      span {
        font-size: 0.8rem;
        line-height: 50px;
        font-weight: 900;
      }
    }
    .second {
      width: 85%;
      height: 100%;
      word-break: break-all;
      display: flex;
      align-items: center;
    }
  }
  .setLeft {
    border-right: 1.5px solid #ececec;
    padding-right: 20px;
    text-align: left;
    align-self: flex-start;
  }
  .setRight {
    border-left: 1.5px solid #ececec;
    padding-left: 20px;
    text-align: right;
    align-self: flex-end;
  }
}

.list {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  flex-wrap: wrap;
  .divider {
    background-color: white;
    display: inline-flex;
    align-self: stretch;
    flex: 1;
    .decorate {
      background-image: require('@/assets/images/pixel-pattern.png');
      background-repeat: repeat;
      background-size: 2px 2px;
      width: 50%;
      border: 1px solid #ececec;
      overflow: hidden;
      #left-divider {
        width: 100%;
        border-bottom: 2px solid #ececec;
        height: 25%;
        box-sizing: border-box;
      }
      #right-divider {
        width: 100%;
        border-bottom: 2px solid #ececec;
        height: 20%;
        box-sizing: border-box;
      }
      #left-divider:last-child,
      #right-divider:last-child {
        border-bottom: none;
      }
    }
  }
  .leftList {
    width: 45%;
    .item {
      margin-top: 200px;
    }
    .item:first-child {
      margin-top: 125+200px;
    }
  }
  .rightList {
    width: 45%;
    .item {
      margin-top: 200px;
    }
  }
}

.footer {
  position: relative;
  display: flex;
  justify-content: space-around;
  margin-top: 20px;
}
</style>
