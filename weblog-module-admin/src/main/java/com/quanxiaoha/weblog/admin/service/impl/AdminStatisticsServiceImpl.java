package com.quanxiaoha.weblog.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Maps;
import com.quanxiaoha.weblog.admin.service.AdminStatisticsService;
import com.quanxiaoha.weblog.common.domain.dos.ArticleCategoryRelDO;
import com.quanxiaoha.weblog.common.domain.dos.ArticleTagRelDO;
import com.quanxiaoha.weblog.common.domain.dos.CategoryDO;
import com.quanxiaoha.weblog.common.domain.dos.TagDO;
import com.quanxiaoha.weblog.common.domain.mapper.ArticleCategoryRelMapper;
import com.quanxiaoha.weblog.common.domain.mapper.ArticleTagRelMapper;
import com.quanxiaoha.weblog.common.domain.mapper.CategoryMapper;
import com.quanxiaoha.weblog.common.domain.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminStatisticsServiceImpl implements AdminStatisticsService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ArticleCategoryRelMapper articleCategoryRelMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ArticleTagRelMapper articleTagRelMapper;


    /**
     * 统计各分类下文章总数
     */
    @Override
    public void statisticsCategoryArticleTotal() {

        List<CategoryDO> categoryDOS = categoryMapper.selectList(Wrappers.emptyWrapper());

        List<ArticleCategoryRelDO> articleCategoryRelDOS = articleCategoryRelMapper.selectList(Wrappers.emptyWrapper());

        Map<Long, List<ArticleCategoryRelDO>> categoryIdAndArticleCategoryRelDOMap = Maps.newHashMap();

        if (!CollectionUtils.isEmpty(articleCategoryRelDOS)) {
            categoryIdAndArticleCategoryRelDOMap = articleCategoryRelDOS.stream()
                    .collect(Collectors.groupingBy(ArticleCategoryRelDO::getCategoryId));
        }
        if (!CollectionUtils.isEmpty(categoryDOS)) {
            for (CategoryDO categoryDO : categoryDOS) {
                Long categoryId = categoryDO.getId();
                List<ArticleCategoryRelDO> articleCategoryRelDOList = categoryIdAndArticleCategoryRelDOMap.get(categoryId);
                int articlesTotal = CollectionUtils.isEmpty(articleCategoryRelDOList) ? 0 : articleCategoryRelDOList.size();
                CategoryDO categoryDO1 = CategoryDO.builder()
                        .id(categoryId)
                        .articlesTotal(articlesTotal)
                        .build();
                categoryMapper.updateById(categoryDO1);
            }
        }
    }

    /**
     * 统计各标签下文章总数
     */
    @Override
    public void statisticsTagArticleTotal() {
        // 查询所有标签
        List<TagDO> tagDOS = tagMapper.selectList(Wrappers.emptyWrapper());
        // 查询所有文章-标签映射记录
        List<ArticleTagRelDO> articleTagRelDOS = articleTagRelMapper.selectList(Wrappers.emptyWrapper());
        // 按所属标签 ID 进行分组
        Map<Long, List<ArticleTagRelDO>> tagIdAndArticleTagRelDOS = Maps.newHashMap();
        // 如果不为空
        if (CollectionUtils.isNotEmpty(articleTagRelDOS)) {
            tagIdAndArticleTagRelDOS = articleTagRelDOS
                    .stream()
                    .collect(Collectors.groupingBy(ArticleTagRelDO::getTagId));
        }
        // 循环统计各标签下的文章总数
        if(CollectionUtils.isNotEmpty(tagDOS)){
            for (TagDO tagDO : tagDOS) {
                Long tagId = tagDO.getId();
                // 获取此标签下所有映射记录
                List<ArticleTagRelDO> articleTagRelDOSByTagId = tagIdAndArticleTagRelDOS.get(tagId);
                // 获取文章总数
                int articlesTotal = CollectionUtils.isNotEmpty(articleTagRelDOSByTagId) ? articleTagRelDOSByTagId.size() : 0;
                // 更新该标签的文章总数
                TagDO tagDO1 = TagDO.builder()
                        .id(tagId)
                        .articlesTotal(articlesTotal)
                        .build();
                tagMapper.updateById(tagDO1);
            }
        }

    }
}
