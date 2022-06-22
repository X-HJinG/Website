module.exports = {
  publicPath: process.env.NODE_ENV === 'development' ? '/' : './',
  devServer: {
    open: true,
    host: 'localhost',
    port: 8080
    // proxy: {
    //   //配置跨域
    //   '/api': {
    //     target: 'http://127.0.0.1:8888',
    //     ws: true,
    //     changOrigin: true, //允许跨域
    //     pathRewrie: {
    //       '^/api': '' //请求的时候使用这个api就可以
    //     }
    //   },
    //   '/static': {
    //     target: 'http://127.0.0.1:8888',
    //     ws: true,
    //     changOrigin: true, //允许跨域
    //     pathRewrie: {
    //       '^/static': '' //请求的时候使用这个api就可以
    //     }
    //   }
    // }
  },
  lintOnSave: false
}
