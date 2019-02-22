package com.demo.springboot;

import com.demo.springboot.wyb.HelloWeChat;
import io.github.biezhi.wechat.api.constant.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableAsync
@EnableScheduling
@EnableAspectJAutoProxy
@Slf4j
public class SpringbootApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringbootApplication.class, args);
		new HelloWeChat(Config.me().autoLogin(true).showTerminal(true)).start();
	}
}
