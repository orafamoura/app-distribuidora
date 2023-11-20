package com.projetoapptabacaria.webtabaca.view.controller.model.product;

public class ProductResponse {//resposta do Product Request, o que vou devolver

   // private Long id;
    private String name;
    private Long quantity;
    private Double value;
    private String description;

    /*public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

