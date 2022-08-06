const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})

// module.exports = {
//   devServer: {
//     proxy: {
//       '/api': {
//         "target" : 'http://localhost:8080/',
//         "pathRewrite": { '^/': '' },
//         "changeOrigin": true,
//         "secure": false,
//         "ws": false,
//       }
//     }
//   }
// }