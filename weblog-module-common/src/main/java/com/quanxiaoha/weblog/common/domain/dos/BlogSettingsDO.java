package com.quanxiaoha.weblog.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_blog_settings")

public class BlogSettingsDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String author;

    private String avatar;

    private String introduction;

    private String logo;

    private String name;

    private String csdnHomepage;

    private String giteeHomepage;

    private String githubHomepage;

    private String cnblogsHomepage;
}
