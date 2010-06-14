package javaitzen.spring.liveconfig;


/**
 * The Interface ConfigLoader.
 */
public interface ConfigLoader {
  
    /**
     * Adds the config.
     * 
     * @param field
     *            the field
     * @param data
     *            the data
     */
    void addConfig(final String field, final LiveConfigData data);

    /**
     * Load.
     */
    void load();
}
