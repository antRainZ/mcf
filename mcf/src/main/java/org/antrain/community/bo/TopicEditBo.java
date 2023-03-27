package org.antrain.community.bo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;


/**
 * 主题编辑对象 topic
 *
 * @author antrain
 * @date 2021-04-11
 */
@Data
@ApiModel("主题编辑对象")
public class TopicEditBo {

    /** 主题主键 */
    private Long id;

    /** 标题 */
    @ApiModelProperty("标题")
    private String title;


    /** 封面 */
    @ApiModelProperty("封面")
    private String cover;

    /** 描述 */
    @ApiModelProperty("描述")
    private String description;

    /** 申请文件 */
    @ApiModelProperty("申请文件")
    private String fileUrl;

    /** 理由 */
    @ApiModelProperty("理由")
    private String reason;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("是否加精，会显示在菜单栏里")
    private String refine;
    /** 是否有效：A 申请中  Y有效 默认， N 无效  R 拒绝*/
    @ApiModelProperty("状态")
    private String status;
}
