<template>
    <Header></Header>

    <!-- 文章标题、标签、Meta 信息 -->
    <div class="bg-white dark:bg-gray-900">
        <div class="max-w-screen-xl flex flex-col flex-wrap mx-auto px-4 md:px-6 pb-14 pt-10">
            <!-- 标签集合 -->
            <div class="mb-5">
                <span v-if="(article.tags && article.tags.length > 0)" @click="goTagArticleListPage(tag.id, tag.name)"
                    v-for="tag in article.tags"
                    class="inline-block mb-1 cursor-pointer bg-green-100 text-green-800 text-xs font-medium mr-2 px-3 py-1 rounded-full hover:bg-green-200 hover:text-green-900 dark:bg-green-900 dark:text-green-300">
                    # {{ tag.name }}
                </span>
            </div>

            <!-- 文章标题 -->
            <h1 class="font-bold text-4xl md:text-5xl mb-8 dark:text-white">{{ article.title }}</h1>

            <!-- Meta 信息 -->
            <div class="flex gap-3 md:gap-6 text-gray-400 items-center text-sm">
                <!-- 字数 -->
                <div class="flex items-center" data-tooltip-target="word-tooltip-bottom"
                    data-tooltip-placement="bottom">
                    <svg t="1701512226243" class="w-4 h-4 mr-1 icon" viewBox="0 0 1024 1024" version="1.1"
                        xmlns="http://www.w3.org/2000/svg" p-id="28617" width="48" height="48">
                        <path
                            d="M682.666667 85.333333l213.333333 213.333334v597.674666a42.368 42.368 0 0 1-42.368 42.325334H170.368A42.666667 42.666667 0 0 1 128 896.341333V127.658667C128 104.277333 146.986667 85.333333 170.368 85.333333H682.666667z m-85.333334 256v212.864L512 469.333333l-84.906667 85.333334L426.666667 341.333333H341.333333v341.333334h85.333334l85.333333-85.333334 85.333333 85.333334h85.333334V341.333333h-85.333334z"
                            p-id="28618" fill="#8a8a8a"></path>
                    </svg>
                    {{ article.totalWords }}
                </div>
                <!-- 文章字数 Tooltip -->
                <div id="word-tooltip-bottom" role="tooltip"
                    class="absolute z-10 invisible inline-block px-3 py-2 text-xs font-medium text-white bg-gray-900 rounded shadow-sm opacity-0 tooltip dark:bg-gray-700">
                    总字数
                    <div class="tooltip-arrow" data-popper-arrow></div>
                </div>

                <!-- 阅读时长 -->
                <div class="hidden md:block">
                    <div class="flex items-center" data-tooltip-target="read-time-tooltip-bottom"
                        data-tooltip-placement="bottom">
                        <svg t="1701512553358" class="w-4 h-4 mr-1.5 icon" viewBox="0 0 1024 1024" version="1.1"
                            xmlns="http://www.w3.org/2000/svg" p-id="37812" width="48" height="48">
                            <path
                                d="M513 33.22c-265.1 0-480 214.9-480 480s214.9 480 480 480 480-214.9 480-480-214.9-480-480-480z m208.9 652.59c-11.05 19.13-35.51 25.69-54.64 14.64L474.1 588.93c-13.06-7.54-20.26-21.34-19.99-35.42 0-0.17-0.01-0.34-0.01-0.51V329.95c0-22.09 17.91-40 40-40s40 17.91 40 40v201.23l173.17 99.98c19.12 11.05 25.68 35.51 14.63 54.65z"
                                fill="#8a8a8a" p-id="37813"></path>
                        </svg>
                        {{ article.readTime }}
                    </div>
                    <!-- 阅读时长 Tooltip -->
                    <div id="read-time-tooltip-bottom" role="tooltip"
                        class="absolute z-10 invisible inline-block px-3 py-2 text-xs font-medium text-white bg-gray-900 rounded shadow-sm opacity-0 tooltip dark:bg-gray-700">
                        阅读时长
                        <div class="tooltip-arrow" data-popper-arrow></div>
                    </div>
                </div>

                <!-- 发布时间 -->
                <div class="flex items-center" data-tooltip-target="publish-time-tooltip-bottom"
                    data-tooltip-placement="bottom">
                    <svg t="1701513012543" class="w-[18px] h-[18px] mr-1 icon" viewBox="0 0 1024 1024" version="1.1"
                        xmlns="http://www.w3.org/2000/svg" p-id="41600" width="48" height="48">
                        <path
                            d="M725.333333 170.666667h74.709334C864.853333 170.666667 917.333333 223.189333 917.333333 288.096V799.893333C917.333333 864.757333 864.832 917.333333 800.042667 917.333333H223.957333C159.146667 917.333333 106.666667 864.810667 106.666667 799.904V288.106667C106.666667 223.242667 159.168 170.666667 223.957333 170.666667H298.666667v-32a32 32 0 0 1 64 0v32h298.666666v-32a32 32 0 0 1 64 0v32z m0 64v32a32 32 0 0 1-64 0v-32H362.666667v32a32 32 0 0 1-64 0v-32h-74.709334A53.354667 53.354667 0 0 0 170.666667 288.096V799.893333A53.301333 53.301333 0 0 0 223.957333 853.333333h576.085334A53.354667 53.354667 0 0 0 853.333333 799.904V288.106667A53.301333 53.301333 0 0 0 800.042667 234.666667H725.333333z m-10.666666 224a32 32 0 0 1 0 64H309.333333a32 32 0 0 1 0-64h405.333334zM586.666667 618.666667a32 32 0 0 1 0 64H309.333333a32 32 0 0 1 0-64h277.333334z"
                            fill="#8a8a8a" p-id="41601"></path>
                    </svg>
                    {{ article.createTime }}
                </div>
                <!-- 发布时间 Tooltip -->
                <div id="publish-time-tooltip-bottom" role="tooltip"
                    class="absolute z-10 invisible inline-block px-3 py-2 text-xs font-medium text-white bg-gray-900 rounded shadow-sm opacity-0 tooltip dark:bg-gray-700">
                    发布时间
                    <div class="tooltip-arrow" data-popper-arrow></div>
                </div>

                <!-- 分类 -->
                <div class="flex items-center" data-tooltip-target="category-tooltip-bottom"
                    data-tooltip-placement="bottom">
                    <svg t="1701513357854" class="w-4 h-4 mr-1.5 icon" viewBox="0 0 1024 1024" version="1.1"
                        xmlns="http://www.w3.org/2000/svg" p-id="50560" width="48" height="48">
                        <path
                            d="M476.7232 112.503467L121.634133 279.825067a68.266667 68.266667 0 0 0 1.6896 124.279466l355.089067 155.648a68.266667 68.266667 0 0 0 54.818133 0l355.089067-155.6992a68.266667 68.266667 0 0 0 1.672533-124.279466l-355.089066-167.253334a68.266667 68.266667 0 0 0-58.197334 0zM150.7328 341.572267l355.089067-167.304534 355.072 167.253334-355.089067 155.6992-355.072-155.648zM860.842667 685.346133a34.133333 34.133333 0 0 1 28.962133 61.781334l-2.4064 1.1264-368.810667 155.682133a34.133333 34.133333 0 0 1-23.671466 1.0752l-2.8672-1.0752-368.7936-155.648a34.133333 34.133333 0 0 1 24.064-63.829333l2.491733 0.938666 355.498667 150.050134 355.5328-150.101334z"
                            fill="#444444" p-id="50561"></path>
                        <path
                            d="M853.333333 512l-341.486933 153.634133L170.666667 512.341333v55.210667c0 13.4656 7.748267 25.6512 19.712 30.9248l286.190933 126.976a78.7968 78.7968 0 0 0 35.2768 8.3968c12.049067 0 24.081067-2.798933 35.293867-8.3968l286.498133-127.249067A33.7408 33.7408 0 0 0 853.333333 567.278933V512z"
                            fill="#00B386" p-id="50562"></path>
                    </svg>
                    <a @click="goCategoryArticleListPage(article.categoryId, article.categoryName)"
                        class="cursor-pointer mr-1 hover:underline">{{ article.categoryName }}</a>
                </div>
                <!-- 分类 Tooltip -->
                <div id="category-tooltip-bottom" role="tooltip"
                    class="absolute z-10 invisible inline-block px-3 py-2 text-xs font-medium text-white bg-gray-900 rounded shadow-sm opacity-0 tooltip dark:bg-gray-700">
                    分类
                    <div class="tooltip-arrow" data-popper-arrow></div>
                </div>

                <!-- 阅读量 -->
                <div class="flex items-center" data-tooltip-target="read-num-tooltip-bottom"
                    data-tooltip-placement="bottom">
                    <svg t="1701513523793" class="w-[18px] h-[18px] mr-1 icon" viewBox="0 0 1024 1024" version="1.1"
                        xmlns="http://www.w3.org/2000/svg" p-id="56112" width="48" height="48">
                        <path
                            d="M512 87.806c-234.721 0-424.194 189.474-424.194 424.194s189.474 424.194 424.194 424.194 424.194-189.474 424.194-424.194-189.474-424.194-424.194-424.194zM594.010 825.904c-18.382 12.725-25.452 8.484-12.725-4.242 11.312-12.725 83.425-103.22-29.694-168.263-36.763-21.21-49.49-72.113-49.49-83.425 0-12.725 2.829-18.382-7.069-14.141-8.484 2.829-195.13 91.908-41.007 265.828 9.899 11.312 5.655 14.141-12.725 5.655-419.953-224.822 137.155-579.732 145.639-583.974 8.484-5.655 5.655 2.829-1.414 16.967-12.725 25.452-45.248 164.022 41.007 220.582 87.668 56.56 213.509 172.507-32.522 345.012z"
                            p-id="56113" fill="#8a8a8a"></path>
                    </svg>
                    {{ article.readNum }}
                </div>
                <!-- 阅读量 Tooltip -->
                <div id="read-num-tooltip-bottom" role="tooltip"
                    class="absolute z-10 invisible inline-block px-3 py-2 text-xs font-medium text-white bg-gray-900 rounded shadow-sm opacity-0 tooltip dark:bg-gray-700">
                    阅读量
                    <div class="tooltip-arrow" data-popper-arrow></div>
                </div>
            </div>
        </div>
    </div>
    <!-- 主内容区域 -->
    <main class="container max-w-screen-xl mx-auto p-4 px-6">
        <!-- grid 表格布局，分为 4 列 -->
        <div class="grid grid-cols-4 gap-7">
            <!-- 左边栏，占用 3 列 -->
            <div class="col-span-4 md:col-span-3 mb-3">
                <!-- 文章卡片父容器 -->
                <div
                    class="w-full p-5 mb-3 bg-white border border-gray-200 rounded-lg dark:bg-gray-800 dark:border-gray-700">

                    <!-- 面包屑 -->
                    <!-- <nav class="flex" aria-label="Breadcrumb">
                        <ol class="inline-flex items-center space-x-1 md:space-x-2 rtl:space-x-reverse">
                            <li class="inline-flex items-center">
                                <a href="#"
                                    class="inline-flex items-center text-sm font-medium text-gray-700 hover:text-blue-600 dark:text-gray-400 dark:hover:text-white">
                                    <svg class="w-3 h-3 me-2.5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg"
                                        fill="currentColor" viewBox="0 0 20 20">
                                        <path
                                            d="m19.707 9.293-2-2-7-7a1 1 0 0 0-1.414 0l-7 7-2 2a1 1 0 0 0 1.414 1.414L2 10.414V18a2 2 0 0 0 2 2h3a1 1 0 0 0 1-1v-4a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v4a1 1 0 0 0 1 1h3a2 2 0 0 0 2-2v-7.586l.293.293a1 1 0 0 0 1.414-1.414Z" />
                                    </svg>
                                    首页
                                </a>
                            </li>
                            <li>
                                <div class="flex items-center">
                                    <svg class="rtl:rotate-180 w-3 h-3 text-gray-400 mx-1" aria-hidden="true"
                                        xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                            stroke-width="2" d="m1 9 4-4-4-4" />
                                    </svg>
                                    <a href="#"
                                        class="ml-1 text-sm font-medium md:ml-3 dark:text-gray-400 dark:hover:text-white">正文</a>
                                </div>
                            </li>
                        </ol>
                    </nav> -->
                    <!-- 文章 -->
                    <article>
                        <!-- 正文 -->
                         <div :class="{ 'dark': isDark }">
                            <div ref="articleContentRef" class="mt-5 article-content" v-viewer v-html="article.content">
                            </div>
                         </div>
                        <!-- <div ref="articleContentRef" class="mt-5 article-content" v-viewer v-html="article.content">
                        </div> -->
                        <!-- 标签集合 -->
                        <!-- <div class="mt-5">
                            <span v-if="(article.tags && article.tags.length > 0)"
                                @click="goTagArticleListPage(tag.id, tag.name)" v-for="tag in article.tags"
                                class="inline-block mb-1 cursor-pointer bg-green-100 text-green-800 text-xs font-medium mr-2 px-3 py-1 rounded-full hover:bg-green-200 hover:text-green-900 dark:bg-green-900 dark:text-green-300">
                                # {{ tag.name }}
                            </span>
                        </div> -->
                        <!-- 上下篇 -->
                        <nav class="flex flex-row mt-7">
                            <!-- basis-1/2 用于占用 flex 布局的一半空间 -->
                            <div class="basis-1/2" v-if="article.preArticle"
                                @click.prevent="router.push('/article/' + article.preArticle.articleId)">
                                <!-- h-full 指定高度占满 -->
                                <a href="#"
                                    class="cursor-pointer flex flex-col h-full p-4 mr-3 text-base font-medium text-gray-500 bg-white border border-gray-300 rounded-lg hover:border-sky-400 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
                                    <div>
                                        <svg class="inline w-3.5 h-3.5 mr-2 mb-1" aria-hidden="true"
                                            xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
                                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                                stroke-width="2" d="M13 5H1m0 0 4 4M1 5l4-4"></path>
                                        </svg>
                                        上一篇
                                    </div>
                                    <div>{{ article.preArticle.articleTitle }}</div>
                                </a>
                            </div>

                            <div class="basis-1/2" v-if="article.nextArticle"
                                @click.prevent="router.push('/article/' + article.nextArticle.articleId)">
                                <!-- text-right 指定文字居右显示 -->
                                <a href="#"
                                    class="cursor-pointer flex flex-col h-full text-right p-4 text-base font-medium text-gray-500 bg-white border border-gray-300 rounded-lg hover:border-sky-400 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
                                    <div>
                                        下一篇
                                        <svg class="inline w-3.5 h-3.5 ml-2 mb-1" aria-hidden="true"
                                            xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
                                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                                stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9"></path>
                                        </svg>
                                    </div>
                                    <div>{{ article.nextArticle.articleTitle }}</div>
                                </a>
                            </div>
                        </nav>

                    </article>
                </div>

            </div>

            <!-- 右边侧边栏，占用一列 -->
            <aside class="col-span-4 md:col-span-1">
                <div class="">
                    <!-- 博主信息 -->
                    <UserInfoCard></UserInfoCard>

                    <!-- 分类 -->
                    <CategoryListCard></CategoryListCard>

                    <!-- 标签 -->
                    <TagListCard></TagListCard>
                </div>
                <!-- 文章目录 -->
                <Toc></Toc>
            </aside>
        </div>

    </main>
    <!-- 返回顶部 -->
    <ScrollToTopButton></ScrollToTopButton>
    <Footer></Footer>
