package za.ac.cput.controller.details;

/**
 * Name: Onele Tomson
 * Student number: 219483280
 *CountryController
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.details.Country;
import za.ac.cput.factory.details.CountryFactory;
import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CountryControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CountryController countryController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private Country country;

    private String urlBase;

    @BeforeEach
    void setUp(){
        assertNotNull(countryController);
        this.country = CountryFactory.createCountry(null , "45862587");
        this.urlBase = "http://localhost" + this.port + "/school_management_gr23/country";
    }

    @Test
    void a_create() {
        String url = urlBase + "save_country";
        System.out.println(url);


        ResponseEntity<Country> responseEntity = this.testRestTemplate
                .postForEntity(url, this.country, Country.class);
        System.out.println(responseEntity);
        assertAll(
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
                () -> assertNotNull(responseEntity.getBody())
        );
    }

    @Test
    void b_read(){
        String url = urlBase + "/read" + this.country.getId();
        System.out.println(url);
        ResponseEntity<Country> responseEntity = this.testRestTemplate.getForEntity(url , Country.class);
        System.out.println(responseEntity);
        assertAll(
                () ->assertEquals(HttpStatus.OK , responseEntity.getStatusCode()) , () -> assertNotNull(responseEntity.getBody())
        );
    }

    @Test
    void c_delete(){
        String url = urlBase + "deleteCountry/" + country.getId();
        this.testRestTemplate.delete(url);
    }

    @Test
    void d_getAll(){
        String url = urlBase + "allCountries";
        System.out.println(url);
        ResponseEntity<Country[]> responseEntity = this.testRestTemplate
                .getForEntity(url , Country[].class);

        System.out.println(Arrays.asList(responseEntity.getBody()));
        assertAll(
                () -> assertEquals(HttpStatus.OK , responseEntity.getStatusCode()),
                () -> assertTrue(responseEntity.getBody().length==0)
        );
    }
}

