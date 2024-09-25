<template>
    <div class="grid grid-cols-2 h-screen">
        <!-- 默认占两列，order 用于指定排列顺序，md 用于适配非移动端（PC 端） -->
        <div class="col-span-2 order-2 p-10 md:col-span-1 md:order-1 bg-slate-900">
            <!-- 指定为 flex 布局，并设置为屏幕垂直水平居中，高度为 100% -->
            <div
                class="flex justify-center items-center h-full flex-col animate__animated animate__bounceInLeft animate__fast">
                <h2 class="font-bold text-4xl mb-7 text-white animate__rubberBand animate__slow">Weblog 博客登录</h2>
                <p class="text-white animate__rubberBand animate__slow">一款由 Spring Boot + Mybaits Plus + Vue 3.2 + Vite 4
                    开发的前后端分离博客。</p>
                <!-- 指定图片宽度为父级元素的 1/2 -->
                <img src="@/assets/girls.png" class="w-1/2 animate__rubberBand animate__slow">
            </div>
        </div>
        <div class="col-span-2 order-1 md:col-span-1 md:order-2 bg-white">
            <!-- flex-col 用于指定子元素垂直排列 -->
            <div
                class="flex justify-center items-center h-full flex-col animate__animated animate__bounceInRight animate__fast">
                <!-- 大标题，设置字体粗细、大小、下边距 -->
                <h1 class="font-bold text-4xl mb-5 animate__rotateOutDownLeft">欢迎回来</h1>
                <div class="flex items-center justify-center mb-7 text-gray-400 space-x-2 animate__rotateOutDownLeft">
                    <!-- 左边横线，高度为 1px, 宽度为 16，背景色设置 -->
                    <span class="h-[1px] w-16 bg-gray-200"></span>
                    <span>账号密码登录</span>
                    <!-- 右边横线 -->
                    <span class="h-[1px] w-16 bg-gray-200"></span>
                </div>
                <!-- 引入 Element Plus 表单组件，移动端设置宽度为 5/6，PC 端设置为 2/5 -->
                <!-- 1、ref="formRef" ： 添加表单引用，用于方便的获取表单；

2、 :rules="rules" : 对表单添加字段校验规则；

3、 :model="form" ：将表单和 form 变量绑定在一起； -->
                <!-- 针对表单的 el-form-item , 需要添加 prop 属性，需要和定义好的表单规则 rules 里面的字段名一一对应，这样 Elment Plus 就知道按你定义好的规则来校验了。 -->
                <el-form class="w-5/6 md:w-2/5" ref="formRef" :rules="rules" :model="form">
                    <el-form-item prop="username">
                        <!-- 输入框组件 通过v-model双向绑定-->
                        <el-input size="large" v-model="form.username" placeholder="请输入用户名" :prefix-icon="User" clearable />
                    </el-form-item>
                    <el-form-item prop="password">
                        <!-- 密码框组件 通过v-model双向绑定-->
                        <el-input size="large" type="password" v-model="form.password" placeholder="请输入密码"
                            :prefix-icon="Lock" clearable show-password />
                    </el-form-item>
                    <el-form-item>
                        <!-- 登录按钮，宽度设置为 100% @click设定点击事件onSubmit()-->
                        <el-button class="w-full" size="large" type="primary" @click="onSubmit"
                            :loading="loading">登录</el-button>
                    </el-form-item>
                </el-form>

            </div>
        </div>
    </div>
</template>

<script setup>
// 引入 Element Plus 中的用户、锁图标
import { User, Lock } from '@element-plus/icons-vue'
// 调用了刚刚声明的 login() 接口，入参为输入框中填写的用户名、密码。最终，打印了 res 对象，用于查看接口能够正常调通
import { login } from '@/api/admin/user'
import { ref, reactive, onMounted, onBeforeUnmount } from 'vue'
import { useRouter } from 'vue-router';
import { showMessage } from '@/composables/util'
import { setToken } from '@/composables/cookies'
//store存储全局状态信息Pinia
import { useUserStore } from '@/stores/user'
// 表单引用
const formRef = ref(null)
// 登录按钮加载
const loading = ref(false)
//
const userStore = useUserStore()
// 表单验证规则
// required : 必填字段；

// message : 验证不通过的提示消息；

// trigger : 什么时候触发，blur 表示输入框失去焦点的时候就校验；
const rules = {
    username: [
        {
            required: true,
            message: '用户名不能为空',
            trigger: 'blur'
        }
    ],
    password: [
        {
            required: true,
            message: '密码不能为空',
            trigger: 'blur',
        },
    ]
}
const router = useRouter()
// 定义响应式的表单对象
const form = reactive({
    username: '',
    password: ''
})

// 登录 form表单对象，登录可能出现跨域问题（在vite.config.js中配置解决方案）
const onSubmit = () => {
    console.log('登录')
    // 先验证 form 表单字段
    formRef.value.validate((valid) => {
        if (!valid) {
            console.log('表单验证不通过')
            return false
        }
        // 开始加载
        loading.value = true
        // 最后，在登录事件 onSubmit() 中通过 formRef.value.validate((valid) => {}) 来校验，当 valid 为 true , 也就是校验通过时，再调用登录接口
        // 调用登录接口
        login(form.username, form.password).then((res) => {
            console.log(res)
            // 判断是否成功
            if (res.success == true) {
                // 提示登录成功
                showMessage('登录成功')
                // 存储 Token 到 Cookie 中(实际需要的返回对象是res.data.data.token)
                let token = res.data.token
                // console.log("real cookie"+token)
                setToken(token)
                // 获取用户信息，并存储到全局状态中
                userStore.setUserInfo()
                // 跳转到后台首页
                router.push('/admin/index')

            } else {
                // 获取服务端返回的错误消息
                let message = res.message
                // 提示消息
                showMessage(message, 'error')
            }

        })
            .finally(() => {
                // 结束加载
                loading.value = false
            })
    })
}
// 按回车键后，执行登录事件
function onKeyUp(e) {
    console.log(e)
    if (e.key == 'Enter') {
        onSubmit()
    }
}

// 添加键盘监听
onMounted(() => {
    console.log('添加键盘监听')
    document.addEventListener('keyup', onKeyUp)
})

// 移除键盘监听
onBeforeUnmount(() => {
    document.removeEventListener('keyup', onKeyUp)
})

</script>