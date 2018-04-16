package samlen.tsoi.showcase.common.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * xml工具类
 *
 * @author samlen_tsoi
 * @date 2017/12/25
 */
public class XmlUtils {

    /**
     * xml转对象
     *
     * @param xml
     * @param load
     * @return
     * @throws JAXBException
     * @throws IOException
     */
    public static Object xmlToBean(String xml, Class<?> load) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(load);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
        return unmarshaller.unmarshal(inputStream);
    }

    /**
     * 对象转xml
     *
     * @param obj
     * @param load
     * @return
     * @throws JAXBException
     */
    public static String beanToXml(Object obj,Class<?> load) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(load);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF8");
        StringWriter writer = new StringWriter();
        marshaller.marshal(obj,writer);
        return writer.toString();
    }
}
