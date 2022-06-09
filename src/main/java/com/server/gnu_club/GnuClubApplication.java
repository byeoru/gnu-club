package com.server.gnu_club;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class GnuClubApplication {

    public static void main(String[] args) {
        SpringApplication.run(GnuClubApplication.class, args);
    }

    @PostConstruct
    public void started() {
        // timezone 셋팅
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }
}
