package javaitzen.esb.objects;

import java.util.Hashtable;
import java.util.Map;


public class Body {

    private Map<String, String> someRandomAppData = new Hashtable<String, String>();

    public Map<String, String> getSomeRandomAppData() {
        return someRandomAppData;
    }

    public void setSomeRandomAppData(Map<String, String> someRandomAppData) {
        this.someRandomAppData = someRandomAppData;
    }
    
}
