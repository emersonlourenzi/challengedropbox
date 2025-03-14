package br.com.challengedropbox.commons.config;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FTPConfig {

    @Bean
    public FTPClient ftpClient() {
        return new FTPClient();
    }

}
