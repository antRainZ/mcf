package org.antrain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动程序
 * 
 * @author antrain
 */
@EnableFeignClients
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class MCFWepApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(MCFWepApp.class, args);
    }
}
