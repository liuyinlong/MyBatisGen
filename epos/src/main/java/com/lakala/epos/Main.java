/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos;

import com.lakala.epos.util.LKLConfig;
import com.lakala.epos.util.SpringUtils;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
public class Main {
	public static final URI BASE_URI = getBaseURI();

	private static URI getBaseURI() {
		return UriBuilder.fromUri(LKLConfig.getValue("jersey.url"))
				.port(Integer.valueOf(LKLConfig.getValue("jersey.port"))
						.intValue()).build(new Object[0]);
	}

	protected static HttpServer startServer() throws IOException {
		System.out.println("Starting grizzly...");
		ResourceConfig rc = new PackagesResourceConfig(new String[] { "com.lakala.epos.resource" });
		return GrizzlyServerFactory.createHttpServer(BASE_URI, rc);
	}

	/**
	 * TODO 方法描述
	 * 
	 * @param
	 * @param
	 * @return
	 * @throws
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringUtils.start();

		HttpServer httpServer = startServer();
		System.out.println(String.format("Jersey app started with WADL available at %sapplication.wadl\nHit enter to stop it...",
								new Object[] { BASE_URI, BASE_URI }));

		System.in.read();
		httpServer.stop();
	}

}
