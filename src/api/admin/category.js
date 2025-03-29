import axios from "@/axios";

// 获取分类分页数据
export function getCategoryPageList(data) {
    return axios.post("/admin/category/list", data)
}
// 添加分类
export function addCategory(data) {
    return axios.post("/admin/category/add", data)
}
// 删除分类
export function deleteCategory(id) {
    return axios.post("/admin/category/delete", {id})
}
// 注意，传入的入参是一个 id , 但是请求接口的时候，我们将其包装成了一个对象的形式：{id : id} ， {id} 是其简写模式。
export function getCategorySelectList(){
    return axios.post("/admin/category/select/list")
}