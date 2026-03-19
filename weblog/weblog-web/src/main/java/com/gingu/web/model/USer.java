package com.gingu.web.model;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class USer {
    @NotBlank(message = "用户名不能为空")
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
}
