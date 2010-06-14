package javaitzen.spring.interceptors;

import java.io.Serializable;

/**
 * The Class MyLittleTestObject.
 */
public class MyLittleTestObject implements Serializable {

    private static final long serialVersionUID = 6754090342991937310L;
    private String meh;

    /**
     * The business method.
     * 
     * @param input the input
     * @return the string
     */
    public String theBusinessMethod(final String input) {

	//some processing
	int i = 0;
	while (i < 1000000) {
	    String wasteTime = i + i + "count + count";
	    i++;
	}

	return "Returning from business method with value: " + input + " " + i;

    }

    /**
     * Generated Getter.
     * 
     * @return the meh
     */
    public String getMeh() {
	return meh;
    }

    /**
     * Generated Setter.
     * 
     * @param meh the meh to set
     */
    public void setMeh(final String meh) {
	this.meh = meh;
    }

    /** (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((meh == null) ? 0 : meh.hashCode());
        return result;
    }

    /** (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MyLittleTestObject other = (MyLittleTestObject) obj;
        if (meh == null) {
            if (other.meh != null)
                return false;
        } else if (!meh.equals(other.meh))
            return false;
        return true;
    }

}
