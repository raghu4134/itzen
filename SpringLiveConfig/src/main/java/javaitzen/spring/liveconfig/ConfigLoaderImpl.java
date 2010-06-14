package javaitzen.spring.liveconfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * The Class ConfigLoaderImpl.
 */
@Component
public class ConfigLoaderImpl implements ConfigLoader {

    private Map<String, LiveConfigData> liveConfigs = new HashMap<String, LiveConfigData>();

    /**
     * Instantiates a new config loader impl.
     */
    public ConfigLoaderImpl() {

    }

    /**
     * Adds the config.
     * 
     * @param field
     *            the field
     * @param data
     *            the data
     */
    public void addConfig(final String field, final LiveConfigData data) {
        liveConfigs.put(field, data);
    }

    /**
     * Load.
     * 
     * @see javaitzen.spring.liveconfig.ConfigLoader#load(java.lang.String)
     */
    @Override
    @Scheduled(fixedDelay = 30000)
    @Async
    public void load() {

        FileInputStream in = null;
        try {
            for (Entry<String, LiveConfigData> liveConfig : liveConfigs.entrySet()) {
                LiveConfigData lcd = liveConfig.getValue();
                Properties props = new Properties();
                in = new FileInputStream(lcd.getFile());
                props.load(in);
                for (Entry<Object, Object> entry : props.entrySet()) {
                    String key = (String) entry.getKey();
                    String value = (String) entry.getValue();
                    LiveConfigData data = liveConfigs.get(key);
                    data.getField().set(data.getBean(), value);
                }
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

}