</template>

<script setup>
import Toc from '@/layouts/frontend/components/Toc.vue'
import ScrollToTopButton from '@/layouts/frontend/components/ScrollToTopButton.vue'
import Header from '@/layouts/frontend/components/Header.vue'
import Footer from '@/layouts/frontend/components/Footer.vue'
import UserInfoCard from '@/layouts/frontend/components/UserInfoCard.vue'
import TagListCard from '@/layouts/frontend/components/TagListCard.vue'
import CategoryListCard from '@/layouts/frontend/components/CategoryListCard.vue'
import { useRoute, useRouter } from 'vue-router'
import { getArticleDetail } from '@/api/frontend/article'
import { ref, watch, onMounted, nextTick } from 'vue'
import hljs from 'highlight.js'
// 代码高亮样式
import 'highlight.js/styles/tokyo-night-dark.css'
import {  initTooltips } from 'flowbite'
import { useDark } from '@vueuse/core'
// 是否是暗黑模式
const isDark = useDark()
// 复制内容到剪切板
function CopyClipboard(text) {
    const textarea = document.createElement('textarea')
    textarea.value = text
    document.body.appendChild(textarea)
    textarea.select()
    document.execCommand('copy')
    document.body.removeChild(textarea)
}
// 正文 div 引用
// const articleContentRef = ref(null)
onMounted(() => {
    // 使用 MutationObserver 监视 DOM 的变化
    // const observer = new MutationObserver(mutationsList => {
    //     for (let mutation of mutationsList) {
    //         if (mutation.type === 'childList') {
    //             // 获取所有 pre code 节点
    //             let highlight = document.querySelectorAll('pre code')
    //             // 循环高亮
    //             highlight.forEach((block) => {
    //                 hljs.highlightBlock(block)
    //             })
    //         }
    //     }
    // })

    // // 配置监视子节点的变化
    // const config = { childList: true, subtree: true }
    // // 开始观察正文内容变化
    // observer.observe(articleContentRef.value, config)
    // 初始化 Flowbit 组件
    initTooltips();
})
const route = useRoute()
const router = useRouter()
// 打印文章 ID
console.log(route.params.articleId)
// 文章数据
const article = ref({})
function refreshArticleDetail(articleId) {
    getArticleDetail(articleId).then((res) => {
        // 该文章不存在(错误码为 20010)
        if (!res.success && res.errorCode == '20010') {
            // 手动跳转 404 页面
            router.push({ name: 'NotFound' })
            return
        }
        if (res.success)
            article.value = res.data

        nextTick(() => {
            // 获取所有 pre code 节点
            let highlight = document.querySelectorAll('pre code')
            // 循环高亮
            highlight.forEach((block) => {
                hljs.highlightElement(block)
            })
            // 代码块复制按钮
            let preElement = document.querySelectorAll('pre')
            preElement.forEach(preElement => {
                let codeElement = preElement.querySelector('code')
                if (codeElement) {
                    let copyCodeBtn = '<button class="hidden copy-code-btn flex items-center justify-center"><div class="copy-icon"></div></button>'
                    codeElement.insertAdjacentHTML('beforebegin', copyCodeBtn)

                    let copyBtn = codeElement.previousElementSibling
                    copyBtn.addEventListener('click', () => {
                        //添加copied样式
                        copyBtn.classList.add('copied')
                        CopyClipboard(codeElement.innerText)
                        setTimeout(() => {
                            copyBtn.classList.remove('copied')
                        }, 1500)
                    })
                }
                // 添加事件监听器，鼠标移入移出事件
                preElement.addEventListener('mouseenter', handleMouseEnter);
                preElement.addEventListener('mouseleave', handleMouseLeave);
                
            })

        })
    })
}
const handleMouseEnter = (e) => {
    let copyBtn = e.target.querySelector('.copy-code-btn')
    if (copyBtn) {
        copyBtn.classList.remove('hidden')
        copyBtn.classList.remove('block')
    }
}
const handleMouseLeave = (e) => {
    let copyBtn = e.target.querySelector('.copy-code-btn')
    if (copyBtn) {
        copyBtn.classList.add('hidden')
    }
}

