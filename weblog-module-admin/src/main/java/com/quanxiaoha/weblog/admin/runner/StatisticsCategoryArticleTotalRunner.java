package com.quanxiaoha.weblog.admin.runner;

import com.quanxiaoha.weblog.admin.service.AdminStatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StatisticsCategoryArticleTotalRunner implements CommandLineRunner {
    @Autowired
    private AdminStatisticsService adminStatisticsService;

    /**
     * 初始化 统计分类下文章数量
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    @Async("threadPoolTaskExecutor")
    public void run(String... args) throws Exception {
        log.info("==> 开始统计各分类下文章数量...");
        adminStatisticsService.statisticsCategoryArticleTotal();
        log.info("==> 结束统计各分类下文章数量...");
    }
}
