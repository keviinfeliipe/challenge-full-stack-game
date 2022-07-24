package co.com.challenge.controller;

import co.com.challenge.model.carta.CartaMaestra;
import co.com.challenge.usecase.CartaUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@Component
@RequestMapping("/api/v1/carta")
public class CartaController {

    private final CartaUseCase useCase;

    public CartaController(CartaUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping()
    public Mono<ResponseEntity<Flux<CartaMaestra>>> listar(){
        return Mono.just(
                ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(useCase.findAll())
        );
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<CartaMaestra>> ver(@PathVariable String id){
        return useCase.findById(id)
                .map(carta ->
                        ResponseEntity
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON).body(carta))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<CartaMaestra>> crear(@RequestBody CartaMaestra carta){
        return useCase.save(carta).map(p-> ResponseEntity
                .created(URI.create("/api/v1/carta/".concat(p.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(p)
        );
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<CartaMaestra>> editar(@RequestBody CartaMaestra carta, @PathVariable String id){
        return useCase.findById(id).flatMap(carta1 -> {
                    carta1.setNombre(carta.getNombre());
                    carta1.setDescipcion(carta.getDescipcion());
                    carta1.setCaracteristica(carta.getCaracteristica());
                    carta1.setPoder(carta.getPoder());
                    return useCase.save(carta1);
                }).map(carta1->ResponseEntity.created(URI.create("/api/v1/carta/".concat(carta1.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(carta1))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminar(@PathVariable String id){
        return useCase.findById(id).flatMap(carta ->{
            return useCase.deleteById(carta.getId()).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

}
