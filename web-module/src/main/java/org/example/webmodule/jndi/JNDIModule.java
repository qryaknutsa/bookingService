//package org.example.webmodule.jndi;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import java.util.Properties;
//
//public class JNDIModule {
//    private static final String WILDFLY_HOST = "booking-ejb-1";
//    private static final int BARS_EJB_PORT = 8080;
//    private static final Context context;
//
//    static {
//        Properties jndiProperties = new Properties();
//        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
//        jndiProperties.put("jboss.naming.client.ejb.context", true);
//        jndiProperties.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", false);
//        jndiProperties.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", false);
//        jndiProperties.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
//        jndiProperties.put(Context.SECURITY_PRINCIPAL, "slovy");
//        jndiProperties.put(Context.SECURITY_CREDENTIALS, "1234");
//        jndiProperties.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, "http-remoting://" + WILDFLY_HOST + ":" + BARS_EJB_PORT));
//
//        try {
//            context = new InitialContext(jndiProperties);
//        } catch (NamingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static IEventService getEventService() {
//        try {
//            return (IEventService) context.lookup("ejb:/booking-module-final/EventService!com.slovy.booking.event.service.IEventService");
//        } catch (NamingException ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//
//}
