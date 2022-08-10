const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})

module.exports = {
  devServer: {
    proxy: {
      '/api': {
        "target" : 'https://i7d109.p.ssafy.io/',
        // "target" : 'http://localhost:8080',
        "pathRewrite": { '^/': '' },
        "changeOrigin": true,
        "secure": false,
        "ws": false,
      }
    }
  }

}

