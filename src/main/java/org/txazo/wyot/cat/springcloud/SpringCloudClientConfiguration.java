package org.txazo.wyot.cat.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudClientConfiguration {

    @Value("${feign.hystrix.enabled:false}")
    private boolean enableHystrix;

    @Bean
    public SpringCloudClientInterceptor requestInterceptor() {
        return new SpringCloudClientInterceptor(enableHystrix);
    }

}
