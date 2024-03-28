package com.microservices.swiggy.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.resolver.DefaultAddressResolverGroup;
import reactor.netty.http.client.HttpClient;

@Component
public class AuthenticationFilter extends 
  AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
	
	@Autowired
	private RestTemplate template;


    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange,chain)->{
        	if (!exchange.getRequest()
        			.getHeaders()
        		    .containsKey(HttpHeaders.AUTHORIZATION)) {
        		        throw new RuntimeException("Missing Authorization Header");
        		    }
        	String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
        	  if (authHeader != null && authHeader.startsWith("Bearer ")) {
                  authHeader = authHeader.substring(7);
              }
        	  HttpClient httpClient = HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
        	  WebClient.builder()
        	  .clientConnector(new ReactorClientHttpConnector(httpClient))
              .baseUrl("http://IDENTITYSERVICE")
              .build()
              .get()
              .uri("/auth/validate?token=" + authHeader)
              .retrieve()
              .bodyToMono(String.class)
              .doOnError(e -> {
            	  throw new RuntimeException("un_authorized access to application");
              })
              .subscribe();
//        	  try{
//        		  System.out.println(authHeader);
//        		  template.getForObject("http://IDENTITYSERVICE/auth/validate?token=" + authHeader, String.class);
//
//        	  } catch (Exception e) {
//                throw new RuntimeException("un_authorized access to application");
//            }

        	return chain.filter(exchange);
        });
    }

    public static class Config {
        // ...
    }
}