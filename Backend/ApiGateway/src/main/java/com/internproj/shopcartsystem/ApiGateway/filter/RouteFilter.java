package com.internproj.shopcartsystem.ApiGateway.filter;

import java.util.List;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.function.Predicate;
@Component
public class RouteFilter {
	
	
	
//	public  static final List<String> openApiEndpoints = List.of(
//			"/user/register",
//			"/user/authenticate",
//			"user/checkIfTokenExpired"
//			);
//	
//	public Predicate<ServerHttpRequest> isSecured = 
//			request -> openApiEndpoints.stream()
//						.noneMatch(uri -> request.getURI().getPath().contains(uri));
	public static final List<String> openApiEndpoints = List.of(
            "/user/register",
            "/user/authenticate",
            "/user/checkIfTokenExpired"
    );

    public static final List<String> authorizedEndpoints = List.of(
            "/product/viewallProducts"
            // Add more authorized endpoints as needed
    );

    public Predicate<ServerHttpRequest> isSecured = request ->
            openApiEndpoints.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri))
                    && authorizedEndpoints.stream()
                    .anyMatch(uri -> request.getURI().getPath().startsWith(uri))
                    && isAdmin(request);

            private boolean isAdmin(ServerHttpRequest request) {
                // Extract the user's role from the request or authentication details
                // Perform the necessary checks to determine if the user is an admin
                // Return true if the user is an admin, false otherwise
                return false/* your logic to check if user is admin */;
            }
}
