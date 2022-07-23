package co.com.challenge.mongo.carta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cartas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartaDocument {

    @Id
    private String id;
    private String nombre;
    private String descipcion;
    private Integer poder;
    private String caracteristica;
    private String imagen;

}
