package com.zhoumoumou.yunmayi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: zhouqie
 * @date 2023/12/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo{
    String username;
    String password;
}
