const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true
})


// 跨域配置
module.exports = {
    devServer: {
        port: 8888,
        proxy: {
            '/api': {              //设置拦截器  拦截器格式
                target: 'http://localhost:8080',     //代理的目标地址
                changeOrigin: true,
                pathRewrite: {
                    '/api': '' //选择忽略拦截器里面的单词
                }
            }
        }
    }
}