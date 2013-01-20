package br.com.alexandre.glassfish.web.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import br.com.alexandre.glassfish.interfaces.ClienteRequestType;
import br.com.alexandre.glassfish.interfaces.ClienteResponseType;
import br.com.alexandre.glassfish.interfaces.ObjectFactory;
import br.com.alexandre.glassfish.web.spring.RequestBean;

@Component("RequestServlet")
public class RequestServlet implements HttpRequestHandler {

	@Autowired
	private RequestBean requestBean;
	
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String id = request.getParameter("id");

		final ObjectFactory objectFactory = new ObjectFactory();
		final ClienteRequestType clienteRequestType = objectFactory.createClienteRequestType();
		clienteRequestType.setId(id);
		
		final ClienteResponseType responseType = requestBean.request(clienteRequestType);
		
		if (responseType != null) {
			final String nome = (responseType.getNome() != null)? responseType.getNome(): "---";
			final String sexo = (responseType.getSexo() != null)? responseType.getSexo(): "---";
			final String dataNasc =  (responseType.getDataNasc() != null)? new SimpleDateFormat("dd/MM/yyyy").format(responseType.getDataNasc().getTime()): "---";
			
			response.getWriter().write("Nome: " + nome + "\n");
			response.getWriter().write("Sexo: " + sexo + "\n");
			response.getWriter().write("DataNasc: " + dataNasc + "\n");
		}
		
	}

	
}
