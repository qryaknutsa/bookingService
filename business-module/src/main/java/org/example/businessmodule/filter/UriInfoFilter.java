package org.example.businessmodule.filter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UriInfoFilter implements ContainerRequestFilter {

    private static final ThreadLocal<UriInfo> uriInfoThreadLocal = new ThreadLocal<>();

    @Context
    private UriInfo uriInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        uriInfoThreadLocal.set(uriInfo);
    }

    public static UriInfo getUriInfo() {
        return uriInfoThreadLocal.get();
    }

    public static void clear() {
        uriInfoThreadLocal.remove();
    }
}
