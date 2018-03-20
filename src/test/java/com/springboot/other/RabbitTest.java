package com.springboot.other;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.learning.Application;
import com.springboot.learning.mq.Sender;

/**
 * @author caiqibin
 * @date 2017年8月8日
 * @introduce:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RabbitTest {
	@Autowired
	private Sender sender;

	@Test
	public void hello() throws Exception {
		sender.send();
	}
}
