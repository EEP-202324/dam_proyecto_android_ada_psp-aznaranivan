package com.example.autoescuela;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class AutoescuelaJsonTest {

    @Autowired
    private JacksonTester<Autoescuela> json;
    
    @Autowired
    private JacksonTester<Autoescuela[]> jsonList;

    private Autoescuela[] autoescuelas;
    
    @BeforeEach
    void setUp() {
        autoescuelas = Arrays.array(
                new Autoescuela(1L,"Fran", "Maurín", "34567871L", "usuario1"),
                new Autoescuela(2L,"Pedro", "Perez", "180517429M", "usuario2"),
                new Autoescuela(3L,"Juan", "Garcia", "828612807I", "usuario3"));
    }

    @Test
    void autoescuelaSerializationTest() throws IOException {
        Autoescuela autoescuela = autoescuelas[0];
    	assertThat(json.write(autoescuela)).isStrictlyEqualToJson("expected.json"); 

        assertThat(json.write(autoescuela)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(autoescuela)).extractingJsonPathNumberValue("@.id")
            .isEqualTo(1);

        assertThat(json.write(autoescuela)).hasJsonPathStringValue("@.nombre");
        assertThat(json.write(autoescuela)).extractingJsonPathStringValue("@.nombre")
            .isEqualTo("Fran");

        assertThat(json.write(autoescuela)).hasJsonPathStringValue("@.apellido");
        assertThat(json.write(autoescuela)).extractingJsonPathStringValue("@.apellido")
            .isEqualTo("Maurín");

        assertThat(json.write(autoescuela)).hasJsonPathStringValue("@.DNI");
        assertThat(json.write(autoescuela)).extractingJsonPathStringValue("@.DNI")
             .isEqualTo("34567871L");
    }
    
    @Test
    void autoescuelaDeserializationTest() throws IOException {
       String expected = """
               {
                   "id":2,
                   "nombre":"Pedro",
                   "apellido":"Perez",
                   "DNI":"180517429M",
                   "owner":"usuario2"                 
               }
               """;
     
       assertThat(json.parse(expected)).isEqualTo(new Autoescuela(2L,"Pedro", "Perez", "180517429M","usuario2"));
       assertThat(json.parseObject(expected).getId()).isEqualTo(2L);
       assertThat(json.parseObject(expected).getNombre()).isEqualTo("Pedro");
       assertThat(json.parseObject(expected).getApellido()).isEqualTo("Perez");
       assertThat(json.parseObject(expected).getDNI()).isEqualTo("180517429M");
    }
    
    @Test
    void autoescuelaListSerializationTest() throws IOException {
       assertThat(jsonList.write(autoescuelas)).isStrictlyEqualToJson("list.json");
    }
    
    @Test
    void autoescuelaListDeserializationTest() throws IOException {
        String expected = """
                [
                     {"id": 1, "nombre": "Fran" , "apellidos": "Maurín" , "DNI": "34567871L" , "owner": "usuario1"},
                     {"id": 2, "nombre": "Pedro" , "apellidos": "Perez" , "DNI": "180517429M" , "owner": "usuario2"},
                     {"id": 3, "nombre": "Juan", "apellidos": "Garcia" , "DNI": "828612807I" , "owner": "usuario3"}
                                                  
                ]
                """;
        assertThat(jsonList.parse(expected)).isEqualTo(autoescuelas);
    }
    
}