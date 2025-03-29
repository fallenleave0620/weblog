package com.quanxiaoha.weblog.web.model.vo.search;

import com.quanxiaoha.weblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "文章搜索VO")
public class SearchArticlePageListReqVO extends BasePageQuery {
    /**
     * 关键字查询
     *
     */
    @NotBlank(message = "搜索关键词不能为空")
    private String word;
}
