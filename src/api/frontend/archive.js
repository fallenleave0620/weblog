import axios from '@/axios'
export function getArchivePageList(data) {
    return axios.post('/archive/list',data)
}