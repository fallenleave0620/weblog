import axios from "@/axios";

// 获取标签列表
export function getTagList(data) {
    return axios.post("/tag/list", data)
}
export function getTagArticlePageList(data) {
    return axios.post("/tag/article/list", data)
}