refreshArticleDetail(route.params.articleId)
// 跳转分类文章列表页
const goCategoryArticleListPage = (id, name) => {
    // 跳转时通过 query 携带参数（分类 ID、分类名称）
    router.push({ path: `/category/article/list`, query: { id, name } })
}
// 跳转标签文章列表页
const goTagArticleListPage = (id, name) => {
    // 跳转时通过 query 携带参数（标签 ID、标签名称）
    router.push({ path: '/tag/article/list', query: { id, name } })
}
watch(route, (newRoute, oldRoute) => {
    // 重新渲染文章详情
    refreshArticleDetail(newRoute.params.articleId)
})
</script>
<style scoped>
/* h1, h2, h3, h4, h5, h6 标题样式 */
::v-deep(.article-content h1,
    .article-content h2,
    .article-content h3,
    .article-content h4,
    .article-content h5,
    .article-content h6) {
    color: #292525;
    line-height: 150%;
    font-family: PingFang SC, Helvetica Neue, Helvetica, Hiragino Sans GB, Microsoft YaHei, "\5FAE\8F6F\96C5\9ED1", Arial, sans-serif;
}

::v-deep(.article-content h2) {
    line-height: 1.5;
    font-weight: 700;
    font-synthesis: style;
    font-size: 24px;
    margin-top: 40px;
    margin-bottom: 26px;
    line-height: 140%;
    border-bottom: 1px solid rgb(241 245 249);
    padding-bottom: 15px;
}

