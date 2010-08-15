package javaitzen.spring.jms.message;

import java.io.Serializable;

public class Content<T> implements Serializable {

    private static final long serialVersionUID = 3008805001429324011L;
   
    private T content;

    public Content(){ 
    }
    
    public Content(final T content) {
        super();
        this.content = content;
    }
    
    public String getContentString() {
        return content.toString();
    }

    public T getContentValue() {
        return content;
    }
    
    public void setContentValue(T content) {
        this.content = content;
    }

}
