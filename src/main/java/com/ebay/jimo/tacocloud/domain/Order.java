package com.ebay.jimo.tacocloud.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
@Entity
@Table(name = "Taco_Order") // To specify the table name otherwise the table name "Order" will be used.
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date placeAt;
    @NotBlank(message = "Name is required.")
    @Column(name = "deliverName")
    private String name;
    @NotBlank(message = "Street is required.")
    @Column(name = "deliverStreet")
    private String street;
    @NotBlank(message = "City is required.")
    @Column(name = "deliverCity")
    private String city;
    @NotBlank(message = "State is required.")
    @Column(name = "deliverState")
    private String state;
    @NotBlank(message = "Zip code is required.")
    @Column(name = "deliverZip")
    private String zip;
    @CreditCardNumber(message = "Not a valid credit card number.")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\\\/])([1-9][0-9])$", message = "Must be formatted MM/YY.")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;
    @ManyToMany(targetEntity = Taco.class)
    public List<Taco> tacos = new ArrayList<>();

    public void addDesign(Taco taco) {
        this.tacos.add(taco);
    }

    @PrePersist
    void placeAt() {
        this.placeAt = new Date();
    }
}
