package cn.yangsansui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.yangsansui.mapper")
@SpringBootApplication
public class BillManagerAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillManagerAppApplication.class, args);
    }

}
