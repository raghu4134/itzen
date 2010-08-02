
package javaitzen.esb.data;

import java.util.Hashtable;
import java.util.Map;
import javaitzen.esb.objects.Body;
import javaitzen.esb.objects.ESBMessage;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService()
@Stateless()
public class DataCollector {


    @WebMethod(operationName = "gatherHistoryData")
    public ESBMessage gatherAppData(@WebParam(name = "message")
    final ESBMessage message) {
        Body body = message.getBody();
        Map<String, String> data = new Hashtable<String, String>();
        data.put("Data1", "X");
        data.put("Data2", "XX");
        data.put("Data3", "XXX");
        body.setSomeRandomAppData(data);

        return message;
    }

}
