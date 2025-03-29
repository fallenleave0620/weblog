<template>
    <Header></Header>
    <!-- 主内容区域 -->
    <main class="container max-w-screen-xl mx-auto p-4">
        <!-- grid 表格布局，分为 4 列 -->
        <div class="grid grid-cols-4 gap-7">
            <!-- 左边栏，占用 3 列 -->
            <div class="col-span-4 md:col-span-3 mb-3">
                <!-- 文章列表，grid 表格布局，分为 2 列 -->
                <div class="grid grid-cols-2 gap-4">
                    <div class="col-span-2 md:col-span-1 " v-for="(article, index) in articles" :key="index">
                        <div
                            class="h-full hover:scale-[1.03] bg-white border border-gray-200 rounded-lg shadow-sm dark:bg-gray-800 dark:border-gray-700">
                            <!-- 文章封面 -->
                            <a href="#" @click.prevent="goArticleDetailPage(article.id)">
                                <img class="rounded-t-lg" :src="article.cover" alt="" />
                            </a>
                            <div class="p-5">
                                <!-- 标签 -->
                                <div class="mb-3">
                                    <span v-for="(tag, tagIndex) in article.tag" :key="tagIndex"
                                        class="cursor-pointer bg-green-100 text-green-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded hover:bg-green-200 hover:text-green-900 dark:bg-green-900 dark:text-green-300">{{
                                            tag.name }}</span>

                                </div>
                                <!-- 文章标题 -->
                                <a href="#" @click.prevent="goArticleDetailPage(article.id)">
                                    <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">
                                        <span class="hover:border-gray-600 hover:border-b-2 dark:hover:border-gray-400">{{ article.title }}</span>
                                    </h5>
                                </a>
                                <!-- 文章简介 -->
                                <p v-if="article.summary" class="mb-3 font-normal text-gray-700 dark:text-gray-400">
                                    {{ article.summary }}
                                </p>
                                <!-- 文章发布时间、所属分类 -->
                                <p class="flex items-center font-normal text-gray-400 text-sm dark:text-gray-400">
                                    <!-- 发布时间 -->
                                    <svg class="inline w-3 h-3 mr-2 text-gray-400 " aria-hidden="true"
                                        xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                            stroke-width="2"
                                            d="M5 1v3m5-3v3m5-3v3M1 7h18M5 11h10M2 3h16a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1Z" />
                                    </svg>
                                    {{ article.createDate }}

                                    <!-- 所属分类 -->
                                    <svg class="inline w-3 h-3 ml-5 mr-2 text-gray-400 "
                                        aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                                        viewBox="0 0 18 18">
                                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                            stroke-width="2"
                                            d="M1 5v11a1 1 0 0 0 1 1h14a1 1 0 0 0 1-1V6a1 1 0 0 0-1-1H1Zm0 0V2a1 1 0 0 1 1-1h5.443a1 1 0 0 1 .8.4l2.7 3.6H1Z" />
                                    </svg>
                                    <a href="#" class="text-gray-400 hover:underline"
                                        @click.prevent="goCategoryAricleListPage(article.category.id, article.category.name)">{{
                                            article.category.name }}</a>
                                </p>

                            </div>
                        </div>

                    </div>
                </div>
                <!-- 分页插件 -->
                <nav aria-label="Page navigation example" class="mt-5">
                    <ul class="flex items-center -space-x-px h-10 text-base justify-center">
                        <li>
                            <a href="#" @click="getArticles(current - 1)"
                                :class="[current > 1 ? '' : 'cursor-not-allowed']"
                                class="flex items-center justify-center px-4 h-10 ms-0 leading-tight text-gray-500 bg-white border border-e-0 border-gray-300 rounded-s-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
                                <span class="sr-only">上一页</span>
                                <svg class="w-3 h-3 rtl:rotate-180" aria-hidden="true"
                                    xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                        stroke-width="2" d="M5 1 1 5l4 4" />
                                </svg>
                            </a>
                        </li>
                        <!-- <li>
                            <a href="#" v-for="(pageNo, index) in pages" :key="index"
                                class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">1</a>
                            {{ index + 1 }}
                        </li> -->
                        <!-- 页码 -->
                        <li v-for="(pageNo, index) in pages" :key="index">
                            <a @click="getArticles(pageNo)"
                                class="flex items-center justify-center px-4 h-10 leading-tight border  dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
                                :class="[pageNo == current ? 'text-sky-600  bg-sky-50 border-sky-500 hover:bg-sky-100 hover:text-sky-700' : 'text-gray-500 border-gray-300 bg-white hover:bg-gray-100 hover:text-gray-700']">
                                {{ index + 1 }}
                            </a>
                        </li>
                        <!-- <li v-for="(pageNo, index) in pages" :key="index">
                            <a @click="getArticles(pageNo)"
                                class="flex items-center justify-center px-4 h-10 leading-tight bg-white border border-gray-300 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
                                :class="[pageNo == current ? 'text-blue-600 bg-blue-50 hover:bg-blue-100 hover:text-blue-700' : 'text-gray-500  hover:bg-gray-100 hover:text-gray-700']">
                                {{ index + 1 }}
                            </a>
                        </li> -->
                        <li>
                            <a href="#" @click="getArticles(current + 1)"
                                :class="[current < pages ? '' : 'cursor-not-allowed']"
                                class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 rounded-e-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
                                <span class="sr-only">下一页</span>
                                <svg class="w-3 h-3 rtl:rotate-180" aria-hidden="true"
                                    xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                        stroke-width="2" d="m1 9 4-4-4-4" />
                                </svg>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            <!-- 右边侧边栏，占用一列 -->
            <aside class="col-span-4 md:col-span-1 animate__animated animate__fadeInUp">
                <div class="sticky top-[5.5rem]">
                    <!-- 博主信息 -->
                    <UserInfoCard></UserInfoCard>
                    <!-- 分类 -->
                    <CategoryListCard></CategoryListCard>
                    <!-- 标签 -->
                    <TagListCard></TagListCard>
                </div>
            </aside>
        </div>
    </main>
    <ScrollToTopButton></ScrollToTopButton>
    <Footer></Footer>
