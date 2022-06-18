package za.ac.cput.factory.details;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.details.Country;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.class)
public class CountryFactoryTest {

    ArrayList<Country> countries = new ArrayList<>();

    private static final Country country1 = CountryFactory.builder("78795124" , "Mexico");

    private static final Country country2 = CountryFactory.builder("219483280" , "South Africa");
    private static final Country country3 = CountryFactory.builder("45628615" , "Ethiopia");


    private static final Country country4 = country3;


    @Test
    void a_builder(){
        countries.add(country1);
        System.out.println("Added " + countries.get(0));
        countries.add(country2);
        System.out.println("Added " + countries.get(1));
        countries.add(country3);
        System.out.println("Added " + countries.get(2));
        assertNotNull(countries);

    }

    @Test
    void b_objectEquality(){

        assertNotEquals(country1.getId() , country2.getId());
        assertNotEquals(country1.getName() , country2.getName());
        assertNotEquals(country1 , country2);
        assertEquals(country3 , country4);

        assertNotNull(country1);
        assertNotNull(country2);
        assertNotNull(country3);
        assertNotNull(country4);

        System.out.println("Test Successful");
    }

    @Test
    void c_objectIdentity(){
        assertNotSame(country1.getId() , country2.getId());
        assertNotSame(country1.getName() , country2.getName());
        assertSame(country3 , country4);

        assertNotNull(country1);
        assertNotNull(country2);
        assertNotNull(country3);
        assertNotNull(country4);
        System.out.println("Test Successful");

    }

}
