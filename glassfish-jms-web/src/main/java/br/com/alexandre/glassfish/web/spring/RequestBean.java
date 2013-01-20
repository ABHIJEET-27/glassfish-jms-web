package br.com.alexandre.glassfish.web.spring;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alexandre.glassfish.interfaces.ClienteRequestType;
import br.com.alexandre.glassfish.interfaces.ClienteResponseType;

@Component
public class RequestBean {

	@Autowired
	private ProducerTemplate producerTemplate;

	public ClienteResponseType request(ClienteRequestType request) {	
		return (ClienteResponseType) producerTemplate.requestBody("direct:request", request);
	}


}
