package javaitzen.esb.objects;

public class ESBMessage {
    private Header header;
    private Body body;

    public Body getBody() {
        return body;
    }
    public void setBody(Body body) {
        this.body = body;
    }
    public Header getHeader() {
        return header;
    }
    public void setHeader(Header header) {
        this.header = header;
    }
}
