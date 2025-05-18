package com.cui.pojo;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
@Data
public class User {
    @NotNull//限制非空
    private Integer id;//主键ID
    private String username;//用户名
    @JsonIgnore//把pwd转化为Json字符串时忽略不显示 // TODO 有的注释名字一样没有效果？
    private String password;//密码
    @NotEmpty//非空 非空字符串
    @Pattern(regexp = "^\\S{1,10}$")// \ \转义字符
    private String nickname;//昵称
    @Email
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
