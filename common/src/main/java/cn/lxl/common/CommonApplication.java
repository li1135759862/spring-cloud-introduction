package cn.lxl.common;

import cn.lxl.common.api.config.FeignFallbackImpl;
import feign.Retryer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xinleili
 */
@Configuration
@EnableFeignClients
public class CommonApplication {

    @Bean
    public FeignFallbackImpl feignFallbackImpl(){
        return new FeignFallbackImpl();
    }


    /**
     * 重试配置
     * */
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100,1000,3);
    }
}