</template>

<script setup>
import ScrollToTopButton from '@/layouts/frontend/components/ScrollToTopButton.vue'
import Header from '@/layouts/frontend/components/Header.vue'
import Footer from '@/layouts/frontend/components/Footer.vue'
import UserInfoCard from '@/layouts/frontend/components/UserInfoCard.vue'
import CategoryListCard from '@/layouts/frontend/components/CategoryListCard.vue'
import TagListCard from '@/layouts/frontend/components/TagListCard.vue'
import { ref } from 'vue'
// import { getCategoryArticlePageList } from '@/api/frontend/category'
import { getArticlePageList } from '@/api/frontend/article'
// import { getCategoryList } from '@/api/frontend/category'
import { useRouter } from 'vue-router'
const router = useRouter()
// 所有分类
// const categories = ref([])
// 监听路由
// watch(route, (newRoute, oldRoute) => {
//     categoryName.value = newRoute.query.name
//     categoryId.value = newRoute.query.id
//     getCategoryArticles(current.value)
// })
// 所有分类
// const categories = ref([])
// getCategoryList().then((res) => {
//     if (res.success) {
//         categories.value = res.data
//     }
// })
//文章集合
const articles = ref([])
//当前页码
const current = ref(1)
//每页显示的文章数
const size = ref(10)
// 总文章数
const total = ref(0)
// 总共多少页
const pages = ref(0)

//获取指定页的文章数据
function getArticles(currentNo) {
    // 上下页是否能点击判断，当要跳转上一页且页码小于 1 时，则不允许跳转；当要跳转下一页且页码大于总页数时，则不允许跳转
    if (currentNo < 1 || (pages.value > 0 && currentNo > pages.value)) return
    //调用分页接口渲染数据
    getArticlePageList({ current: currentNo, size: size.value }).then((res) => {
        if (res.success) {
            articles.value = res.data
            current.value = res.current
            size.value = res.size
            total.value = res.total
            pages.value = res.pages
            // console.log(res.data)
        }
    })
    // 调用分页接口渲染数据
    // getCategoryArticlePageList({current: currentNo, size: size.value, id: categoryId.value}).then((res) => {
    //     if (res.success) {
    //         articles.value = res.data
    //         current.value = res.current
    //         size.value = res.size
    //         total.value = res.total
    //         pages.value = res.pages
    //         console.log(articles)
    //     }
    // })
}
getArticles(current.value)
const goCategoryAricleListPage = (id, name) => {
    router.push({ path: '/category/article/list', query: { id, name } })
}
const goArticleDetailPage = (articleId) => {
    router.push({ path: '/article/' + articleId })
}
</script>
