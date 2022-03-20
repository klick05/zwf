package com.zwf.upms.api.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 描述: 部门关系表
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/20 14:30:47
 */
@Data
@ApiModel(value = "部门关系")
@EqualsAndHashCode(callSuper = true)
public class SysDeptRelation extends Model<SysDeptRelation> {

    private static final long serialVersionUID = 1L;

    /**
     * 祖先节点
     */
    @ApiModelProperty(value = "祖先节点")
    private Integer ancestor;

    /**
     * 后代节点
     */
    @ApiModelProperty(value = "后代节点")
    private Integer descendant;

}
