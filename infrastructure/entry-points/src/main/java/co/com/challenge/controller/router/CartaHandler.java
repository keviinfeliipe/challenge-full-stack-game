package co.com.challenge.controller.router;

import co.com.challenge.model.carta.Carta;
import co.com.challenge.usecase.CartaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@RequiredArgsConstructor
public class CartaHandler {

    private final CartaUseCase useCase;

    public Mono<ServerResponse> hola(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue("Hola mungo :v"));
    }

    public Mono<ServerResponse> save(ServerRequest request){
        var carta = request.bodyToMono(Carta.class);

        return carta.flatMap(pet1 -> {
            return useCase.save(pet1)
                    .flatMap(cartaDB -> ServerResponse
                            .created(URI.create("/api/usecase/pet/".concat(cartaDB.getId())))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(fromValue(cartaDB)));
        });
    }

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(useCase.findAll(), Carta.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        var id = request.pathVariable("id");
        return useCase.findById(id)
                .flatMap(pet -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(pet)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> update(ServerRequest request){
        var id = request.pathVariable("id");
        var petRequest = request.bodyToMono(Carta.class);
        var petDB = useCase.findById(id);
        return petDB
                .zipWith(petRequest, (car, req) -> {
                    car.setNombre(req.getNombre());
                    car.setDescipcion(req.getDescipcion());
                    car.setCaracteristica(req.getCaracteristica());
                    car.setPoder(req.getPoder());
                    return car;
                }).flatMap(car -> ServerResponse
                        .created(URI.create("/api/usecase/pet/".concat(car.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(useCase.save(car),Carta.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete(ServerRequest request){
        var id = request.pathVariable("id");
        var carDB = useCase.findById(id);
        return carDB.flatMap(pet -> useCase
                .deleteById(id)
                .then(ServerResponse.noContent().build())
        ).switchIfEmpty(ServerResponse.notFound().build());
    }

}
