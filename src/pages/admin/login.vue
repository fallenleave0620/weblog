<template>
    <div class="grid grid-cols-2 h-screen">
        <!-- 默认占两列，order 用于指定排列顺序，md 用于适配非移动端（PC 端） -->
        <div class="col-span-2 order-2 p-10 md:col-span-1 md:order-1 bg-slate-900">
            <!-- 指定为 flex 布局，并设置为屏幕垂直水平居中，高度为 100% -->
            <div
                class="flex justify-center items-center h-full flex-col animate__animated animate__bounceInLeft animate__fast">
                <h2 class="font-bold text-4xl mb-7 text-white animate__rubberBand animate__slow">Weblog 博客登录</h2>
                <p class="text-white animate__rubberBand animate__slow">一款由 Spring Boot + Mybaits Plus + Vue 3.2 + Vite
                    4
                    开发的前后端分离博客。</p>
                <!-- 指定图片宽度为父级元素的 1/2 -->
                <img src="@/assets/girls.png" class="w-1/2 animate__rubberBand animate__slow">
            </div>
        </div>
        <div class="flex flex-col col-span-2 order-1 md:col-span-1 md:order-2 bg-white">
            <div class="toggle-wrapper" >
                <div class="toggle-cont">
                    <input class="toggle-input" id="toggle" name="toggle" type="checkbox" @click="toggleDark()"/>
                    <label class="toggle-label" for="toggle">
                        <div class="cont-icon">
                            <span style="--width: 2; --deg: 25; --duration: 11" class="sparkle"></span>
                            <span style="--width: 1; --deg: 100; --duration: 18" class="sparkle"></span>
                            <span style="--width: 1; --deg: 280; --duration: 5" class="sparkle"></span>
                            <span style="--width: 2; --deg: 200; --duration: 3" class="sparkle"></span>
                            <span style="--width: 2; --deg: 30; --duration: 20" class="sparkle"></span>
                            <span style="--width: 2; --deg: 300; --duration: 9" class="sparkle"></span>
                            <span style="--width: 1; --deg: 250; --duration: 4" class="sparkle"></span>
                            <span style="--width: 2; --deg: 210; --duration: 8" class="sparkle"></span>
                            <span style="--width: 2; --deg: 100; --duration: 9" class="sparkle"></span>
                            <span style="--width: 1; --deg: 15; --duration: 13" class="sparkle"></span>
                            <span style="--width: 1; --deg: 75; --duration: 18" class="sparkle"></span>
                            <span style="--width: 2; --deg: 65; --duration: 6" class="sparkle"></span>
                            <span style="--width: 2; --deg: 50; --duration: 7" class="sparkle"></span>
                            <span style="--width: 1; --deg: 320; --duration: 5" class="sparkle"></span>
                            <span style="--width: 1; --deg: 220; --duration: 5" class="sparkle"></span>
                            <span style="--width: 1; --deg: 215; --duration: 2" class="sparkle"></span>
                            <span style="--width: 2; --deg: 135; --duration: 9" class="sparkle"></span>
                            <span style="--width: 2; --deg: 45; --duration: 4" class="sparkle"></span>
                            <span style="--width: 1; --deg: 78; --duration: 16" class="sparkle"></span>
                            <span style="--width: 1; --deg: 89; --duration: 19" class="sparkle"></span>
                            <span style="--width: 2; --deg: 65; --duration: 14" class="sparkle"></span>
                            <span style="--width: 2; --deg: 97; --duration: 1" class="sparkle"></span>
                            <span style="--width: 1; --deg: 174; --duration: 10" class="sparkle"></span>
                            <span style="--width: 1; --deg: 236; --duration: 5" class="sparkle"></span>
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 30 30" class="icon">
                                <path
                                    d="M0.96233 28.61C1.36043 29.0081 1.96007 29.1255 2.47555 28.8971L10.4256 25.3552C13.2236 24.11 16.4254 24.1425 19.2107 25.4401L27.4152 29.2747C27.476 29.3044 27.5418 29.3023 27.6047 29.32C27.6563 29.3348 27.7079 29.3497 27.761 29.3574C27.843 29.3687 27.9194 29.3758 28 29.3688C28.1273 29.3617 28.2531 29.3405 28.3726 29.2945C28.4447 29.262 28.5162 29.2287 28.5749 29.1842C28.6399 29.1446 28.6993 29.0994 28.7509 29.0477L28.9008 28.8582C28.9468 28.7995 28.9793 28.7274 29.0112 28.656C29.0599 28.5322 29.0811 28.4036 29.0882 28.2734C29.0939 28.1957 29.0868 28.1207 29.0769 28.0415C29.0705 27.9955 29.0585 27.9524 29.0472 27.9072C29.0295 27.8343 29.0302 27.7601 28.9984 27.6901L25.1638 19.4855C23.8592 16.7073 23.8273 13.5048 25.0726 10.7068L28.6145 2.75679C28.8429 2.24131 28.7318 1.63531 28.3337 1.2372C27.9165 0.820011 27.271 0.721743 26.7491 0.9961L19.8357 4.59596C16.8418 6.15442 13.2879 6.18696 10.2615 4.70062L1.80308 0.520214C1.7055 0.474959 1.60722 0.441742 1.50964 0.421943C1.44459 0.409215 1.37882 0.395769 1.3074 0.402133C1.14406 0.395769 0.981436 0.428275 0.818095 0.499692C0.77284 0.519491 0.719805 0.545671 0.67455 0.578198C0.596061 0.617088 0.524653 0.675786 0.4596 0.74084C0.394546 0.805894 0.335843 0.877306 0.296245 0.956502C0.263718 1.00176 0.237561 1.05477 0.217762 1.10003C0.152708 1.24286 0.126545 1.40058 0.120181 1.54978C0.120181 1.61483 0.126527 1.6735 0.132891 1.73219C0.15269 1.85664 0.178881 1.97332 0.237571 2.08434L4.41798 10.5427C5.91139 13.5621 5.8725 17.1238 4.3204 20.1099L0.720514 27.0233C0.440499 27.5536 0.545137 28.1928 0.96233 28.61Z">
                                </path>
                            </svg>
                        </div>
                    </label>
                </div>
            </div>
            <!-- From Uiverse.io by MuhammadHasann -->


            <!-- flex-col 用于指定子元素垂直排列 -->
            <div
                class="flex justify-center items-center h-full flex-col animate__animated animate__bounceInRight animate__fast dark:bg-gray-800">
                <!-- 大标题，设置字体粗细、大小、下边距 -->
                <h1 class="font-bold text-4xl mb-5 animate__rotateOutDownLeft dark:text-white">欢迎回来</h1>
                <div class="flex items-center justify-center mb-7 text-gray-400 space-x-2 animate__rotateOutDownLeft dark:text-gray-500">
                    <!-- 左边横线，高度为 1px, 宽度为 16，背景色设置 -->
                    <span class="h-[1px] w-16 bg-gray-200 dark:bg-gray-700"></span>
                    <span>账号密码登录</span>
                    <!-- 右边横线 -->
                    <span class="h-[1px] w-16 bg-gray-200 dark:bg-gray-700"></span>
                </div>
                <!-- 引入 Element Plus 表单组件，移动端设置宽度为 5/6，PC 端设置为 2/5 -->
                <!-- 1、ref="formRef" ： 添加表单引用，用于方便的获取表单；

