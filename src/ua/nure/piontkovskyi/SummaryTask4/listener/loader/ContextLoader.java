package ua.nure.piontkovskyi.SummaryTask4.listener.loader;

import net.sf.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.piontkovskyi.SummaryTask4.annotation.Autowired;
import ua.nure.piontkovskyi.SummaryTask4.annotation.Repository;
import ua.nure.piontkovskyi.SummaryTask4.annotation.Service;
import ua.nure.piontkovskyi.SummaryTask4.db.AnnotationHandler;
import ua.nure.piontkovskyi.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.piontkovskyi.SummaryTask4.db.manager.ConnectionManager;
import ua.nure.piontkovskyi.SummaryTask4.util.FileServiceImpl;
import ua.nure.piontkovskyi.SummaryTask4.util.Locale;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Settings;
import ua.nure.piontkovskyi.SummaryTask4.util.serializer.JSONSerializer;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Loads all beans, services and serializers.
 */
public class ContextLoader extends AbstractContextLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContextLoader.class);
    private ServletContext servletContext;
    private ConnectionHolder connectionHolder;
    private ConnectionManager connectionManager;
    private CacheManager cacheManager;
    private Map<String, Object> beans = new HashMap<>();
    private Map<String, Object> services = new HashMap<>();

    /**
     * Creates new {@code ContextLoader} object.
     *
     * @param holder       connection holder
     * @param manager      connection manager
     * @param context      servlet context
     * @param cacheManager
     */
    public ContextLoader(ConnectionHolder holder, ConnectionManager manager, ServletContext context, CacheManager cacheManager) {
        String[] localesFromContext = (String[]) context.getAttribute(Constants.Attributes.LOCALES);
        String[] defaultLocales = {"ru", "en"};
        String[] localesToSet = localesFromContext == null ? defaultLocales : localesFromContext;

        this.cacheManager = cacheManager;
        this.servletContext = context;
        this.connectionHolder = holder;
        this.connectionManager = manager;

        context.setAttribute(Locale.class.getName(), new Locale("/" + Settings.BUNDLE_PATH, localesToSet));
        context.setAttribute(Constants.Attributes.CONNECTION_MANAGER, manager);
        context.setAttribute(Constants.Service.FILE_PROC_SERVICE, new FileServiceImpl(Settings.getUploadDirectory()));
        context.setAttribute(Constants.Attributes.CACHE, cacheManager.getCache(Constants.Attributes.CACHE));
        context.setAttribute(Constants.Attributes.SERIALIZER, new JSONSerializer());

        cacheManager.addCache(Constants.Attributes.CACHE);
    }

    /**
     * Loads all beans from specified packages.
     *
     * @param packagesNames packages to load beans from
     */
    public void load(String... packagesNames) {
        try {
            loadBeans(packagesNames);
            beans.putAll(services);
            autowireBeans();
            manageServices();
        } catch (ReflectiveOperationException e) {
            LOGGER.error("Cannot load bean(s). Cause", e);
        } catch (IOException e) {
            LOGGER.error("Cannot load resources. Cause", e);
        }
        LOGGER.info("All beans loaded successfully");
    }

    /**
     * Cleans up taken resources.
     *
     * @param context servlet context.
     */
    public void destroy(ServletContext context) {
        connectionManager = (ConnectionManager) context.getAttribute(Constants.Attributes.CONNECTION_MANAGER);
        connectionManager.shutdown();
    }

    @Override
    protected void loadBean(Class<?> c)
            throws ReflectiveOperationException {
        if (c.isAnnotationPresent(Repository.class)) {
            loadRepository(c);
            return;
        }
        if (c.isAnnotationPresent(Service.class)) {
            loadService(c);
            return;
        }
    }

    @SuppressWarnings("unchecked")
    private void loadRepository(Class c) throws ReflectiveOperationException {
        Constructor<?> constructor = c.getConstructor(ConnectionHolder.class);
        Object o = constructor.newInstance(connectionHolder);
        String s = null;
        for (Object obj : o.getClass().getInterfaces()) {
            if (obj.toString().contains("Repository")) {
                s = obj.toString().replace("interface ", "");
                break;
            }
        }
        if (s != null) {
            beans.put(s, o);
        } else {
            LOGGER.debug("Repository {} wasnt logged because its not repository", o);
        }
    }

    private void loadService(Class c) throws IllegalAccessException, InstantiationException {
        Object o = c.newInstance();
        String s = null;
        for (Object obj : o.getClass().getInterfaces()) {
            if (obj.toString().contains("Service")) {
                s = obj.toString().replace("interface ", "");
                break;
            }
        }
        if (s != null) {
            services.put(s, o);
            return;
        }
        LOGGER.debug("Service {} wasnt logged Because its not repository", o);
    }

    private void autowireBeans() throws IllegalAccessException {
        for (Object bean : beans.values()) {
            for (Field field : bean.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Autowired.class)) {
                    Object existingBean = beans.get(field.getType().getName());
                    if (existingBean != null) {
                        field.set(bean, existingBean);
                    }
                }
            }
        }
    }

    private void manageServices() {
        for (Map.Entry<String, Object> entry : services.entrySet()) {
            Object service = entry.getValue();
            Object proxy = Proxy.newProxyInstance(
                    this.getClass().getClassLoader(),
                    service.getClass().getInterfaces(),
                    new AnnotationHandler(connectionHolder, service, connectionManager, cacheManager.getCache(Constants.Attributes.CACHE))
            );
            servletContext.setAttribute(entry.getKey(), proxy);
        }
    }

}
