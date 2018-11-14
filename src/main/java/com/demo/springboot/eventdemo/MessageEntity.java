package com.demo.springboot.eventdemo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: user
 * @Date: 2018/11/7
 * @Description: 消息实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {
    String message;

    String code;
}
