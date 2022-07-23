package co.com.challenge.controller;

import co.com.challenge.model.juego.Carta;
import co.com.challenge.usecase.CartaUseCase;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Component
@RequestMapping("/api/v1/carta")
public class CartaController {



}
