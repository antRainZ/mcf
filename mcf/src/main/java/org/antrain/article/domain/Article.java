package org.antrain.article.domain;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.math.BigDecimal;
import java.util.List;

import org.antrain.common.annotation.Excel;

/**
 * 文章对象 article
 * 
 * @author antrain
 * @date 2021-04-11
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("article")
public class Article implements Serializable {

private static final long serialVersionUID=1L;


    /** 文章主键 */
    @TableId(value = "id")
    private Long id;

    /** 主题标题 */
    private String title;

    /** 内容 */
    private String content;

    /** 备注 */
    private String remark;

    /** 创办用户id */
    private Long userId;

    /** 创办用户昵称 */
    private String nickname;

    /** 是否加精 */
    private String refine;

    /** 是否有效： Y有效 默认， N 无效 */
    private String status;

    /** 是否有效： Y有效 默认， N 无效 */
    private String labels;

    @TableField(exist = false)
    private List<String> labelName;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 修改时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    public static List<Article> setListOfLabelName(List<Article> list, JSONObject all) {
        for ( Article article: list ) {
            if (StrUtil.isNotBlank(article.getLabels())) {
                String[] ids = article.getLabels().split(",");
                List<String> labelName = new ArrayList<>();
                for (String id: ids) {
                    labelName.add(all.get(id).toString());
                }
                article.setLabelName(labelName);
            }
        }
        return list;
    }
}
