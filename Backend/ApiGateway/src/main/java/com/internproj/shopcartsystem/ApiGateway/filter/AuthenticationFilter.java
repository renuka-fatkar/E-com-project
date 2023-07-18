package com.internproj.shopcartsystem.ApiGateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import com.google.common.net.HttpHeaders;
import com.internproj.shopcartsystem.ApiGateway.util.JwtService;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{

	@Autowired
	private RouteFilter validator;
	
	@Autowired
	JwtService util;
	
	
	public AuthenticationFilter() {
		super(Config.class);
		// TODO Auto-generated constructor stub
	}

	public AuthenticationFilter(Class<Config> configClass) {
		super(configClass);
		// TODO Auto-generated constructor stub
	}

	
	public static class Config{
			
	}


	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange,chain)->{
			if(validator.isSecured.test(exchange.getRequest())) {
				if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
				throw new RuntimeException("Missisng authorization header");	
				}
				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				System.out.println("*********************************************************"+authHeader);
				if(authHeader!=null && authHeader.startsWith("Bearer ")) {
					authHeader = authHeader.substring(7);
					System.out.println("*********************************************************"+authHeader);

				}
				try {
					util.validateToken(authHeader);
				}
				catch(Exception e) {
					System.out.println("Invalid access");
					throw new RuntimeException("unauthorized access");
				}
			}
			return chain.filter(exchange);
		});
	}	
}
