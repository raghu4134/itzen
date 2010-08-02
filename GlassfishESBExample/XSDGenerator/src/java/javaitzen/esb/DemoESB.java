package javaitzen.esb;

import javaitzen.esb.objects.ESBMessage;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebParam;

@WebService()
@Stateless()
public class DemoESB {

    @WebMethod(operationName = "mainESBEntry")
    public ESBMessage mainESBEntry(@WebParam ESBMessage esbMessage) {
        return esbMessage;
    }

}
