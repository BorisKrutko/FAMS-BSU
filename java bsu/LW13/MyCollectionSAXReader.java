package LW13;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyCollectionSAXReader {
    public static MyCollection readFromXMLUsingSAX(File file) {
        MyCollection collection = new MyCollection();
        
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                Student currentStudent;
                StringBuilder data;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equals("Student")) {
                        this.currentStudent = new Student();
                    }
                    data = new StringBuilder();
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    data.append(new String(ch, start, length));
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (currentStudent != null) {
                        switch (qName) { // Сравнение без изменения регистра
                            case "Surname":
                                currentStudent.setSurname(data.toString());
                                break;
                            case "GroupNumber":
                                currentStudent.setGroupNumber(Integer.parseInt(data.toString()));
                                break;
                            case "CourceNumber":
                                currentStudent.setCourseNumber(Integer.parseInt(data.toString()));
                                break;
                            case "Student":
                                // Завершение элемента Student — добавляем в коллекцию
                                collection.add(currentStudent);
                                currentStudent = null; // Сбрасываем текущего студента
                                break;
                        }
                    }
                }
            };

            saxParser.parse(file, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return collection;
    }
}