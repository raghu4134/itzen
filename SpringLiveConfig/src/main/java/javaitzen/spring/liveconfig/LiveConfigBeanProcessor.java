package javaitzen.spring.liveconfig;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * The Class LiveConfigBeanProcessor.
 */
@Component
public class LiveConfigBeanProcessor implements BeanPostProcessor {
    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private ConfigLoader loader;
    /**
     * Post process before initialization.
     * 
     * @param bean
     *            the bean
     * @param beanName
     *            the bean name
     * @return the object
     * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object,
     *      java.lang.String)
     */
    public final Object postProcessBeforeInitialization(final Object bean, final String beanName) {

        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(LiveConfig.class)) {

                try {
                    field.setAccessible(true);
                    LiveConfig ann = field.getAnnotation(LiveConfig.class);
                    LiveConfigData lcd = new LiveConfigData();
                    lcd.setFile(ann.configLocation());
                    lcd.setBean(bean);
                    lcd.setField(field);
                    loader.addConfig(field.getName(), lcd);
                    loader.load();
                } catch (Exception e) {
                    logger.warn(e);
                }
            }
        }
        return bean;
    }

    /**
     * Post process after initialization.
     * 
     * @param bean
     *            the bean
     * @param beanName
     *            the bean name
     * @return the object
     * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object,
     *      java.lang.String)
     */
    public Object postProcessAfterInitialization(final Object bean, final String beanName) {
        return bean;
    }

}
