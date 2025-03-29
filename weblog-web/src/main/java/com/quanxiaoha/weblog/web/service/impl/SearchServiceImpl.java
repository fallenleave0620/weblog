package com.quanxiaoha.weblog.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import com.quanxiaoha.weblog.common.utils.PageResponse;
import com.quanxiaoha.weblog.common.utils.Response;
import com.quanxiaoha.weblog.search.LuceneHelper;
import com.quanxiaoha.weblog.search.config.LuceneProperties;
import com.quanxiaoha.weblog.search.index.ArticleIndex;
import com.quanxiaoha.weblog.web.model.vo.search.SearchArticlePageListReqVO;
import com.quanxiaoha.weblog.web.model.vo.search.SearchArticlePageListRspVO;
import com.quanxiaoha.weblog.web.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.StringReader;
import java.util.List;

@Slf4j
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    LuceneProperties luceneProperties;
    @Autowired
    LuceneHelper luceneHelper;

    /**
     * @param searchArticlePageListReqVO searchArticlePageListReqVO
     * @return
     */
    @Override
    public Response searchArticlePageList(SearchArticlePageListReqVO searchArticlePageListReqVO) {

        int current = Math.toIntExact(searchArticlePageListReqVO.getCurrent());
        int size = Math.toIntExact(searchArticlePageListReqVO.getSize());
        String word = searchArticlePageListReqVO.getWord();
        String[] columns = {ArticleIndex.COLUMN_TITLE, ArticleIndex.COLUMN_SUMMARY};
        Long total = luceneHelper.searchTotal(ArticleIndex.NAME,word, columns);

        List<Document> documents = luceneHelper.search(ArticleIndex.NAME,word, columns, current, size);
        if (CollectionUtils.isEmpty(documents)) {
            return PageResponse.success(total, current, size, null);
        }

        Analyzer analyzer = new SmartChineseAnalyzer();
        QueryParser queryParser = new QueryParser(ArticleIndex.COLUMN_TITLE, analyzer);
        Query query = null;
        try {
            query = queryParser.parse(word);
        } catch (Exception e) {
            log.error("关键词解析错误");
        }

        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<span style=\"color: #f73131\">", "</span>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));

        List<SearchArticlePageListRspVO> vos = Lists.newArrayList();
        documents.forEach(document -> {
            try {
                String title = document.get(ArticleIndex.COLUMN_TITLE);
                TokenStream tokenStream = analyzer.tokenStream(ArticleIndex.COLUMN_TITLE,new StringReader(title));
                String titleFragment = highlighter.getBestFragment(tokenStream,title);
                String highlightedTitle = StringUtils.isNoneBlank(titleFragment) ? titleFragment : title;

                String id = document.get(ArticleIndex.COLUMN_ID);
                String summary = document.get(ArticleIndex.COLUMN_SUMMARY);
                String cover = document.get(ArticleIndex.COLUMN_COVER);
                String createTime = document.get(ArticleIndex.COLUMN_CREATE_TIME);

                SearchArticlePageListRspVO vo = SearchArticlePageListRspVO.builder()
                        .title(highlightedTitle)
                        .cover(cover)
                        .summary(summary)
                        .id(Long.valueOf(id))
                        .createDate(createTime)
                        .build();
                vos.add(vo);
            } catch (Exception e) {
                log.error("文档转换错误",e);
            }
        });
        return PageResponse.success(total,current,size,vos);
    }
}
