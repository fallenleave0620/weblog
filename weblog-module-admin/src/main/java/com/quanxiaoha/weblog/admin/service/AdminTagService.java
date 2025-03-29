package com.quanxiaoha.weblog.admin.service;

import com.quanxiaoha.weblog.admin.module.vo.tag.AddTagReqVO;
import com.quanxiaoha.weblog.admin.module.vo.tag.DeleteTagReqVO;
import com.quanxiaoha.weblog.admin.module.vo.tag.FindTagPageListReqVO;
import com.quanxiaoha.weblog.admin.module.vo.tag.SearchTagReqVO;
import com.quanxiaoha.weblog.common.utils.PageResponse;
import com.quanxiaoha.weblog.common.utils.Response;

public interface AdminTagService {
//    /**
//     * 添加分类
//     * @param addCategoryReqVO
//     * @return
//     */
//    Response addCategory(AddCategoryReqVO addCategoryReqVO);
//    /**
//     * 分类分页数据查询
//     * @param findCategoryPageListReqVO
//     * @return
//     */
//    PageResponse findCategoryList(FindCategoryPageListReqVO findCategoryPageListReqVO);
//    /**
//     * 删除分类
//     * @param deleteCategoryReqVO
//     * @return
//     */
//    Response deleteCategory(DeleteCategoryReqVO deleteCategoryReqVO);
//    /**
//     * 获取文章分类的 Select 列表数据
//     * @return
//     */
//    Response findCategorySelectList();

    /**
     * 添加分类
     *
     * @param addTagReqVO
     * @return
     */
    Response addTag(AddTagReqVO addTagReqVO);

    /**
     * 标签分页数据查询
     *
     * @param findTagListReqVO
     * @return
     */

    PageResponse findTagPageList(FindTagPageListReqVO findTagListReqVO);

    /**
     * 删除标签
     *
     * @param deleteTagReqVO
     * @return
     */

    Response deleteTag(DeleteTagReqVO deleteTagReqVO);

    Response searchTag(SearchTagReqVO searchTagReqVO);

    /**
     * 查询标签 Select 列表数据
     * @return
     */
    Response findTagSelectList();
}

