package com.quanxiaoha.weblog.web.model.vo.article;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "article detail search interface")
public class FindArticleDetailReqVO {
//    articleId
    @NotNull(message = "articleId don't allow empty")
    private Long articleId;
}
