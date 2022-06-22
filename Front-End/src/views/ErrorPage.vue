<template>
  <div class="container">
    <img :src="require('../assets/images/'+Status+'.jpg')" class="img" alt="" srcset="">
  </div>
</template>

<script>
export default {
  created () {
    this.getImages();
  },
  data () {
    return {
      Status: ''
    };
  },
  methods: {
    getImages () {
      let status = this.$route.fullPath.split("?")[1];
      switch (status) {
        case '100': ;
        case '200': ;
        case '400': ;
        case '401': ;
        case '403': ;
        case '404': ;
        case '405': ;
        case '408': ;
        case '409': ;
        case '413': ;
        case '414': ;
        case '426': ;
        case '429': ;
        case '431': ;
        case '450': ;
        case '500': ;
        case '599': this.Status = status;
          break;
        default: this.Status = '404';
          break;
      }
    }
  }
};
</script>

<style lang="less" scoped>
.container {
  position: relative;
  background-color: #000;
  width: 100%;
  height: 100%;
  overflow: hidden;
}
.img {
  position: absolute;
  width: 600px;
  transform: translateX(50%);
}
</style>
