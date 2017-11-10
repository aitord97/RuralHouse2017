package businessLogic;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;

public class FacadeFactory {
	
	public ApplicationFacadeInterfaceWS getFacade(String facadeType) {
		
		if(facadeType.compareTo("Local")==0) 
		{
			return new FacadeImplementationWS();
		}
		if(facadeType.compareTo("Global")==0) 
		{
			ConfigXML c=ConfigXML.getInstance();
			
			String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";
			URL url=null;
			try {
				url = new URL(serviceName);
			} catch (MalformedURLException e) {
				
				e.printStackTrace();
			}

	        QName qname = new QName("http://businessLogic/", "FacadeImplementationWSService");
	 
	        Service service = Service.create(url, qname);
	 
	        return service.getPort(ApplicationFacadeInterfaceWS.class);
		}
		return null;
		
	}

}
