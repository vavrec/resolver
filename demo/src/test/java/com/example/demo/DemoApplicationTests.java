package com.example.demo;

import com.example.demo.model.CarResponse;
import com.example.demo.model.ColomboResponse;
import com.example.demo.model.Response;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DemoApplicationTests {

    @Test
    void simpleTypeDeserializer() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        var object = mapper.readValue("""
                {
                   "header" : {
                        "type": "simpleCar"
                   }
                }      
                """, Response.class);

        assertThat(object, instanceOf(Response.class));
    }

    @Test
    void colomboTypeDeserializer() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        var object = mapper.readValue("""
                {
                   "header" : {
                        "type": "colombo"
                   }
                }      
                """, Response.class);

        assertThat(object, instanceOf(ColomboResponse.class));
    }

    @Test
    void unknownType() {

        ObjectMapper mapper = new ObjectMapper();

        assertThrows( JsonParseException.class,() -> {
            mapper.readValue("""
                {
                   "header" : {
                        "type": "none"
                   }
                }      
                """, Response.class);

        } );
    }

    @Test
    void carDeserializer() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        var object = mapper.readValue("""
                {
                   "header" : {
                        "type": "car"
                   },
                   "detail": {
                    "name" : "sss"
                   }
                }
                """, Response.class);

        assertThat(object, instanceOf(CarResponse.class));

    }

}