::v-deep(.article-content h3) {
    font-size: 20px;
    margin-top: 40px;
    margin-bottom: 16px;
    font-weight: 600;
}

::v-deep(.article-content h4) {
    font-size: 18px;
    margin-top: 30px;
    margin-bottom: 16px;
    font-weight: 600;
}

::v-deep(.article-content h5) {
    font-size: 16px;
    margin-top: 30px;
    margin-bottom: 14px;
    font-weight: 600;
}

::v-deep(.article-content h6) {
    font-size: 16px;
    margin-top: 30px;
    margin-bottom: 14px;
    font-weight: 600;
}

/* p 段落样式 */
::v-deep(.article-content p) {
    letter-spacing: .3px;
    margin: 0 0 20px;
    line-height: 30px;
    color: #4c4e4d;
    font-weight: 400;
    word-break: normal;
    word-wrap: break-word;
    font-family: -apple-system, BlinkMacSystemFont, PingFang SC, Hiragino Sans GB, Microsoft Yahei, Arial, sans-serif;
}

/* blockquote 引用样式 */
::v-deep(.article-content blockquote) {
    border-left: 2.3px solid rgb(52, 152, 219);
    quotes: none;
    background: rgb(236, 240, 241);
    color: #777;
    font-size: 16px;
    margin-bottom: 20px;
    padding: 24px;
}

