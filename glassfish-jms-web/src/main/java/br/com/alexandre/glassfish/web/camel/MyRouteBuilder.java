package br.com.alexandre.glassfish.web.camel;

import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		from("direct:request")
		.marshal("jaxbFormat")
		.to("jms:queue:RequestQueue?replyTo=queue:ResponseQueue")
		.unmarshal("jaxbFormat");		
	}
	
}
