package com.demo.springboot.wyb;

import com.demo.springboot.holiday.Holidaytime;
import com.demo.springboot.wyb.util.WeatherPush;
import com.demo.springboot.wyb.util.WeatherSent;
import io.github.biezhi.wechat.WeChatBot;
import io.github.biezhi.wechat.api.annotation.Bind;
import io.github.biezhi.wechat.api.constant.Config;
import io.github.biezhi.wechat.api.enums.MsgType;
import io.github.biezhi.wechat.api.model.WeChatMessage;
import io.github.biezhi.wechat.utils.DateUtils;
import io.github.biezhi.wechat.utils.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

public class HelloWeChat extends WeChatBot {

    public HelloWeChat(Config config) {
        super(config);
    }
//
//    @Bind(msgType = MsgType.TEXT)
//    public void handleText(WeChatMessage message) {
//        if (StringUtils.isNotEmpty(message.getName()) && message.getFromUserName().equals("任宏杰_死党")) {
//            //log.info("接收到 [{}] 的消息: {}", message.getName(), message.getText());
//            System.out.println("+++" + message.getName());
//            this.sendMsg(message.getFromUserName(), Robot.AiMain(message.getText()));
//            //process();
//        }
//    }

    public static void main(String[] args) {
        new HelloWeChat(Config.me().autoLogin(false).showTerminal(true)).start();
    }

    public void other(){
        while(true){
            System.out.println(LocalDateTime.now().toString());
            if(LocalDateTime.now().toString().substring(11,16).equals("05:00") && !Holidaytime.isHoliday()){
                String msg = WeatherSent.sendContent("101010200", "海淀");
                this.sendMsgByName("王贺飞", msg);
                DateUtils.sleep(60000);
            }else{
                DateUtils.sleep(3600000);
                String msg = WeatherSent.sendContent("101010200", "海淀");
                this.sendMsgByName("王贺飞", msg);
                DateUtils.sleep(60000);
            }
            if(LocalDateTime.now().toString().substring(11,16).equals("09:53")){
                String msg = WeatherSent.sendContent("101010200", "海淀");
                this.sendMsgByName("王贺飞", msg);
                DateUtils.sleep(600000);
            }
        }
    }
}