<template>
    <Header></Header>
    <!-- 主内容区域 -->
    <main class="container max-w-screen-xl mx-auto p-4 px-6">
        <!-- grid 表格布局，分为 4 列 -->
        <div class="grid grid-cols-4 gap-7">
            <!-- 左边栏，占用 3 列 -->
            <div class="col-span-4 md:col-span-3 mb-3">
                <!-- <CategoryListCard></CategoryListCard> -->
                <div
                    class="w-full p-5 pb-7 mb-3 bg-white border border-gray-200 rounded-lg dark:bg-gray-800 dark:border-gray-700">
                    <!-- 分类标题 -->
                    <h2 class="flex items-center mb-5 font-bold text-gray-900 uppercase dark:text-white">
                        <!-- 文件夹图标 -->
                        <svg t="1741759983252" class="icon inline w-5 h-5 mr-2" viewBox="0 0 1024 1024" version="1.1"
                            xmlns="http://www.w3.org/2000/svg" p-id="2642" width="200" height="200">
                            <path
                                d="M51.2 512c0 254.976 205.824 460.8 460.8 460.8s460.8-205.824 460.8-460.8S766.976 51.2 512 51.2 51.2 257.024 51.2 512z"
                                fill="#FFF0F0" p-id="2643"></path>
                            <path
                                d="M448.512 328.704H342.016c-19.456 0-34.816 15.36-34.816 34.816v105.472c0 19.456 15.36 34.816 34.816 34.816h105.472c19.456 0 34.816-15.36 34.816-34.816V364.544c1.024-19.456-14.336-35.84-33.792-35.84M706.56 392.192L631.808 317.44c-13.312-13.312-35.84-13.312-50.176 0l-10.24 10.24-64.512 64.512c-6.144 6.144-10.24 15.36-10.24 24.576 0 9.216 4.096 18.432 10.24 24.576l10.24 10.24 54.272 54.272 10.24 10.24c13.312 13.312 35.84 13.312 50.176 0l64.512-64.512 10.24-10.24c13.312-13.312 13.312-34.816 0-49.152M448.512 540.672H342.016c-19.456 0-34.816 15.36-34.816 34.816v105.472c0 19.456 15.36 34.816 34.816 34.816h105.472c19.456 0 34.816-15.36 34.816-34.816V575.488c1.024-19.456-14.336-34.816-33.792-34.816m210.944 0H553.984c-19.456 0-34.816 15.36-34.816 34.816v105.472c0 19.456 15.36 34.816 34.816 34.816h105.472c19.456 0 34.816-15.36 34.816-34.816V575.488c1.024-19.456-15.36-34.816-34.816-34.816"
                                fill="#F78FBA" p-id="2644"></path>
                        </svg>
                        分类
                        <span v-if="categories && categories.length > 0"
                            class="ml-2 text-gray-600 font-normal dark:text-gray-300">( {{ categories.length }} )</span>
                    </h2>

                    <!-- 分类列表 -->
                    <div
                        class="text-sm flex flex-wrap gap-3 font-medium text-gray-600 rounded-lg dark:border-gray-600 dark:text-white">
                        <a @click="goCategoryArticleListPage(category.id, category.name)"
                            v-for="(category, index) in categories" :key="index"
                            class="cursor-pointer inline-flex items-center px-4 py-2 text-sm font-medium text-center border rounded-lg 
            hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-gray-300 
            dark:bg-gray-800 dark:text-gray-300 dark:hover:bg-gray-700 dark:focus:ring-gray-800 dark:border-gray-700 dark:hover:text-white">
                            {{ category.name }}
                            <span
                                class="inline-flex items-center justify-center w-4 h-4 ms-2 text-xs font-semibold text-sky-800 bg-sky-200 rounded-full">
                                {{ category.articlesTotal }}
                            </span>
                        </a>
                    </div>
                </div>
            </div>

            <!-- 右边侧边栏，占用一列 -->
            <aside class="col-span-4 md:col-span-1">
                <div class="sticky top-[5.5rem]">
                    <!-- 博主信息 -->
                    <UserInfoCard></UserInfoCard>
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
// import CategoryListCard from '@/layouts/frontend/components/CategoryListCard.vue'
import Header from '@/layouts/frontend/components/Header.vue'
import Footer from '@/layouts/frontend/components/Footer.vue'
import UserInfoCard from '@/layouts/frontend/components/UserInfoCard.vue'
import TagListCard from '@/layouts/frontend/components/TagListCard.vue'
import { getCategoryList } from '@/api/frontend/category'
import { ref } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()
// 跳转分类文章列表页
const goCategoryArticleListPage = (id, name) => {
    // 跳转时通过 query 携带参数（分类 ID、分类名称）
    router.push({ path: '/category/article/list', query: { id, name } })
}
// 所有分类
const categories = ref([])
getCategoryList({}).then((res) => {
    if (res.success) {
        categories.value = res.data
        // console.log(categories.value)
    }
})
</script>