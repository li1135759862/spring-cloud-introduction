package cn.lxl.webservice.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
public class WebUtilConfiguration implements ApplicationContextAware {


    private static Set<String> webUrl = new HashSet<>();

    @Getter
    private Map<String, HandlerMethod> handlerMethods = new HashMap<>();

    static {
        webUrl.add("/testWeb/web");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RequestMappingHandlerMapping bean = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
        handlerMethods.forEach((k, v) -> {
            PatternsRequestCondition pattern = k.getPatternsCondition();
            BeanWrapper p = new BeanWrapperImpl(pattern);
            Set<String> urls = (Set<String>) p.getPropertyValue("patterns");
            webUrl.forEach(u->{
                if(!CollectionUtils.isEmpty(urls)&&urls.contains(u)){
                    this.handlerMethods.put(u, v);
                }
            });

        });
    }
}
