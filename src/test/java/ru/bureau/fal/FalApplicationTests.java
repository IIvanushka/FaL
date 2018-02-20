package ru.bureau.fal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "classpath:initDB.sql")
public class FalApplicationTests {

	@Test
	public void contextLoads() {
	}

}
