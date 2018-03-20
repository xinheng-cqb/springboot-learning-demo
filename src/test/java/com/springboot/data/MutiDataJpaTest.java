package com.springboot.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.learning.Application;
import com.springboot.learning.entity.primary.PrimaryDataJpaEntity;
import com.springboot.learning.entity.second.SecondDataJpaEntity;
import com.springboot.learning.repository.primary.PrimaryDataJpaRepository;
import com.springboot.learning.repository.second.SecondDataJpaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MutiDataJpaTest {
	@Autowired
	private PrimaryDataJpaRepository primaryDataJpaRepository;
	@Autowired
	private SecondDataJpaRepository secondDataJpaRepository;

	// 先清空，避免原先数据对测试的影响
	@Before
	public void setUp() {
		primaryDataJpaRepository.deleteAll();
		secondDataJpaRepository.deleteAll();
	}

	@Test
	public void test() throws Exception {
		primaryDataJpaRepository.save(new PrimaryDataJpaEntity("aaa", 10L));
		primaryDataJpaRepository.save(new PrimaryDataJpaEntity("bbb", 20L));
		primaryDataJpaRepository.save(new PrimaryDataJpaEntity("ccc", 30L));
		primaryDataJpaRepository.save(new PrimaryDataJpaEntity("ddd", 40L));
		Assert.assertEquals(4, primaryDataJpaRepository.findAll().size());
		secondDataJpaRepository.save(new SecondDataJpaEntity("o1", 1L));
		secondDataJpaRepository.save(new SecondDataJpaEntity("o2", 2L));
		secondDataJpaRepository.save(new SecondDataJpaEntity("o3", 3L));
		Assert.assertEquals(3, secondDataJpaRepository.findAll().size());
	}
}
