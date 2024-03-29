package services;

import models.soap.EnvelopeResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class JaxbWorker {
    public String soapRequestBody(Object bodyXml, Class responseType) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(responseType);
        StringWriter writer = new StringWriter();

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
        marshaller.marshal(bodyXml, writer);
        return writer.toString();
    }

    public String unmarshallerEnvelopeBody(String xmlBody) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(EnvelopeResponse.class);
        StringReader reader = new StringReader(xmlBody);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        EnvelopeResponse unmarshallerEnvelope = (EnvelopeResponse) unmarshaller.unmarshal(reader);
        return unmarshallerEnvelope.getResponseBodyXml().getCustomerId();
    }
}
