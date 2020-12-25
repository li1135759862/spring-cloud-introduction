package cn.lxl.webservice.web;

import cn.lxl.webservice.pojo.EnvelopePojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;

/**
 * @author xinleili
 */
@RestController
@RequestMapping("/testWeb")
@Slf4j
public class TestWeb implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private WebUtilConfiguration configuration;

    @PostMapping("/web")
    public String web(@RequestBody String mgs,HttpServletResponse response){
        try {
            JAXBContext context = JAXBContext.newInstance(EnvelopePojo.class);
            Unmarshaller unMarshaller = context.createUnmarshaller();
            EnvelopePojo pojo = (EnvelopePojo) unMarshaller.unmarshal(new StringReader(mgs));
            System.out.println(pojo.toString());
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }


        response.setContentType("text/xml;charset=utf-8");
        try {
            response.getWriter().print(mgs);
            response.getWriter().flush();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }



    @PostMapping("/wsdl")
    public String wsdl(@RequestHeader String mapping ,@RequestBody String mgs){
        return getAllRequestMapping(mapping,mgs);
    }

    public String  getAllRequestMapping(String mapping,String mgs){
        HandlerMethod handlerMethod = configuration.getHandlerMethods().get(mapping);
        if (handlerMethod!=null){
            Object o = this.applicationContext.getBean(handlerMethod.getBeanType());
            try {
                return (String) handlerMethod.getMethod().invoke(o, mgs);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
