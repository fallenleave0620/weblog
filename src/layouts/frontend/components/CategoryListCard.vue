<template>
    <div v-if="categories && categories.length > 0"
        class="w-full p-5 mt-6 bg-white border border-gray-200 rounded-lg dark:bg-gray-800 dark:border-gray-700">
        <!-- 分类标题 -->
        <div class="flex mb-3">
            <h2 class="flex items-center font-bold text-gray-900 uppercase dark:text-white">
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
            </h2>
            <span class="grow"></span>

            <!-- 查看更多 -->
            <a @click="router.push('/category/list')" class=" bg-gray-100 flex items-center px-2.5 py-1 text-xs font-medium text-center 
            text-gray-900  rounded-lg hover:bg-gray-200 focus:ring-4 focus:outline-none focus:ring-gray-200 
            dark:bg-gray-800 dark:text-white dark:border dark:border-gray-700 dark:hover:bg-gray-700 dark:hover:border-gray-700
             dark:focus:ring-gray-700">
                <svg class="w-[7px] h-[7px] text-gray-400 dark:text-white" aria-hidden="true"
                    xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 8 14">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                        d="m1 13 5.7-5.326a.909.909 0 0 0 0-1.348L1 1" />
                </svg>
            </a>

        </div>

        <!-- 分类列表 -->
        <div
            class="text-sm flex flex-wrap gap-3 font-medium text-gray-600 rounded-lg dark:border-gray-600 dark:text-white">
            <a @click="goCategoryAricleListPage(category.id, category.name)" v-for="(category, index) in categories"
                :key="index"
                class="cursor-pointer inline-flex items-center px-3 py-1.5 text-xs font-medium text-center border rounded-lg 
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
</template>
<script setup>
import { getCategoryList } from '@/api/frontend/category'
import { ref } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()
// 所有分类
const categories = ref([])
getCategoryList().then((res) => {
    if (res.success) {
        categories.value = res.data
    }
})
const goCategoryAricleListPage = (id, name) => {
    router.push({ path: '/category/article/list', query: { id, name } })
}
// 一次显示的分类数
const size = ref(10)

getCategoryList({ size: size.value }).then((res) => {
    if (res.success) {
        categories.value = res.data
    }
})
</script>