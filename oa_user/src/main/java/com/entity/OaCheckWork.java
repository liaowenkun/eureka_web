package com.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@TableName(value = "oa_check_work")
public class OaCheckWork {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 上班打卡时间
     */
    @TableField(value = "clock_in_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date clockInTime;

    /**
     * 上班打卡地址
     */
    @TableField(value = "clock_in_position")
    private String clockInPosition;

    /**
     * 下班打卡时间
     */
    @TableField(value = "clock_out_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date clockOutTime;

    /**
     * 下班打卡地址
     */
    @TableField(value = "clock_out_position")
    private String clockOutPosition;
}