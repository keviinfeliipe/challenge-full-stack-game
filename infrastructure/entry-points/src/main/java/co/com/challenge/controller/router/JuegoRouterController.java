package co.com.challenge.controller.router;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

public class JuegoRouterController {
    public RouterFunction<ServerResponse> routerFunction(JuegoHandler handler){
        return route(POST("api/v2/juego"), handler::crearJuego);
    }
}
