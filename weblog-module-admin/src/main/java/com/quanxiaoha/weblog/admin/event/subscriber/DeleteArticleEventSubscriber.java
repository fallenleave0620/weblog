package com.quanxiaoha.weblog.admin.event.subscriber;

import com.quanxiaoha.weblog.admin.event.DeleteArticleEvent;
import com.quanxiaoha.weblog.admin.service.AdminStatisticsService;
import com.quanxiaoha.weblog.search.LuceneHelper;
import com.quanxiaoha.weblog.search.index.ArticleIndex;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.index.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DeleteArticleEventSubscriber implements ApplicationListener<DeleteArticleEvent> {
    @Autowired
    private LuceneHelper luceneHelper;
    @Autowired
    private AdminStatisticsService adminStatisticsService;

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    @Async("threadPoolTaskExecutor")
    public void onApplicationEvent(DeleteArticleEvent event) {
        Long articleId = event.getArticleId();

        String threadName = Thread.currentThread().getName();

        Term condition = new Term(ArticleIndex.COLUMN_ID, String.valueOf(articleId));
        long count = luceneHelper.deleteIndex(ArticleIndex.NAME, condition);

        log.info("==> 删除文章对应 Lucene 文档结束，articleId: {}，受影响行数: {}", articleId, count);
        adminStatisticsService.statisticsCategoryArticleTotal();
        log.info("==> 重新统计分类文章数量结束");
        // 重新统计各标签下文章总数
        adminStatisticsService.statisticsTagArticleTotal();
        log.info("==> 重新统计各标签下文章总数");

    }
}
