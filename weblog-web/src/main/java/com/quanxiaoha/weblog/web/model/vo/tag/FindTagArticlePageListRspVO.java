package com.quanxiaoha.weblog.web.model.vo.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindTagArticlePageListRspVO {
//    @NotNull(message = "文章Id不能为空")
    private Long id;
    private String title;
    private String cover;
    /**
     * 发布日期
     */
    private LocalDate createDate;
}
