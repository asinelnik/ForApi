package services;

import models.soap.BodyXml;
import models.soap.EnvelopeXml;
import models.soap.HeaderXml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class JaxbWorker {
    public String soapRequestBody(String token, Long phone) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(EnvelopeXml.class);

        EnvelopeXml envelopeXml = new EnvelopeXml();
        HeaderXml headerXml = new HeaderXml();
        headerXml.setAuthToken(token);

        BodyXml bodyXml = new BodyXml();
        bodyXml.setPhoneNumber(phone);
        envelopeXml.setHeaderXml(headerXml);
        envelopeXml.setBodyXml(bodyXml);
        StringWriter writer = new StringWriter();

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
        marshaller.marshal(envelopeXml, writer);
        return writer.toString();
    }
}
