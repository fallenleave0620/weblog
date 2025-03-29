package com.quanxiaoha.weblog.web.model.vo.tag;

import com.quanxiaoha.weblog.common.model.BasePageQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindTagArticlePageListReqVO extends BasePageQuery {

//    标签ID查询关联文章
    @NotNull(message="标签 ID不能为空")
    private Long id;
}
