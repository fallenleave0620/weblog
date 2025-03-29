<template>
    <Header></Header>

    <!-- 主内容区域 -->
    <main class="container max-w-screen-xl mx-auto p-4 px-6">
        <!-- grid 表格布局，分为 4 列 -->
        <div class="grid grid-cols-4 gap-7">
            <!-- 左边栏，占用 3 列 -->
            <div class="col-span-4 md:col-span-3 mb-3">
                <!-- 标签 -->
                <div v-if="tags && tags.length > 0"
                    class="w-full p-5 pb-7 mb-3 bg-white border border-gray-200 rounded-lg dark:bg-gray-800 dark:border-gray-700">
                    <!-- 标签标题 -->
                    <h2 class="flex items-center mb-5 font-bold text-gray-900 uppercase dark:text-white">
                        <!-- 标签图标 -->
                        <svg t="1741760070486" class="icon inline w-4 h-4 mr-2" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
            p-id="3650" width="200" height="200">
            <path
                d="M531.071917 56.831751l402.814237-20.671909c37.055838 0 67.199706 30.079868 67.199706 67.071706l-18.047921 404.478231c0 31.807861-15.807931 47.039794-37.759835 68.351701l-10.047956 9.855957-398.718255 397.822259A136.959401 136.959401 0 0 1 439.040319 1023.99552a137.0874 137.0874 0 0 1-97.279574-40.127824L56.193994 698.876942a135.935405 135.935405 0 0 1-40.319823-97.151575c0-36.735839 14.207938-71.295688 40.319823-97.151575l398.718256-397.886259c5.439976-5.759975 10.239955-11.007952 14.399937-15.743931 20.799909-22.719901 31.167864-34.17585 61.69573-34.17585z m162.687288 425.598138c35.327845 0 70.527691-13.439941 97.343574-40.191824a136.319404 136.319404 0 0 0 40.447823-97.279574c0-36.67184-14.335937-71.231688-40.447823-97.087575A137.791397 137.791397 0 0 0 556.159807 344.958491c0 36.735839 14.271938 71.295688 40.319823 97.151575 26.879882 26.879882 62.079728 40.191824 97.343575 40.191824z m-41.599818-178.879217a58.751743 58.751743 0 0 1 41.599818-17.087925 58.751743 58.751743 0 0 1 58.751743 58.623743 58.879742 58.879742 0 0 1-117.503486 0c0-15.615932 6.143973-30.399867 17.215925-41.535818zM258.561109 504.765792a47.167794 47.167794 0 0 0-66.879708-0.064 47.487792 47.487792 0 0 0-0.127999 67.071706l273.470804 275.198796 0.063999 0.192a47.167794 47.167794 0 0 0 66.751708 0c18.559919-18.559919 18.559919-48.511788 0.128-67.135707l-273.406804-275.198796z"
                fill="#51A9FF" p-id="3651"></path>
        </svg>
                        标签
                        <span class="ml-2 text-gray-600 font-normal dark:text-gray-300">( {{ tags.length }} )</span>
                    </h2>

                    <!-- 标签列表 -->
                    <div class="flex flex-wrap gap-3">
                        <a v-for="(tag, index) in tags" :key="index" @click="goTagArticleListPage(tag.id, tag.name)"
                            class="cursor-pointer inline-flex items-center px-3.5 py-1.5 text-xs font-medium text-center border rounded-[12px]
            hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-gray-300 
            dark:bg-gray-800 dark:text-gray-300 dark:hover:bg-gray-700 dark:focus:ring-gray-800 
            dark:border-gray-700 dark:hover:text-white">
                            {{ tag.name }}
                            <span
                                class="inline-flex items-center justify-center w-4 h-4 ms-2 text-xs font-semibold text-sky-800 bg-sky-200 rounded-full">
                                {{ tag.articlesTotal }}
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
                    <!-- 分类 -->
                    <CategoryListCard></CategoryListCard>
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
import { getTagList } from '@/api/frontend/tag'
import { ref } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()
// 所有标签
const tags = ref([])
getTagList().then((res) => {
    if (res.success) {
        tags.value = res.data
        // console.log(tags.value)
    }
})
// 跳转标签文章列表页
const goTagArticleListPage = (id, name) => {
    // 跳转时通过 query 携带参数（标签 ID、标签名称）
    router.push({ path: '/tag/article/list', query: { id, name } })
}
</script>
