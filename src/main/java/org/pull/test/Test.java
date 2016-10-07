package org.pull.test;

import org.pull.domain.Person;
import org.pull.http.HttpUtils;
import org.pull.parser.PullXMLTools;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by durban126 on 16/10/7.
 */
public class Test {

     public static void main(String[] args) throws IOException, XmlPullParserException {
         String path = "http://192.168.31.220:8080/ProductManagement/persons.xml";
         InputStream inputStream = HttpUtils.getXML(path);
         List<Person> list = null;
         list = PullXMLTools.parseXML(inputStream, "utf-8");
         for(Person person:list){
             System.out.println(person.toString());
         }
     }
}
