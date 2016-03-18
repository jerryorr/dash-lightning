package com.jerryorr.lightning;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jerryorr.lightning.dash.Push;
import com.jerryorr.lightning.jmx.JmxService;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * A listener that pushes an updated session count to Dash when a session is
 * created or destroyed.
 * 
 * @author jerryorr
 */
@Component
public class SessionListener implements HttpSessionListener, ServletContextListener {
	@Resource
	JmxService jmxService;
	
	@Resource
	Push push;
	
	// from src/main/resources/application.properties
	@Value("${dash.sessions}")
	private String pushUrl;

	// Notice that we're doing the push asynchronously so we aren't slowing down
	// the user's web request
	@Async
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		push();
	}
	
	@Async
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		push();
	}

	private void push() {
		Integer sessions = jmxService.getActiveSessions();
		try {
			Unirest.post(pushUrl)
					.header("content-type", "text/plain").body(sessions).asString().getBody();
			
			// Or using a more convenient API...
			// push.start().url(pushUrl).plain(sessions).push();
		} catch (UnirestException e) {
			throw new RuntimeException("Error pushing active session count", e);
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext())
				.getAutowireCapableBeanFactory().autowireBean(this);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
