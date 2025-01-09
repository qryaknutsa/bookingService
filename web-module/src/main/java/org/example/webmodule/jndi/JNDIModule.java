package org.example.webmodule.jndi;

import org.example.businessmodule.BusinessService;
import org.example.businessmodule.BusinessServiceRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class JNDIModule {
    private static final String WILDFLY_HOST = "localhost";
    private static final int EJB_PORT = 8080;
    private static final Context context;

    static {
        Properties jndiProperties = new Properties();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put("jboss.naming.client.ejb.context", true);
        jndiProperties.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", false);
        jndiProperties.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", false);
        jndiProperties.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
        jndiProperties.put(Context.SECURITY_PRINCIPAL, "vika");
        jndiProperties.put(Context.SECURITY_CREDENTIALS, "vika");
        jndiProperties.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, "http-remoting://" + WILDFLY_HOST + ":" + EJB_PORT));
//        jndiProperties.put("org.jboss.ejb.client.verbose", true); // Enable verbose ejb client logging

        try {
            context = new InitialContext(jndiProperties);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public static BusinessServiceRemote getBusinessService() {
        try {
            return (BusinessServiceRemote) context.lookup("ejb:/business-module-1.0-SNAPSHOT/BusinessService!org.example.businessmodule.BusinessServiceRemote");
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }

}
