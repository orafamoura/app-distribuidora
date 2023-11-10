package com.projetoapptabacaria.webtabaca.shared;

/** metodo de visualizacao do cliente
 * @productDTO retorna o visual pro cliente
 * */
public class ProductDTO {

    private String name;

    private Integer quantity;

    private Double value;

    private Long description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Long getDescription() {
        return description;
    }

    public void setDescription(Long description) {
        this.description = description;
    }



}
