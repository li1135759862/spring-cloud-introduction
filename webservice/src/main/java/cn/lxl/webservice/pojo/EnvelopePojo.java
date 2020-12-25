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
@XmlRootElement(name = "envelope")
@XmlAccessorType(XmlAccessType.FIELD)
@Slf4j
@Data
public class EnvelopePojo {

    @XmlElement(name = "header")
    private String header;

    @XmlElement(name = "body")
    private BodyPojo body;
}
