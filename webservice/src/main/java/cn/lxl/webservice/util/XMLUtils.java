package cn.lxl.webservice.util;


import cn.lxl.webservice.pojo.BodyPojo;
import cn.lxl.webservice.pojo.Member;
import cn.lxl.webservice.pojo.WebServicePojo;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class XMLUtils{

    /**
     * add by scy 通过JAXB生成XML字符串
     * @param obj
     * @return
     */
    public static String obj2XML(Object obj){
        StringWriter sw = new StringWriter();
        String xml = null;
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8");

            // 将对象转换成输出流形式的xml
            marshaller.marshal(obj, sw);
            // 去掉standalone属性
            xml = sw.toString().replace("standalone=\"yes\"", "");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xml;
    }

    /**
     * add by scy 通过JAXB将XML转化为Object
     * @param xml
     * @param clazz
     * @return
     */
    public static <T> Object xml2Obj(String xml,Class<T> clazz){
        Object obj = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller um = jaxbContext.createUnmarshaller();
            StringReader sr = new StringReader(xml);
            obj = clazz.newInstance();
            obj = um.unmarshal(sr);
        } catch (JAXBException | InstantiationException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return obj;
    }

    public static void main(String[] args) {
        BodyPojo bodyPojo = new BodyPojo();
        WebServicePojo web = new WebServicePojo();
        web.setMsg("lxl");
        bodyPojo.setWebServicePojo(web);
        System.out.println(obj2XML(bodyPojo));
    }
}