/* 设置 blockquote 中最后一个 p 标签的 margin-bottom 为 0 */
::v-deep(.article-content blockquote p:last-child) {
    margin-bottom: 0;
}

/* 斜体样式 */
::v-deep(.article-content em) {
    color: #c849ff;
}

/* 超链接样式 */
::v-deep(.article-content a) {
    color: #167bc2;
}

::v-deep(.article-content a:hover) {
    text-decoration: underline;
}

/* ul 样式 */
::v-deep(.article-content ul) {
    padding-left: 2rem;
}

::v-deep(.article-content > ul) {
    margin-bottom: 20px;
}

::v-deep(.article-content ul li) {
    list-style-type: disc;
    padding-top: 5px;
    padding-bottom: 5px;
    font-size: 16px;
}

::v-deep(.article-content ul li p) {
    margin-bottom: 0 !important;
}

::v-deep(.article-content ul ul li) {
    list-style-type: square;
}

/* ol 样式 */
::v-deep(.article-content ol) {
    list-style-type: decimal;
    padding-left: 2rem;
}

/* 图片样式 */
::v-deep(.article-content img) {
    max-width: 100%;
    overflow: hidden;
    display: block;
    margin: 0 auto;
    border-radius: 8px;
}

::v-deep(.article-content img:hover,
    img:focus) {
    box-shadow: 2px 2px 10px 0 rgba(0, 0, 0, .15);
}

