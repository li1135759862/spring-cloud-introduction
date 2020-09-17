package cn.lxl.common.api.config;

import cn.lxl.common.api.user.UserApi;
import com.alibaba.fastjson.JSON;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author xinleili
 */
@Slf4j
@AllArgsConstructor
public class FeignFallbackInterceptor<T> implements MethodInterceptor {

    private final Class<T> targetType;

    private final String targetName;

    private final Throwable throwable;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        String errorMessage = throwable.getMessage();
        log.error(throwable.getMessage(),throwable);
        if (!(throwable instanceof FeignException)) {

            return null;
        }
        FeignException exception = (FeignException) throwable;
        return JSON.toJSON(exception.content());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FeignFallbackInterceptor<?> that = (FeignFallbackInterceptor<?>) o;
        return targetType.equals(that.targetType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetType);
    }
}
