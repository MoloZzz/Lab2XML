package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLparserDom {
    public static void parseXML(String filePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(filePath);

            Element root = document.getDocumentElement();
            NodeList deviceList = root.getElementsByTagName("Device");

            for (int i = 0; i < deviceList.getLength(); i++) {
                Element deviceElement = (Element) deviceList.item(i);

                String id = deviceElement.getAttribute("id");
                String name = getTextContent(deviceElement, "Name");
                String origin = getTextContent(deviceElement, "Origin");
                double price = Double.parseDouble(getTextContent(deviceElement, "Price"));
                boolean critical = Boolean.parseBoolean(getTextContent(deviceElement, "Critical"));

                NodeList typesList = deviceElement.getElementsByTagName("Type");
                for (int j = 0; j < typesList.getLength(); j++) {
                    Element typeElement = (Element) typesList.item(j);
                    String type = typeElement.getTextContent();
                }

                System.out.println("Device ID: " + id);
                System.out.println("Device Name: " + name);
                System.out.println("Device Origin: " + origin);
                System.out.println("Device Price: " + price);
                System.out.println("Is Critical: " + critical);

                for (int j = 0; j < typesList.getLength(); j++) {
                    Element typeElement = (Element) typesList.item(j);
                    String type = typeElement.getTextContent();
                    System.out.println("Device Type: " + type);
                }

                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getTextContent(Element parent, String childName) {
        NodeList nodeList = parent.getElementsByTagName(childName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        } else {
            return "";
        }
    }
}
