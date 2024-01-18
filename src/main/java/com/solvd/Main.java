package com.solvd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Starting...");
////        MyBatis Test
//        ClientServiceImpl clientService = new ClientServiceImpl("RELATIONAL", "MYBATIS");
//        clientService.getAll().forEach(LOGGER::info);
//
////        Parsers
//        File xmlFile = new File("src/main/resources/xml/event.xml");
//        File jsonFile = new File("src/main/resources/json/event.json");
//
////        DOM
//        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//        try {
//            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//            Document document = documentBuilder.parse(xmlFile);
//            document.getChildNodes();
//        } catch (ParserConfigurationException | SAXException | IOException e) {
//            throw new RuntimeException(e);
//        }
//
////        SAX
//        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
//        try {
//            SAXParser saxParser = saxParserFactory.newSAXParser();
//            saxParser.parse(xmlFile, new SaxHandler());
//        } catch (SAXException | ParserConfigurationException | IOException e) {
//            throw new RuntimeException(e);
//        }
//
////        StAX
//        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
//        try (FileInputStream fileInputStream = new FileInputStream(xmlFile)) {
//            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(fileInputStream);
//            while (xmlEventReader.hasNext()) {
//                    XMLEvent xmlEvent = xmlEventReader.nextEvent();
//            }
//        } catch (IOException | XMLStreamException e) {
//            throw new RuntimeException(e);
//        }
//
////        JAXB
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(Event.class);
//            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//            Event event = (Event) unmarshaller.unmarshal(xmlFile);
//        } catch (JAXBException e) {
//            throw new RuntimeException(e);
//        }
//
////        Jackson
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            Event event = objectMapper.readValue(jsonFile, Event.class);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
////        Builder pattern
//        Client c = Client.builder()
//                .cuit("20999887775")
//                .businessName("Husqvarna")
//                .address("Juan B. Justo 1112, C1425 CABA")
//                .email("husqvarna@gmail.com")
//                .phone("+549118293441")
//                .build();
//
////        Listener pattern
//        ListenersHolder.subscribe(new EmployeeListenerImpl());
    }
}