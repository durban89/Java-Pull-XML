package org.pull.parser;

import org.pull.domain.Person;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by durban126 on 16/10/7.
 */
public class PullXMLTools {

    public static List<Person> parseXML(InputStream inputStream, String encode) throws XmlPullParserException, IOException {
        List<Person> list = null;

        Person person = null;

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance(System.getProperty(XmlPullParserFactory.PROPERTY_NAME), Thread.currentThread().getContextClassLoader().getClass());
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(inputStream, encode);

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    list = new ArrayList<Person>();
                    break;
                case XmlPullParser.START_TAG:
                    if ("person".equals(parser.getName())) {
                        person = new Person();
                        int id = Integer.parseInt(parser.getAttributeValue(0));
                        person.setId(id);
                    } else if ("name".equals(parser.getName())) {
                        String name = parser.nextText();
                        person.setName(name);
                    } else if ("age".equals(parser.getName())) {
                        int age = Integer.parseInt(parser.nextText());
                        person.setAge(age);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("person".equals(parser.getName())) {
                        list.add(person);
                        person = null;
                    }
                    break;
            }
            eventType = parser.next();
        }
        return list;
    }
}
