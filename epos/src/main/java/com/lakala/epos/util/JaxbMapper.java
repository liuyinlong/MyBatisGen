/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.namespace.QName;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.util.Assert;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
public class JaxbMapper {
	private static ConcurrentMap<Class, JAXBContext> jaxbContexts = new ConcurrentHashMap();

	public static String toXml(Object root) {
		Class clazz = Reflections.getUserClass(root);
		return toXml(root, clazz, null);
	}

	public static String toXml(Object root, String encoding) {
		Class clazz = Reflections.getUserClass(root);
		return toXml(root, clazz, encoding);
	}

	public static String toXml(Object root, Class clazz, String encoding) {
		StringWriter writer;
		try {
			writer = new StringWriter();
			createMarshaller(clazz, encoding).marshal(root, writer);
			return writer.toString();
		} catch (JAXBException e) {
			throw Exceptions.unchecked(e);
		}
	}

	public static String toXml(Collection<?> root, String rootName, Class clazz) {
		return toXml(root, rootName, clazz, null);
	}

	public static String toXml(Collection<?> root, String rootName,
			Class clazz, String encoding) {
		CollectionWrapper wrapper;
		try {
			wrapper = new CollectionWrapper();
			wrapper.collection = root;

			JAXBElement wrapperElement = new JAXBElement(new QName(rootName),
					CollectionWrapper.class, wrapper);

			StringWriter writer = new StringWriter();
			createMarshaller(clazz, encoding).marshal(wrapperElement, writer);

			return writer.toString();
		} catch (JAXBException e) {
			throw Exceptions.unchecked(e);
		}
	}

	public static <T> T fromXml(String xml, Class<T> clazz) {
		StringReader reader;
		try {
			reader = new StringReader(xml);
			return createUnmarshaller(clazz).unmarshal(reader);
		} catch (JAXBException e) {
			throw Exceptions.unchecked(e);
		}
	}

	public static Marshaller createMarshaller(Class clazz, String encoding) {
		JAXBContext jaxbContext;
		try {
			jaxbContext = getJaxbContext(clazz);

			Marshaller marshaller = jaxbContext.createMarshaller();

			marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);

			if (StringUtils.isNotBlank(encoding)) {
				marshaller.setProperty("jaxb.encoding", encoding);
			}

			return marshaller;
		} catch (JAXBException e) {
			throw Exceptions.unchecked(e);
		}
	}

	public static Unmarshaller createUnmarshaller(Class clazz) {
		JAXBContext jaxbContext;
		try {
			jaxbContext = getJaxbContext(clazz);
			return jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			throw Exceptions.unchecked(e);
		}
	}

	protected static JAXBContext getJaxbContext(Class clazz) {
		Assert.notNull(clazz, "'clazz' must not be null");
		JAXBContext jaxbContext = (JAXBContext) jaxbContexts.get(clazz);
		if (jaxbContext == null)
			try {
				jaxbContext = JAXBContext.newInstance(new Class[] { clazz,
						CollectionWrapper.class });
				jaxbContexts.putIfAbsent(clazz, jaxbContext);
			} catch (JAXBException ex) {
				throw new RuntimeException(
						"Could not instantiate JAXBContext for class [" + clazz
								+ "]: " + ex.getMessage(), ex);
			}

		return jaxbContext;
	}

	public static String formatXml(String text) {
		String res = null;
		XMLWriter writer = null;
		StringWriter out = new StringWriter();
		try {
			Document doc = DocumentHelper.parseText(text);
			OutputFormat formater = OutputFormat.createPrettyPrint();
			formater.setEncoding("UTF-8");
			writer = new XMLWriter(out, formater);
			writer.write(doc);
			writer.flush();
			res = out.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		return res;
	}

	public static class CollectionWrapper {

		@XmlAnyElement
		protected Collection<?> collection;
	}
}