/* 图片描述文字 */
::v-deep(.image-caption) {
    min-width: 20%;
    max-width: 80%;
    min-height: 43px;
    display: block;
    padding: 10px;
    margin: 0 auto;
    font-size: 13px;
    color: #999;
    text-align: center;
}

/* code 样式 */
::v-deep(.article-content code:not(pre code)) {
    padding: 2px 4px;
    margin: 0 2px;
    font-size: 95% !important;
    border-radius: 4px;
    color: rgb(41, 128, 185);
    background-color: rgba(27, 31, 35, 0.05);
    font-family: Operator Mono, Consolas, Monaco, Menlo, monospace;
}

/* 表格样式 */
::v-deep(table) {
    margin-bottom: 20px;
    width: 100%;
}

::v-deep(table tr) {
    background-color: #fff;
    border-top: 1px solid #c6cbd1;
}

::v-deep(table th) {
    padding: 6px 13px;
    border: 1px solid #dfe2e5;
}

::v-deep(table td) {
    padding: 6px 13px;
    border: 1px solid #dfe2e5;
}

::v-deep(table tr:nth-child(2n)) {
    background-color: #f6f8fa;
}

/* hr 横线 */
::v-deep(hr) {
    margin-bottom: 20px;
}

::v-deep(code) {
    font-size: 98%;
}