2、 :rules="rules" : 对表单添加字段校验规则；

3、 :model="form" ：将表单和 form 变量绑定在一起； -->
                <!-- 针对表单的 el-form-item , 需要添加 prop 属性，需要和定义好的表单规则 rules 里面的字段名一一对应，这样 Elment Plus 就知道按你定义好的规则来校验了。 -->
                <el-form class="w-5/6 md:w-2/5" ref="formRef" :rules="rules" :model="form">
                    <el-form-item prop="username">
                        <!-- 输入框组件 通过v-model双向绑定-->
                        <el-input size="large" v-model="form.username" placeholder="请输入用户名" :prefix-icon="User"
                            clearable />
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
import { useDark, useToggle } from '@vueuse/core'
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
// 是否是白天
const isLight = ref(true)
const isDark = useDark({
  onChanged(dark) {
    // update the dom, call the API or something
    console.log('onchange:' + dark)
    if (dark) {
        // 给 body 添加 class="dark"
        document.documentElement.classList.add('dark');
        // 设置 switch 的值
        isLight.value = false
    } else {
        // 移除 body 中添加 class="dark"
        document.documentElement.classList.remove('dark');
        isLight.value = true
    }
  },
})
const toggleDark = useToggle(isDark)
</script>
<style scoped>
/* 新增父容器样式 */
.toggle-wrapper {
    position: fixed;
    top: 20px;
    right: 20px;
    z-index: 9999;
    /* 提高父容器的 z-index */
}

