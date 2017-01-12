package ua.nure.piontkovskyi.SummaryTask4.listener;

import net.sf.ehcache.CacheManager;
import ua.nure.piontkovskyi.SummaryTask4.db.holder.ThreadLocalConnectionHolder;
import ua.nure.piontkovskyi.SummaryTask4.db.manager.HikariCPManager;
import ua.nure.piontkovskyi.SummaryTask4.listener.context.ContextLoader;
import ua.nure.piontkovskyi.SummaryTask4.util.Settings;

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
        loader.load(
                "ua.nure.piontkovskyi.SummaryTask4.repository",
                "ua.nure.piontkovskyi.SummaryTask4.service"
        );
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        loader.destroy(sce.getServletContext());
    }

}
