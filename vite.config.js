import { fileURLToPath, URL } from 'node:url'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    AutoImport({
      resolvers: [ElementPlusResolver()],
    }),
    Components({
      resolvers: [ElementPlusResolver()],
    }),
  ],
  resolve: {
    alias: {
      // 定义一个别名 '@'，该别名对应于当前 JavaScript 模块文件所在目录下的 'src' 目录的绝对文件路径。
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  // 在开发环境中，你可以使用代理来绕过跨域问题
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ''),
      },
    }
  }
//   server：这是一个配置对象，通常用于定义服务器的行为。
// proxy：这是 http-proxy-middleware 的配置对象，用于定义代理规则。
// '/api'：这是匹配请求路径的模式，意味着所有以 /api 开头的请求都会被代理。
// target：这是代理的目标服务器地址，即所有匹配 /api 的请求都会被转发到 http://localhost:8080。
// changeOrigin：当设置为 true 时，代理服务器会将请求头中的 Host 字段更改为目标服务器的地址。这有助于避免服务器拒绝来自不同源的请求。
// rewrite：这是一个函数，用于重写请求路径。在这里，它将移除请求路径开始的 /api 部分。例如，如果请求路径是 /api/user，重写后的路径将是 /user。
})
