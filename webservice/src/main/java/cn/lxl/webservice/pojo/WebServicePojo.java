package cn.lxl.webservice.pojo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author xinleili
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Slf4j
@Data
public class WebServicePojo {

    @XmlElement(name = "msg")
    private String msg;
}
