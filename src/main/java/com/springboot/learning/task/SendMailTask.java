package com.springboot.learning.task;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * @author caiqibin
 * @date 2017年7月31日
 * @introduce:邮件发送任务
 */
@Service
public class SendMailTask {

	private static final Log LOGGER = LogFactory.getLog(SendMailTask.class);

	// 由于Spring Boot的starter模块提供了自动化配置，所以在引入了spring-boot-starter-mail依赖之后，
	// 会根据配置文件中的内容去创建JavaMailSender实例，这里可以直接在需要使用的地方直接@Autowired来引入邮件发送对象。
	@Autowired
	private JavaMailSender sender;

	@Value("${spring.mail.username}")
	private String from;

	/**
	 * @introduce: 简单邮件发送
	 * @param title
	 * @param text
	 * @param toArray 发送人可多个
	 * @return void
	 */
	public void sendSimpleMail(String title, String text, String... toArray) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		// 指定收件人
		message.setTo(toArray);
		/*//指定抄送人
		message.setCc("");
		//指定密送人
		message.setBcc("");*/
		message.setSubject(title);
		message.setText(text);
		sender.send(message);
	}

	/**
	 * @introduce:附件发送
	 * @param title
	 * @param text
	 * @param filePath 文件的绝对路径
	 * @param toArray
	 * @return void
	 */
	public void sendAdjunctMail(String title, String text, String filePath, String... toArray) {
		MimeMessage mimeMessage = sender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(from);
			helper.setTo(toArray);
			helper.setSubject(title);
			helper.setText(text);

			// 加载附件
			FileSystemResource resource = new FileSystemResource(new File(filePath));
			helper.addAttachment("附件1", resource);

			sender.send(mimeMessage);
		} catch (MessagingException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			LOGGER.error(sw.toString());
		}
	}

	/**
	 * @introduce:发送携带静态资源邮件
	 * @param title
	 * @param text
	 * @param filePath 文件的绝对路径
	 * @param toArray
	 * @return void
	 */
	public void sendStaticResourceMail(String title, String text, String filePath, String... toArray) {
		MimeMessage mimeMessage = sender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(from);
			helper.setTo(toArray);
			helper.setSubject(title);
			helper.setText(text + "<html><body><img src=\"cid:fileName\" ></body></html>", true);

			FileSystemResource file = new FileSystemResource(new File(filePath));
			// 需要注意的是addInline函数中资源名称fileName需要与正文中cid:fileName对应起来
			helper.addInline("fileName", file);

			sender.send(mimeMessage);
		} catch (MessagingException e) {
			LOGGER.error("==== error  ===", e);
		}
	}

}
