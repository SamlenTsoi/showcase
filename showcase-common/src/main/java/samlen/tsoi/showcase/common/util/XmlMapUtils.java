package samlen.tsoi.showcase.common.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Xml与Map互转工具
 * Created by samlen_tsoi on 2017/9/27.
 */
public class XmlMapUtils {

    /**
     * xml转map
     * @param xml
     * @return
     */
    public static Map<String, Object> xml2map(String xml) {
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (doc == null) {
            return map;
        }
        Element rootElement = doc.getRootElement();
        element2map(rootElement,map);
        return map;
    }

    public static Map element2map(Element elmt, Map<String, Object> map) {
        String name = elmt.getName();
        if (elmt.isTextOnly()) {
            map.put(name, elmt.getText());
        } else {
            Map<String, Object> mapSub = new HashMap<String, Object>();
            List<Element> elements = (List<Element>) elmt.elements();
            for (Element elmtSub : elements) {
                element2map(elmtSub, mapSub);
            }
            Object first = map.get(name);
            if (null == first) {
                map.put(name, mapSub);
            } else {
                if (first instanceof List<?>) {
                    ((List) first).add(mapSub);
                } else {
                    List<Object> listSub = new ArrayList<Object>();
                    listSub.add(first);
                    listSub.add(mapSub);
                    map.put(name, listSub);
                }
            }
        }
        return map;
    }
}
