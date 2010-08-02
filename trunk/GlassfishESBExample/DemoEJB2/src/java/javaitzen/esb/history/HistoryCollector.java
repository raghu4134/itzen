/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaitzen.esb.history;

import java.util.ArrayList;
import java.util.List;
import javaitzen.esb.objects.ESBMessage;
import javaitzen.esb.objects.Header;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ejb.Stateless;

@WebService()
@Stateless()
public class HistoryCollector {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "gatherHistoryData")
    public ESBMessage gatherHistoryData(@WebParam(name = "message")
    final ESBMessage message) {
        Header header = message.getHeader();
        List<String> history = new ArrayList<String>();
        history.add("Step1");
        history.add("Step2");
        history.add("Step3");
        history.add("Step4");
        header.setHistory(history);
        return message;
    }

}
