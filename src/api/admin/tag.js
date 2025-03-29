import axios from "@/axios";
// 添加分类
export function addTag(data) {
    return axios.post("/admin/tag/add", data)
}
// 获取标签分页数据
export function getTagPageList(data) {
    return axios.post("/admin/tag/list", data)
}
// 删除标签
export function deleteTag(id) {
    return axios.post("/admin/tag/delete", {id})
}
// 根据标签名模糊查询
export function searchTags(key) {
    return axios.post("/admin/tag/search", {key})
}
// 获取标签 select 列表数据
export function getTagSelectList() {
    return axios.post("/admin/tag/select/list")
}

