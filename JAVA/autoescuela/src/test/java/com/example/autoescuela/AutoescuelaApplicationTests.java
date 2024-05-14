package com.example.autoescuela;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)

class AutoescuelaApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnAAutoescuelaWhenDataIsSaved() {
        ResponseEntity<String> response = restTemplate.getForEntity("/autoescuela/1", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(1);

        String nombre = documentContext.read("$.nombre");
        assertThat(nombre).isEqualTo("Fran");
        
        String apellido = documentContext.read("$.apellido");
        assertThat(apellido).isEqualTo("Maurín");
        
        String DNI = documentContext.read("$.DNI");
        assertThat(DNI).isEqualTo("34567871L");
    }
    
    @Test
    void shouldNotReturnAAutoescuelaWithAnUnknownId() {
      ResponseEntity<String> response = restTemplate.getForEntity("/autoescuela/1000", String.class);

      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
      assertThat(response.getBody()).isBlank();
    }
    
    @Test 
    @DirtiesContext
    void shouldCreateANewAutoescuela() {
       Autoescuela newAutoescuela = new Autoescuela(null,"Fran", "Maurín", "34567871L", "usuario1");
       ResponseEntity<Void> createResponse = restTemplate.postForEntity("/autoescuela", newAutoescuela, Void.class);
       assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

       URI locationOfNewAutoescuela = createResponse.getHeaders().getLocation();
       ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewAutoescuela, String.class);
       assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

       DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
       Number id = documentContext.read("$.id");
       assertThat(id).isEqualTo(1);

       String nombre = documentContext.read("$.nombre");
       assertThat(nombre).isEqualTo("Fran");

       String apellido = documentContext.read("$.apellido");
       assertThat(apellido).isEqualTo("Maurín");

       String DNI = documentContext.read("$.DNI");
       assertThat(DNI).isEqualTo("34567871L");

    }
    
    @Test
    void shouldReturnAllAutoescuelaWhenListIsRequested() {
        ResponseEntity<String> response = restTemplate.getForEntity("/autoescuela", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int autoescuelaCount = documentContext.read("$.length()");
        assertThat(autoescuelaCount).isEqualTo(3);

        JSONArray ids = documentContext.read("$..id");
        assertThat(ids).containsExactlyInAnyOrder(1, 2, 3);

        JSONArray nombres = documentContext.read("$..nombre");
        assertThat(nombres).containsExactlyInAnyOrder("Fran", "Pedro", "Juan");
        
        JSONArray apellidos = documentContext.read("$..apellido");
        assertThat(apellidos).containsExactlyInAnyOrder("Maurín", "Perez", "Garcia");
        
        JSONArray DNIS = documentContext.read("$..DNI");
        assertThat(DNIS).containsExactlyInAnyOrder("34567871L", "180517429M", "828612807I");
        
    }
    
    @Test
    void shouldReturnAPageOfAutoescuela() {
        ResponseEntity<String> response = restTemplate.getForEntity("/autoescuelas?page=0&size=1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray page = documentContext.read("$[*]");
        assertThat(page.size()).isEqualTo(1);
    }
    
    @Test
    void shouldReturnASortedPageOfAutoescuelas() {
        ResponseEntity<String> response = restTemplate.getForEntity("/autoescuelas?page=0&size=1&sort=amount,desc", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray read = documentContext.read("$[*]");
        assertThat(read.size()).isEqualTo(1);

        String nombre = documentContext.read("$[0].nombre");
        assertThat(nombre).isEqualTo("Juan");
        
        String apellido = documentContext.read("$[0].apellido");
        assertThat(apellido).isEqualTo("Garcia");
        
        String DNI = documentContext.read("$[0].DNI");
        assertThat(DNI).isEqualTo("828612807I");
        
        
    }

    @Test
    void shouldReturnASortedPageOfAutoescuelasWithNoParametersAndUseDefaultValues() {
        ResponseEntity<String> response = restTemplate.getForEntity("/autoescuelas", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray page = documentContext.read("$[*]");
        assertThat(page.size()).isEqualTo(3);

        JSONArray nombres = documentContext.read("$..nombre");
        assertThat(nombres).containsExactly("Pedro", "Fran", "Juan");
        
        JSONArray apellidos = documentContext.read("$..apellido");
        assertThat(apellidos).containsExactly("Perez", "Maurín", "Garcia");
        
        JSONArray DNIS = documentContext.read("$..DNI");
        assertThat(DNIS).containsExactly("180517429M", "34567871L", "828612807I");
    }
    
    @Test
    void shouldNotReturnAAutoescuelaWhenUsingBadCredentials() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/autoescuelas/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);

        response = restTemplate
                .getForEntity("/autoescuelas/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    void shouldRejectUsersWhoAreNotAutoescuelaOwners() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/autoescuelas/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void shouldNotAllowAccessToAutoescuelasTheyDoNotOwn() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/autoescuelas/3", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
    
}