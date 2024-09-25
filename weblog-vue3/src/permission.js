import router from '@/router/index'
import { getToken } from '@/composables/cookies'
import { showMessage } from '@/composables/util'
import { showPageLoading, hidePageLoading } from '@/composables/util'

// 全局路由前置守卫
router.beforeEach((to, from, next) => {
    console.log('==> 全局路由前置守卫')
    // 展示页面加载 Loading
    showPageLoading()
    // 若用户想访问后台（以 /admin 为前缀的路由）
    // 未登录，则强制跳转登录页
    let token = getToken()
    // console.log('==> 全局路由前置守卫'+token)
    if (!token && to.path.startsWith('/admin')) {
        showMessage('请先登录', 'warning')
        next({ path: '/login' })
    } else if (token && to.path == '/login') {
        // 若用户已经登录，且重复访问登录页
        showMessage('请勿重复登录', 'warning')
        // 跳转后台首页
        next({ path: '/admin/index' })
    } else{
        next()
    }
})

// 全局路由后置守卫
router.afterEach((to, from) => {
    // 动态设置页面 Title
    let title = (to.meta.title ? to.meta.title : '') + ' - Weblog'
    document.title = title
    // 隐藏页面加载 Loading
    hidePageLoading()
})
