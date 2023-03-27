package org.antrain.article.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;


/**
 * 文章评论编辑对象 article_comment
 *
 * @author antrain
 * @date 2021-04-11
 */
@Data
@ApiModel("文章评论编辑对象")
public class ArticleCommentEditBo {

    private Long id;
    /** 对应文章主键 */
    @ApiModelProperty("对应文章主键")
    private Long articleId;

    /** 对应文章标题 */
    @ApiModelProperty("对应文章标题")
    private String articleTitle;

    /** 评论用户id */
    @ApiModelProperty("评论用户id")
    private Long userId;

    /** 评论用户昵称 */
    @ApiModelProperty("评论用户昵称")
    private String nickname;

    /** 内容 */
    @ApiModelProperty("内容")
    private String content;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;

    /** 是否有效： Y有效 默认， N 无效 */
    @ApiModelProperty("是否有效： Y有效 默认， N 无效")
    private String status;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Date updateTime;
}
