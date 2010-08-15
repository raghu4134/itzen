package javaitzen.spring.jms.message;

import java.io.Serializable;

public class Header implements Serializable {

    private static final long serialVersionUID = 1201985581526142757L;
    private String someHeader;

    public Header(){    }
    
    public Header(final String someHeader) {
        super();
        this.someHeader = someHeader;
    }

    public final String getSomeHeader() {
        return someHeader;
    }

    public final void setSomeHeader(final String someHeader) {
        this.someHeader = someHeader;
    } 
    
}
