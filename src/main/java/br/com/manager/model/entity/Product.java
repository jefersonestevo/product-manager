package br.com.manager.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity(name = "product")
@SequenceGenerator(name = "seq_product", sequenceName = "seq_product", initialValue = 1000)
public class Product extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_product")
    private Long id;

    @NotNull(message = "{product.name.NotNull.message}")
    @Size(min = 1, max = 200, message = "{product.name.Size.message}")
    private String name;

    @Digits(integer = 15, fraction = 2, message = "{product.value.Digits.message}")
    private BigDecimal value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
