package org.antrain.article.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 文章添加对象 article
 *
 * @author antrain
 * @date 2021-04-11
 */
@Data
@ApiModel("文章添加对象")
public class ArticleAddBo {

    /** 创办用户id */
    private Long userId;

    /** 创办用户昵称 */
    private String nickname;


    /** 主题标题 */
    @ApiModelProperty("主题标题")
    private String title;
    /** 内容 */
    @ApiModelProperty("内容")
    private String content;
    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;

}
