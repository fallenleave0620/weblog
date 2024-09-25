import '@/assets/main.css'
import '/dist/tailwind.css'
import 'nprogress/nprogress.css'

import { createApp } from 'vue'
import App from '@/App.vue'
// 导入路由
import router from '@/router'
// 导入全局路由守卫
import '@/permission'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import 'animate.css'
// 引入全局状态管理 Pinia
import pinia from '@/stores'
// 引入全局状态管理 Pinia
// import { createPinia } from 'pinia'
// import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
// const pinia = createPinia()
// pinia.use(piniaPluginPersistedstate)
const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

// 应用 Pinia
app.use(pinia)
// 应用路由
app.use(router)
app.mount('#app')

