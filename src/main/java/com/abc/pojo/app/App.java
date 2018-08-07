package com.abc.pojo.app;

import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.abc.pojo.*;

public class App {

	public static void main(String[] args) throws DatatypeConfigurationException, JAXBException {
		unmarshal();
		marshal();
	}
	
	private static void unmarshal() throws DatatypeConfigurationException, JAXBException {
		File file = new File("CustomerFile.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		Customer cus = (Customer) jaxbUnmarshaller.unmarshal(file);
		System.out.println(cus);
		System.out.println(cus.getName());
		System.out.println(cus.getAddress());
		System.out.println(cus.getPayment());
	}
	
	private static void marshal() throws DatatypeConfigurationException {
		try {

			File file = new File("CustomerFile.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			Customer cus = createCustomer();
			jaxbMarshaller.marshal(cus, file);
			// jaxbMarshaller.marshal(book, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	private static Customer createCustomer() throws DatatypeConfigurationException {
		
		Customer c= new Customer();
		c.setName("Akhil");
		c.setCustomerId(1);
		c.setAnnualSalary(78324);
		GregorianCalendar c1 = new GregorianCalendar();
		c1.set(1995, 05, 29);
		XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c1);
		c.setDateOfBirth(date2);
		
		Address address1 = new Address();
		address1.setStreetName("2527 Louise Street");
		address1.setCity("Denton");
		address1.setState("TX");
		address1.setPinCode("76201");
		
		Address address2= new Address();	
		address2.setStreetName("2126 Stella Street");
		address2.setCity("Denton");
		address2.setState("TX");
		address2.setPinCode("76201");

		c.getAddress().add(address1);
		c.getAddress().add(address2);
		
		PaymentMethod pm1= new PaymentMethod();
		pm1.setCard("CREDIT CARD");
		pm1.setCardName("Master");
		pm1.setCardNumber(48723635);
		GregorianCalendar c2 = new GregorianCalendar();
		c2.set(2016, 05, 29);
		XMLGregorianCalendar date3 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c2);
		pm1.setDateFrom(date3);

		GregorianCalendar c3 = new GregorianCalendar();
		c3.set(2012, 05, 29);
		XMLGregorianCalendar date4 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c3);
		pm1.setDateTo(date4);
		
		c.getPayment().add(pm1);
		
		return c;

	}

}
