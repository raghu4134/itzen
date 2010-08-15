package javaitzen.spring.jms.message;

import java.io.Serializable;

/**
 * The Class ExampleMessage.
 */
public class ExampleMessage implements Serializable {
    
    private static final long serialVersionUID = 4759716828378650012L;

    private Header header;
    private Content< ? > content;
    
    public final Header getHeader() {
        return header;
    }
    
    public final void setHeader(Header header) {
        this.header = header;
    }
    
    @SuppressWarnings("unchecked")
    public final Content getContent() {
        return content;
    }
    

    @SuppressWarnings("unchecked")
    public final void setContent(Content content) {
        this.content = content;
    }
    

}
