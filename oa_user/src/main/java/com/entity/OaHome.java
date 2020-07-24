package com.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

@Data
@TableName(value = "oa_home")
public class OaHome {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 经度
     */
    @TableField(value = "longitude")
    private String longitude;

    /**
     * 纬度
     */
    @TableField(value = "latitude")
    private String latitude;

    /**
     * 打卡范围(米)
     */
    @TableField(value = "maximum")
    private Integer maximum;

    /**
     * 上班时间
     */
    @TableField(value = "work_time")
    private Date workTime;

    /**
     * 下班时间
     */
    @TableField(value = "closing_time")
    private Date closingTime;
}