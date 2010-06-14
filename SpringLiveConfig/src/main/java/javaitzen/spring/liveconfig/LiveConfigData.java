package javaitzen.spring.liveconfig;

import java.lang.reflect.Field;

/**
 * The Class LiveConfigData.
 */
public class LiveConfigData {
    
    private String file;
    private Field field;
    private Object bean;

    /**
     * Gets the file.
     * 
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * Sets the file.
     * 
     * @param file
     *            the new file
     */
    public void setFile(final String file) {
        this.file = file;
    }

    /**
     * Gets the field.
     * 
     * @return the field
     */
    public Field getField() {
        return field;
    }

    /**
     * Sets the field.
     * 
     * @param field
     *            the new field
     */
    public void setField(final Field field) {
        this.field = field;
    }

    /**
     * Gets the bean.
     * 
     * @return the bean
     */
    public Object getBean() {
        return bean;
    }

    /**
     * Sets the bean.
     * 
     * @param bean
     *            the new bean
     */
    public void setBean(final Object bean) {
        this.bean = bean;
    }
    
}
