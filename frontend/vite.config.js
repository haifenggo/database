import {fileURLToPath, URL} from "node:url";

import {defineConfig} from "vite";
import vue from "@vitejs/plugin-vue";
import AutoImport from "unplugin-auto-import/vite";
import Components from "unplugin-vue-components/vite";
import {ElementPlusResolver} from "unplugin-vue-components/resolvers";

// https://vitejs.dev/config/
export default defineConfig({
    resolve: {
        alias: {
            "@": fileURLToPath(new URL("./src", import.meta.url)),
        },
    },

    plugins: [
        AutoImport({
            resolvers: [ElementPlusResolver()],
        }),
        Components({
            resolvers: [ElementPlusResolver()],
        }),
        vue(),
    ],
    server: {
        port: 8888,    //自定义启动时的端口
        proxy: {
            "/api": {
                target: "http://localhost:8880",
                changeOrigin: true, // 允许跨域
                secure: false,  //忽略安全证书
                rewrite: (path) => path.replace(/^\/api/, ''), // 重写路径把路径变成空字符,
            },
        },
    },

    envPrefix: "DB_", //APP_  为自定义开头名

    css: {
        // css预处理器
        preprocessorOptions: {
            scss: {},
        },
    },
});
