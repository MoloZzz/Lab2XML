package org.example;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
public class XMLParserSAX {

    public static List<Device> parseXML(String filePath){
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();

            SAXParser saxParser = factory.newSAXParser();

            MyHandler handler = new MyHandler();

            saxParser.parse(filePath, handler);

            return handler.getDevices();


        }catch (Exception e){
            e.printStackTrace();
            return Collections.emptyList();
        }


    }

    private static class MyHandler extends DefaultHandler {
        private List<Device> devices;
        private Device currentDevice;
        private StringBuilder currentText;

        public List<Device> getDevices() {
            return devices;
        }
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            currentText = new StringBuilder();

            if ("Device".equals(qName)) {
                currentDevice = new Device();
                currentDevice.setId(attributes.getValue("id"));
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            currentText.append(new String(ch, start, length).trim());
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {

            String text = currentText.toString().trim();

            switch (qName) {
                case "Name":
                    currentDevice.setName(text);
                    break;
                case "Origin":
                    currentDevice.setOrigin(text);
                    break;
                case "Price":
                    currentDevice.setPrice(Double.parseDouble(text));
                    break;
                case "Type":
                    currentDevice.getTypes().add(text);
                    break;
                case "Critical":
                    currentDevice.setCritical(Boolean.parseBoolean(text));
                    break;
                case "Device":
                    devices.add(currentDevice);
                    break;
            }
        }

        @Override
        public void startDocument() throws SAXException {
            devices = new ArrayList<>();
        }

        @Override
        public void endDocument() throws SAXException {
//            for (Device device : devices) {
//                System.out.println(device);
//            }
        }

    }
}


