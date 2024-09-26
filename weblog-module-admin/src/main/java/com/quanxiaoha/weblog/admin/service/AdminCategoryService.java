package com.quanxiaoha.weblog.admin.service;

import com.quanxiaoha.weblog.admin.module.vo.category.AddCategoryReqVO;
import com.quanxiaoha.weblog.common.utils.Response;

public interface AdminCategoryService {
    /**
     * 添加分类
     * @param addCategoryReqVO
     * @return
     */
    Response addCategory(AddCategoryReqVO addCategoryReqVO);
}