/* From Uiverse.io by MuhammadHasann */
.toggle-cont {
    --primary: #54a8fc;
    --light: #d9d9d9;
    --dark: #121212;
    --gray: #414344;

    position: relative;
    z-index: 10000;
    /* 提高开关组件的 z-index */

    width: fit-content;
    height: 20px;
    /* 进一步减小组件高度 */

    border-radius: 9999px;
}

.toggle-cont .toggle-input {
    display: none;
}

.toggle-cont .toggle-label {
    --gap: 2px;
    /* 进一步减小间隙 */
    --width: 20px;
    /* 进一步减小宽度 */

    cursor: pointer;

    position: relative;
    display: inline-block;

    padding: 0.2rem;
    /* 进一步减小内边距 */
    width: calc((var(--width) + var(--gap)) * 2);
    height: 100%;
    background-color: var(--dark);

    border: 1px solid #777777;
    border-bottom: 0;

    border-radius: 9999px;
    box-sizing: content-box;
    transition: all 0.3s ease-in-out;
}

.toggle-label::before {
    content: "";

    position: absolute;
    z-index: -10;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);

    width: calc(100% + 0.8rem);
    /* 进一步减小伪元素宽度 */
    height: calc(100% + 0.8rem);
    /* 进一步减小伪元素高度 */
    background-color: var(--gray);

    border: 1px solid #777777;
    border-bottom: 0;
    border-radius: 9999px;

    transition: all 0.3s ease-in-out;
}

.toggle-label::after {
    content: "";

    position: absolute;
    z-index: -10;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);

    width: 100%;
    height: 100%;
    background-image: radial-gradient(circle at 50% -100%,
            rgb(58, 155, 252) 0%,
            rgba(12, 12, 12, 1) 80%);

    border-radius: 9999px;
}

.toggle-cont .toggle-label .cont-icon {
    position: relative;

    display: flex;
    justify-content: center;
    align-items: center;

    position: relative;
    width: var(--width);
    height: 20px;
    /* 进一步减小图标容器高度 */
    background-image: radial-gradient(circle at 50% 0%,
            #666666 0%,
            var(--gray) 100%);

    border: 1px solid #aaaaaa;
    border-bottom: 0;
    border-radius: 9999px;
    box-shadow: inset 0 -0.05rem 0.05rem var(--primary),
        inset 0 0 0.2rem 0.3rem var(--second);
    /* 进一步调整阴影大小 */

    transition: transform 0.3s ease-in-out;
}

.cont-icon {
    overflow: clip;
    position: relative;
}

.cont-icon .sparkle {
    position: absolute;
    top: 50%;
    left: 50%;

    display: block;

    width: calc(var(--width) * 0.6px);
    /* 进一步减小闪烁元素大小 */
    aspect-ratio: 1;
    background-color: var(--light);

    border-radius: 50%;
    transform-origin: 50% 50%;
    rotate: calc(1deg * var(--deg));
    transform: translate(-50%, -50%);
    animation: sparkle calc(100s / var(--duration)) linear calc(0s / var(--duration)) infinite;
}

@keyframes sparkle {
    to {
        width: calc(var(--width) * 0.3px);
        /* 进一步减小闪烁元素结束时大小 */
        transform: translate(2000%, -50%);
    }
}

.cont-icon .icon {
    width: 0.5rem;
    /* 进一步减小图标大小 */
    fill: var(--light);
}

.toggle-cont:has(.toggle-input:checked) {
    --checked: true;
}

@container style(--checked: true) {
    .toggle-cont .toggle-label {
        background-color: #41434400;

        border: 1px solid #3d6970;
        border-bottom: 0;
    }

    .toggle-cont .toggle-label::before {
        box-shadow: 0 1rem 2.5rem -2rem #0080ff;
    }

    .toggle-cont .toggle-label .cont-icon {
        overflow: visible;

        background-image: radial-gradient(circle at 50% 0%,
                #045ab1 0%,
                var(--primary) 100%);

        border: 1px solid var(--primary);
        border-bottom: 0;

        transform: translateX(calc((var(--gap) * 2) + 100%)) rotate(-225deg);
    }

    .toggle-cont .toggle-label .cont-icon .sparkle {
        z-index: -10;

        width: calc(var(--width) * 0.9px);
        /* 进一步调整选中状态闪烁元素大小 */
        background-color: #acacac;

        animation: sparkle calc(100s / var(--duration)) linear calc(10s / var(--duration)) infinite;
    }

    @keyframes sparkle {
        to {
            width: calc(var(--width) * 0.6px);
            /* 进一步调整选中状态闪烁元素结束时大小 */
            transform: translate(5000%, -50%);
        }
    }
}
</style>