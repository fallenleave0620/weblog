package com.quanxiaoha.weblog.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.quanxiaoha.weblog.common.domain.dos.ArticleDO;
import com.quanxiaoha.weblog.common.domain.mapper.ArticleMapper;
import com.quanxiaoha.weblog.common.domain.mapper.CategoryMapper;
import com.quanxiaoha.weblog.common.domain.mapper.TagMapper;
import com.quanxiaoha.weblog.common.utils.Response;
import com.quanxiaoha.weblog.web.model.vo.statistics.FindStatisticsInfoRspVO;
import com.quanxiaoha.weblog.web.service.StatisticsService;
import io.minio.messages.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CategoryMapper categoryMapper;
    /**
     * 获取文章总数、分类总数、标签总数、总访问量统计信息
     *
     * @return
     */
    @Override
    public Response findInfo() {

        Long tagTotalCount = tagMapper.selectCount(Wrappers.emptyWrapper());

        Long articleTotalCount = articleMapper.selectCount(Wrappers.emptyWrapper());

        Long categoryTotalCount = categoryMapper.selectCount(Wrappers.emptyWrapper());

        List<ArticleDO> articleDOS = articleMapper.selectAllReadNum();
        Long pvTotalCount = 0L;

        if(!CollectionUtils.isEmpty(articleDOS)){
           pvTotalCount =  articleDOS.stream().mapToLong(ArticleDO::getReadNum).sum();
        }

        FindStatisticsInfoRspVO vo = FindStatisticsInfoRspVO.builder()
                .tagTotalCount(tagTotalCount)
                .articleTotalCount(articleTotalCount)
                .categoryTotalCount(categoryTotalCount)
                .pvTotalCount(pvTotalCount)
                .build();

        return Response.success(vo);
    }
}
