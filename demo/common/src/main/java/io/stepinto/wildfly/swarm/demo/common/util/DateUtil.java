package io.stepinto.wildfly.swarm.demo.common.util;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * Kisegítő osztály.
 */
public final class DateUtil {

    private DateUtil(){}

    public static XMLGregorianCalendar getDate(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        XMLGregorianCalendar xmlGregorianCalendar;
        try {
            xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        } catch (DatatypeConfigurationException e) {
            return null;
        }
        return xmlGregorianCalendar;
    }

    public static Date getGregorianDate(XMLGregorianCalendar date) {
        if (Objects.isNull(date)) {
            return null;
        }
        return date.toGregorianCalendar().getTime();
    }
}
