package com.home.demos;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CustomZuulFilter extends ZuulFilter {

    @Override
    public Object run() {
        final RequestContext ctx = RequestContext.getCurrentContext();

        ctx.addZuulRequestHeader("end-to-end-id", UUID.randomUUID().toString());
        ctx.addZuulRequestHeader(
                "route-address",
                String.format("%s:%s", ctx.getRouteHost().getHost(), ctx.getRouteHost().getPort())
        );

        return null;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public int filterOrder() {
        return 1110;
    }

    @Override
    public String filterType() {
        return "pre";
    }

}
