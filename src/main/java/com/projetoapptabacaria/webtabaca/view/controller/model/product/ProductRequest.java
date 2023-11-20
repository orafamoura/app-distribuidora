package com.projetoapptabacaria.webtabaca.view.controller.model.product;

public class ProductRequest {  //vai ser o tipo de dado que eu espero receber fazendo uma requisicao pro back end
    // como se eu estivesse cadastrando o produto, excluimos o id pois ele e gerado automaticamente
    private String name;
    private Long quantity;
    private Double value;
    private String description;

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

