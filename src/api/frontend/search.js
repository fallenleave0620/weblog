import axios from '@/axios';
export function getArticleSearchPageList(data) {
    return axios.post('/article/search', data);
}