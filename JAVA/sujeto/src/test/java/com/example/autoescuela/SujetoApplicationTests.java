package com.example.autoescuela;

import static org.assertj.core.api.Assertions.assertThat;
import java.net.URI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.sujeto.Sujeto;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;


class SujetoApplicationTests {

	@Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnASujetoWhenDataIsSaved() {
        ResponseEntity<String> response = restTemplate.getForEntity("/sujetos/99", String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(99);
        
        String nombre = documentContext.read("$.nombre");
        assertThat(nombre).isEqualTo("Ivan");
        
        String apellido = documentContext.read("$.apellido");
        assertThat(apellido).isEqualTo("Maurin");
        
        String DNI = documentContext.read("$.DNI");
        assertThat(DNI).isEqualTo("34567871L");
    }
    
    @Test
    void shouldNotReturnASujetoWithAnUnknownId() {
      ResponseEntity<String> response = restTemplate.getForEntity("/sujetos/1000", String.class);

      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
      assertThat(response.getBody()).isBlank();
    }

    @Test
    void shouldCreateANewSujeto() {
       Sujeto newSujeto = new Sujeto(null, "Ivan","Maurin","34567871L");
       ResponseEntity<Void> createResponse = restTemplate.postForEntity("/sujetos", newSujeto, Void.class);
       assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
       
       URI locationOfNewSujeto = createResponse.getHeaders().getLocation();
       ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewSujeto, String.class);
       assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
       
       DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
       Number id = documentContext.read("$.id");
       String nombre = documentContext.read("$.nombre");
       String apellido = documentContext.read("$.apellido");
       String DNI = documentContext.read("$.DNI");
       
       assertThat(id).isNotNull();
       assertThat(nombre).isEqualTo("Ivan");
       assertThat(apellido).isEqualTo("Maurin");
       assertThat(DNI).isEqualTo("34567871L");
    }

}

