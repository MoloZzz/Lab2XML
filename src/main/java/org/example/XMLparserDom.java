package org.example;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

public class XMLparserDom {

    public static List<Device> parseXML(String filePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(filePath);

            Element root = document.getDocumentElement();
            NodeList deviceList = root.getElementsByTagName("Device");

            List<Device> devices = new ArrayList<>();

            for (int i = 0; i < deviceList.getLength(); i++) {
                Element deviceElement = (Element) deviceList.item(i);
                Device device = handleDeviceElement(deviceElement);
                devices.add(device);
            }

            return devices;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static Device handleDeviceElement(Element deviceElement) {
        String id = deviceElement.getAttribute("id");
        String name = getTextContent(deviceElement, "Name");
        String origin = getTextContent(deviceElement, "Origin");
        double price = Double.parseDouble(getTextContent(deviceElement, "Price"));
        boolean critical = Boolean.parseBoolean(getTextContent(deviceElement, "Critical"));

        NodeList typesList = deviceElement.getElementsByTagName("Type");
        List<String> types = new ArrayList<>();
        for (int j = 0; j < typesList.getLength(); j++) {
            Element typeElement = (Element) typesList.item(j);
            String type = typeElement.getTextContent();
            types.add(type);
        }

        return new Device(id, name, origin, price, types, critical);
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

