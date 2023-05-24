package co.com.challenge.config;

import co.com.challenge.services.JuegoActivoQueryService;
import co.com.challenge.services.JuegoActualQueryService;
import co.com.challenge.usecase.RepartirCartasUseCase;
import co.com.challenge.usecase.listeners.*;
import co.com.sofka.business.generic.ServiceBuilder;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.infraestructure.asyn.SubscriberEvent;
import co.com.sofka.infraestructure.bus.EventBus;
import co.com.sofka.infraestructure.repository.EventStoreRepository;
import org.reactivecommons.utils.ObjectMapperI;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.MongoPropertiesClientSettingsBuilderCustomizer;
import org.springframework.boot.autoconfigure.mongo.ReactiveMongoClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;


@Configuration
@ComponentScan(basePackages = {"co.com.challenge*","co.com.challenge.usecase","co.com.challenge.model","co.com.challenge.controller"},
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+Repository$"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+Adapter$"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+Controller$"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+Handler$")
        },
        useDefaultFilters = false)
public class ApplicationConfig {

    private String origin = "*";
    public static final String EXCHANGE = "juego-heroes";
    //test

    @Bean
    public MongoDBSecret dbSecret(Environment env) {
        return new MongoDBSecret(env.getProperty("spring.data.mongodb.uri"));
    }

    @Bean
    public ReactiveMongoClientFactory mongoProperties(MongoDBSecret secret, Environment env) {
        MongoProperties properties = new MongoProperties();
        properties.setUri(secret.getUri());
        List<MongoClientSettingsBuilderCustomizer> list = new ArrayList<>();
        list.add(new MongoPropertiesClientSettingsBuilderCustomizer(properties, env));
        return new ReactiveMongoClientFactory(list);
    }

    @Bean
    public ObjectMapperI objectMapper() {
        return new ObjectMapperI();
    }

    @Bean
    public SubscriberEvent subscriberEvent(EventStoreRepository eventStoreRepository, EventBus eventBus) {
        return new SubscriberEvent(eventStoreRepository, eventBus);
    }

    @Bean
    public ServiceBuilder serviceBuilder(
            JuegoActivoQueryService juegoActivoQueryService,
            JuegoActualQueryService jugadorCartasQueryService
    ) {
        ServiceBuilder serviceBuilder = new ServiceBuilder();
        serviceBuilder.addService(juegoActivoQueryService);
        serviceBuilder.addService(jugadorCartasQueryService);
        return serviceBuilder;
    }

    @Bean
    public Set<UseCase.UseCaseWrap> listUseCasesForListener(
            RepartirCartasUseCase repartirCartasUseCase,
            CrearTableroUseCase crearTableroUseCase,
            CrearRondaUseCase crearRondaUseCase,
            IniciarCronometroUseCase iniciarCronometroUseCase,
            DescontarTiempoUseCase DescontarTiempoUseCase,
            DeterminarGanadorDeRondaUseCase determinarGanadorUseCase,
            ValidarTiempoUseCase validarTiempoUseCase,
            ValidarCartasApostadasUseCase validarCartasApostadasUseCase,
            MostrarCartasApostadasUseCase mostrarCartasApostadasUseCase
    ) {
        return Set.of(
                new UseCase.UseCaseWrap("juego.CartasRepartidas", (UseCase) crearTableroUseCase),
                new UseCase.UseCaseWrap("juego.TableroCreado", (UseCase) crearRondaUseCase),
                new UseCase.UseCaseWrap("juego.RondaCreada", (UseCase) iniciarCronometroUseCase),
                new UseCase.UseCaseWrap("juego.CronometroIniciado", (UseCase) DescontarTiempoUseCase),
                new UseCase.UseCaseWrap("juego.TiempoDescontado", (UseCase) validarTiempoUseCase),
                new UseCase.UseCaseWrap("juego.TiempoTerminado", (UseCase) validarCartasApostadasUseCase),
                new UseCase.UseCaseWrap("juego.CartaAlAzarSeleccionada", (UseCase) mostrarCartasApostadasUseCase),
                new UseCase.UseCaseWrap("juego.CartasApostadasMostradas", (UseCase) determinarGanadorUseCase)
        );
    }

    @Bean
    public RabbitAdmin rabbitmqAdmin(RabbitTemplate rabbitmqTemplate) {
        var admin = new RabbitAdmin(rabbitmqTemplate);
        admin.declareExchange(new TopicExchange(EXCHANGE));
        return admin;
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                if (!origin.isBlank()) {
                    Logger.getLogger("config").info("Allowed Origin ==> " + origin);
                    registry.addMapping("/**").allowedOrigins(origin);
                }
            }
        };
    }

}
