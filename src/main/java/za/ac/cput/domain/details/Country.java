package za.ac.cput.domain.details;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Country implements Serializable {

    @Id
    private String id;
    @NotNull
    private String name;

    protected Country() {
    }

    private Country(Builder b){
        this.id = b.id;
        this.name = b.name;
    }

    public String getId() {
        return id;
    }

    public void setId(String countryId) {
        this.id = countryId;
    }



    public String getName() {
        return name;
    }

    public void setName(String countryName) {
        this.name = countryName;
    }

    @Override
    public String toString() {
        return "Country{" +
                " Country id='" + id + '\'' +
                ", countryName='" + name + '\'' +
                '}';
    }


    public static class Builder{
        private String id;
        private String name;

        public Builder id(String id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Country.Builder copy(Country c){
            this.id = c.id;
            this.name = c.name;
            return this;
        }

        public Country build(){
            return new Country(this);
        }



    }
}
