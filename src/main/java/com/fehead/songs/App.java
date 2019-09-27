package com.fehead.songs;

import com.fehead.songs.dao.UserDOMapper;
import com.fehead.songs.dataobject.UserDO;
import org.joda.time.DateTime;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;

/**
 * Hello world!
 *
 */
@RestController
@SpringBootApplication(scanBasePackages = {"com.fehead.songs"})
@MapperScan("com.fehead.songs.dao")
@EnableCaching
public class App 
{


    public static void main( String[] args )
    {
        System.out.println(new Date());
        DateTime dateTime=new DateTime(new Date());
        System.out.println(dateTime);
        System.out.println( "Hello World!" );
        SpringApplication.run(App.class,args);
    }
}
