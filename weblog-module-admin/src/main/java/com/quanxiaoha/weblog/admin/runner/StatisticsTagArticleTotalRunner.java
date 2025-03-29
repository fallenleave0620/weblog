package com.quanxiaoha.weblog.admin.runner;

import com.quanxiaoha.weblog.admin.service.AdminStatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StatisticsTagArticleTotalRunner implements CommandLineRunner {
    @Autowired
    private AdminStatisticsService adminStatisticsService;
    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    @Async("threadPoolTaskExecutor")
    public void run(String... args) throws Exception {
            log.info("==> 开始统计各标签下文章数量...");
            adminStatisticsService.statisticsTagArticleTotal();
            log.info("==> 结束统计各标签下文章数量...");
    }
}
