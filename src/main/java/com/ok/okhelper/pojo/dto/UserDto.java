package com.ok.okhelper.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/*
 *Author:zhangxin_an
 *Description:添加员工
 *Data:Created in 8:33 2018/4/15
 */
@Data
public class UserDto {
    
    /**
     * 用户Id
     */
    @ApiModelProperty(value = "用户Id")
    private Long id;
    
    
    /**
     * 登录账号
     */
    @ApiModelProperty(value = "用户名",required = true)
    @NotNull(message = "用户名不能为空")
    private String userName;
    
    /**
     * 登录密码
     */
    
    @ApiModelProperty(value = "登陆密码",required = true)
    @NotNull(message = "密码不能为空")
    private String userPassword;
    
    /**
     * 密保问题
     */
    @ApiModelProperty(value = "密保问题")
    private String passProblem;
    
    /**
     * 密保答案
     */
    @ApiModelProperty(value = "密保答案")
    private String passAnswer;
    
    /**
     * 昵称
     */
    
    @ApiModelProperty(value = "昵称")
    private String userNick;
    
    /**
     * 头像
     */
    
    @ApiModelProperty(value = "头像")
    private String userAvatar;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String userPhone;
    
    /**
     * 邮箱
     */
    
    @ApiModelProperty(value = "邮箱")
    private String userEmail;
    
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String userSex;
    
    /**
     * 生日
     */
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "生日")
    private Date userBirthday;
    
    /**
     * 状态 0废除，1激活
     */
    @ApiModelProperty(value = "状态")
    private String deleteStatus;

    /**
     * 操作者
     */
    @ApiModelProperty(value = "操作店长")
    private Long operator;
    
    /*
    *所属店铺
    */
    @ApiModelProperty(value = "所属店铺")
    private Long storeId;

}
