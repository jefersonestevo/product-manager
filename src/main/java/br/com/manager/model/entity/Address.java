package br.com.manager.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "address")
@SequenceGenerator(name = "seq_adress", sequenceName = "seq_adress", initialValue = 1000)
public class Address implements PMEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_adress")
    private Long id;

    @NotNull
    @Size(min = 1, max = 200)
    private String street;

    @NotNull
    @Size(min = 1, max = 20)
    private String number;

    @NotNull
    @Size(min = 1, max = 50)
    private String state;

    @NotNull
    @Size(min = 1, max = 10)
    private String country;

    @NotNull
    @Size(min = 1, max = 20)
    private String postalCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