/* pre 样式 */
::v-deep(pre) {
    margin-bottom: 20px;
    padding-top: 30px;
    background: #21252b;
    border-radius: 6px;
    position: relative;
}

::v-deep(pre code.hljs) {
    padding: 0.7rem 1rem;
    border-bottom-left-radius: 6px;
    border-bottom-right-radius: 6px;
}

::v-deep(pre:before) {
    background: #fc625d;
    border-radius: 50%;
    box-shadow: 20px 0 #fdbc40, 40px 0 #35cd4b;
    content: ' ';
    height: 10px;
    margin-top: -19px;
    margin-left: 10px;
    position: absolute;
    width: 10px;
}

/* 
代码块复制按钮样式 
*/
::v-deep(.copy-code-btn) {
    border-width: 0;
    cursor: pointer;
    position: absolute;
    top: 0.5em;
    right: 0.5em;
    z-index: 5;
    width: 2.5rem;
    height: 2.5rem;
    padding: 0;
    border-radius: 0.5rem;
    opacity: 0;
    transition: opacity .4s;
    opacity: 1
}

::v-deep(.copy-code-btn:hover) {
    background: #2f3542;
}

::v-deep(.copy-icon) {
    --copy-icon: url("data:image/svg+xml;utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='none' height='20' width='20' stroke='rgba(128,128,128,1)' stroke-width='2'%3E%3Cpath stroke-linecap='round' stroke-linejoin='round' d='M9 5H7a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V7a2 2 0 0 0-2-2h-2M9 5a2 2 0 0 0 2 2h2a2 2 0 0 0 2-2M9 5a2 2 0 0 1 2-2h2a2 2 0 0 1 2 2'/%3E%3C/svg%3E");
    background: currentcolor;
    -webkit-mask-image: var(--copy-icon);
    mask-image: var(--copy-icon);
    -webkit-mask-position: 50%;
    mask-position: 50%;
    -webkit-mask-repeat: no-repeat;
    mask-repeat: no-repeat;
    -webkit-mask-size: 1em;
    mask-size: 1em;
    width: 1.25rem;
    height: 1.25rem;
    padding: 0.625rem;
    color: #9e9e9e;
    font-size: 1.25rem;
}
/* 
复制成功提示样式
 */
::v-deep(.copied) {
    display: flex;
    background: #2f3542;
}

