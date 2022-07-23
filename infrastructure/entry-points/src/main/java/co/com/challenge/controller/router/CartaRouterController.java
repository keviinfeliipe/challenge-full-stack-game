package co.com.challenge.controller.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
@Configuration
public class CartaRouterController {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(CartaHandler handler) {
        return route(POST("/api/v1/carta"), handler::save)
                .andRoute(GET("/api/v1/carta"), handler::findAll )
                .andRoute(GET("/api/v1/carta/{id}"), handler::findById)
                .andRoute(PUT("/api/v1/carta/{id}"), handler::update)
                .andRoute(DELETE("/api/v1/carta/{id}"), handler::delete)
                .andRoute(GET("/api/v1/carta/hola"), handler::hola);
    }
}
