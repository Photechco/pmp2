package PMP2_1_1;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Messungen {
    public static void main(String[] args) {

        List<Sensor> messungen = new ArrayList<>();

        Sensor messnung0 = new Sensor(0, ((double)(((int)(Math.random()*1000)))/10));
        Sensor messnung1 = new Sensor(1, ((double)(((int)(Math.random()*1000)))/10));

        messungen.add(messnung0);
        messungen.add(messnung1);

        System.out.println(messnung0.toString());
        System.out.println(messnung1.toString());

        try {
            toXML(messungen);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //public static void fromXML ();


    public static void toXML (List<Sensor> messungen) throws Exception {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        Element sensorElement = doc.createElement("Sensor");

        Iterator<Sensor> it_mess = messungen.iterator();

        int i = 0;

        while (it_mess.hasNext()) {
            Sensor temp = it_mess.next();
            Node messungenElement = doc.createElement("Messungen");
            ((Element)messungenElement).setAttribute("wert", "" + temp.getMessung());
            ((Element)messungenElement).setAttribute("zeit", temp.getZeitstempel());
            sensorElement.appendChild(messungenElement);
        }

        doc.appendChild(sensorElement);
        doc.normalizeDocument();

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","3");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("out.xml"));
        transformer.transform(source, result);
    }
}
