package cn.ekgc.esp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <b>ESP项目启动器</b>
 * @author wyh
 * @version 1.0.0 2020-08-19
 * @since 1.0.0
 */
@MapperScan("cn.ekgc.esp.dao")
@SpringBootApplication
public class EspStarter {
	public static void main(String[] args) {
		SpringApplication.run(EspStarter.class, args);
	}
}
