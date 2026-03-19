package com.gingu.web.model;

import javax.validation.constraints.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@ApiModel(value = "用户实体类")
public class USer {
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String name;
    @NotNull(message = "性别不能为空")
    private Integer sex;
    @NotNull(message = "年龄不能为空")
    @Min(value = 18,message = "未成年退款")
    @Max(value = 100,message = "该死了老登")
    private Integer age;
    @Email(message = "邮箱格式不正确")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    // 创建时间
    private LocalDateTime createTime;
    // 更新日期
    private LocalDate updateDate;
    // 时间
    private LocalTime time;
}
