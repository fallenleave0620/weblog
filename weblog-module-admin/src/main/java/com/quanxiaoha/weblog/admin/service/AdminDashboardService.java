package com.quanxiaoha.weblog.admin.service;

import com.quanxiaoha.weblog.common.utils.Response;

public interface AdminDashboardService {
    Response findDashboardStatistics();
    Response findDashboardPublishArticleStatistics();
    /**
     * 获取文章最近一周 PV 访问量统计信息
     * @return
     */
    Response findDashboardPVStatistics();
}
