const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})

module.exports = {
  devServer: {
    proxy: {
      '/api': {

        "target" : 'https://i7d109.p.ssafy.io/',
        
        "pathRewrite": { '^/': '' },
        "changeOrigin": true,
        "secure": false,
        "ws": false,
      }
    }
  }

}

