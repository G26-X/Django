package com.migu.tntsb.tntsb;

import com.migu.tntsb.tntsb.mailService.mailSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SendemailApplicationTests {

    /**
     * 注入发送邮件的接口
     */
    @Autowired
    private mailSender mailService;

    /**
     * 测试发送文本邮件
     */
    @Test
    public void sendmail() {
        mailService.sendSimpleMail("lilimul@yeah.net","测试纯文本文件","邮件内容应该是纯文本");
    }

    @Test
    public void sendmailHtml(){
        mailService.sendHtmlMail("lilimul@yeah.net","测试HTML文件","<h1>这个应该解析成HTML标签</h1>");
    }
}
