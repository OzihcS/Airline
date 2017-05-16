package listener;

import db.holder.ThreadLocalConnectionHolder;
import db.manager.HikariCPManager;
import listener.loader.ContextLoader;
import net.sf.ehcache.CacheManager;
import util.constants.Settings;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ContextLoadListener class.
 */
public final class ContextLoadListener implements ServletContextListener {

    private ContextLoader loader;

    public void contextInitialized(ServletContextEvent sce) {
        loader = new ContextLoader(
                new ThreadLocalConnectionHolder(), new HikariCPManager(), sce.getServletContext(),
                CacheManager.create(Settings.getEhcacheConfig())
        );
        loader.load("repository", "service");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        loader.destroy(sce.getServletContext());
    }

}
