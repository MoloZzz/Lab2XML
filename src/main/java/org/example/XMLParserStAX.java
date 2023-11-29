package org.example;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;

public class XMLParserStAX {

    public static void parseXML(String filePath){

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(filePath));

            String id = "";
            String name = "";
            String origin = "";
            double price = 0;
            boolean critical = false;

            while (reader.hasNext()) {
                int event = reader.next();
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        String elementName = reader.getLocalName();
                        switch (elementName) {
                            case "Device":
                                id = reader.getAttributeValue(null, "id");
                                break;
                            case "Name":
                                name = reader.getElementText();
                                break;
                            case "Origin":
                                origin = reader.getElementText();
                                break;
                            case "Price":
                                price = Double.parseDouble(reader.getElementText());
                                break;
                            case "Critical":
                                critical = Boolean.parseBoolean(reader.getElementText());
                                break;
                            case "Type":
                                // Обробляємо всі типи для поточного пристрою
                                String type = reader.getElementText();
                                System.out.println("Device Type: " + type);
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        // Виведення результатів при закінченні елементу "Device"
                        if ("Device".equals(reader.getLocalName())) {
                            System.out.println("Device ID: " + id);
                            System.out.println("Device Name: " + name);
                            System.out.println("Device Origin: " + origin);
                            System.out.println("Device Price: " + price);
                            System.out.println("Is Critical: " + critical);
                            System.out.println();
                        }
                        break;
                }
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
