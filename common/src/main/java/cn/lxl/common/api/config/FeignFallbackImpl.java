package cn.lxl.common.api.config;

import cn.lxl.common.api.user.UserApi;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xinleili
 */
@Component
@Slf4j
public class FeignFallbackImpl implements FallbackFactory<UserApi>{


    @Override
    public UserApi create(Throwable cause) {
        log.error(cause.getMessage(),cause);
        return new UserApi() {
            @Override
            public String getById(String id) {
                return cause.getMessage();
            }

            @Override
            public String list() {
                return cause.getMessage();
            }

            @Override
            public String update(String id) {
                return cause.getMessage();
            }
        };
    }
}
