package com.springboot.other;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.learning.Application;
import com.springboot.learning.task.SendMailTask;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SendMailTest {
	@Autowired
	private SendMailTask sender;

	@Test
	public void sendSimpleMail() throws Exception {
		sender.sendSimpleMail("title", "text", "18257344546@139.com");
	}

	@Test
	public void sendAdjunctMail() throws Exception {
		String filePath = "D:\\workspace\\springboot-learning-demo\\target\\classes\\static\\Cartoon.png";
		sender.sendAdjunctMail("附件", "带附件发送", filePath, "18257344546@139.com");
	}

	@Test
	public void sendStaticResourceMail() throws Exception {
		String filePath = "D:\\workspace\\springboot-learning-demo\\target\\classes\\static\\Cartoon.png";
		sender.sendStaticResourceMail("静态资源", "文本带静态资源", filePath, "18257344546@139.com");
	}
}
