const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
	transpileDependencies: true,
	devServer: {
		port: 7070,
		// changeOrigin: true,
		proxy: {
			"/api": {
				target: "http://localhost:8001",
				changeOrigin: true, // 是否跨域 需要设置此值为true 才可以让本地服务代理我们发出请求
				pathRewrite: {
					"^/api": "",
				},
			},
		},
	},
});