::v-deep(.copied:after) {
    content: "已复制";
    position: absolute;
    top: 0;
    right: calc(100% + .25rem);
    display: block;
    height: 2.5rem;
    padding: .625rem;
    border-radius: .5rem;
    background: #2f3542;
    color: #9e9e9e;
    font-weight: 500;
    line-height: 1.25rem;
    white-space: nowrap;
    font-size: 14px;
    font-family: -apple-system, BlinkMacSystemFont, PingFang SC, Hiragino Sans GB, Microsoft Yahei, Arial, sans-serif;
}

::v-deep(.copied .copy-icon) {
    --copied-icon: url("data:image/svg+xml;utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='none' height='20' width='20' stroke='rgba(128,128,128,1)' stroke-width='2'%3E%3Cpath stroke-linecap='round' stroke-linejoin='round' d='M9 5H7a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V7a2 2 0 0 0-2-2h-2M9 5a2 2 0 0 0 2 2h2a2 2 0 0 0 2-2M9 5a2 2 0 0 1 2-2h2a2 2 0 0 1 2 2m-6 9 2 2 4-4'/%3E%3C/svg%3E");
    -webkit-mask-image: var(--copied-icon);
    mask-image: var(--copied-icon);
}
/* h1, h2, h3, h4, h5, h6 标题样式 */
::v-deep(.dark .article-content h2) {
    --tw-text-opacity: 1;
    color: rgb(226 232 240/var(--tw-text-opacity));
    border-bottom: 1px solid;
    border-color: rgb(55 65 81 / 1);
}

::v-deep(.dark .article-content h3) {
    --tw-text-opacity: 1;
    color: rgb(226 232 240/var(--tw-text-opacity));
}

::v-deep(.dark .article-content h4) {
    --tw-text-opacity: 1;
    color: rgb(226 232 240/var(--tw-text-opacity));
}

::v-deep(.dark .article-content h5) {
    --tw-text-opacity: 1;
    color: rgb(226 232 240/var(--tw-text-opacity));
}

::v-deep(.dark .article-content h6) {
    --tw-text-opacity: 1;
    color: rgb(226 232 240/var(--tw-text-opacity));
}

/* p 段落样式 */
::v-deep(.dark .article-content p) {
    color: #9e9e9e;
}

/* blockquote 引用样式 */
::v-deep(.dark .article-content blockquote) {
    quotes: none;
    --tw-bg-opacity: 1;
    background-color: rgb(31 41 55 / var(--tw-bg-opacity));
    border-left: 2.3px solid #555;
    color: #666;
    font-size: 16px;
    margin-bottom: 20px;
    padding: 0.25rem 0 0.25rem 1rem;
}

/* ul 样式 */
::v-deep(.dark .article-content ul) {
    padding-left: 2rem;
    color: #9e9e9e;
}

/* ol 样式 */
::v-deep(.dark .article-content ol) {
    color: #9e9e9e;
}

/* code 样式 */
::v-deep(.dark .article-content code:not(pre code)) {
    padding: 2px 4px;
    margin: 0 2px;
    font-size: .85em;
    border-radius: 5px;
    color: #abb2bf;
    background: #333;
    /* background-color: rgba(27, 31, 35, 0.05); */
    font-family: Operator Mono, Consolas, Monaco, Menlo, monospace;
}

/* 表格样式 */
::v-deep(.dark table tr) {
    background-color: rgb(31 41 55 / 1);
}

::v-deep(.dark table) {
    color: #9e9e9e;
}

::v-deep(.dark table th) {
    border: 1px solid #394048;
}

::v-deep(.dark table td) {
    border: 1px solid #394048;
}

::v-deep(.dark table tr:nth-child(2n)) {
    background-color: rgb(21 41 55 / 1);
}

/* hr 横线 */
::v-deep(.dark hr) {
    --tw-border-opacity: 1;
    border-color: rgb(55 65 81 / var(--tw-border-opacity));
}
</style>
