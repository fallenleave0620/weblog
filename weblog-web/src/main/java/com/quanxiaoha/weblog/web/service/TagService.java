package com.quanxiaoha.weblog.web.service;

import com.quanxiaoha.weblog.common.utils.Response;
import com.quanxiaoha.weblog.web.model.vo.tag.FindTagArticlePageListReqVO;
import com.quanxiaoha.weblog.web.model.vo.tag.FindTagArticlePageListRspVO;
import com.quanxiaoha.weblog.web.model.vo.tag.FindTagListReqVO;

public interface TagService {
    /**
     * 获取标签列表
     * @return
     */
    Response findTagList(FindTagListReqVO findTagList);

    Response findTagePageList(FindTagArticlePageListReqVO findTagArticlePageListReqVO);


}

