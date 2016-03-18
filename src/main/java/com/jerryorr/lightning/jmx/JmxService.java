package com.jerryorr.lightning.jmx;

import java.lang.management.ManagementFactory;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.openmbean.CompositeData;

import org.springframework.stereotype.Component;

/**
 * Service for retrieving basic JMX object attributes.
 *  
 * @author jerryorr
 */
@Component
public class JmxService {
	private static final MBeanServer M_BEAN_SERVER = ManagementFactory.getPlatformMBeanServer();

	public <T> T getAttribute(String objectName, String attribute, Class<T> type) {
		try {
			return (T) M_BEAN_SERVER.getAttribute(new ObjectName(objectName), attribute);
		} catch (AttributeNotFoundException | InstanceNotFoundException | MalformedObjectNameException | MBeanException
				| ReflectionException e) {
			throw new JmxRuntimeException(
					"Exception looking up attribute " + attribute + " on JMX object " + objectName, e);
		}
	}

	private Long parseLong(Object o) {
		return o == null ? null : Long.parseLong(o.toString());
	}

	public MemoryUsage getMemory() {
		CompositeData usage = getAttribute("java.lang:type=Memory", "HeapMemoryUsage", CompositeData.class);
		return new MemoryUsage(parseLong(usage.get("used")), parseLong(usage.get("max")));
	}

	public Integer getActiveSessions() {
		return getAttribute("Tomcat:context=/,host=localhost,type=Manager", "activeSessions", Integer.class);
	}
}
