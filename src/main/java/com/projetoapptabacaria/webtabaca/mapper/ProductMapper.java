package com.projetoapptabacaria.webtabaca.mapper;

import com.projetoapptabacaria.webtabaca.model.Product;
import com.projetoapptabacaria.webtabaca.shared.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring") // colocando o componentModel por usar o spring, aplicacao so de java nao precisaria
public interface ProductMapper {

    //@mapping(source = "nome1", target = "nome2") se o nome em alguma conversao estiver diferente para ele fazer a associacao
    ProductDTO productToProductDto(Product product);

    List<ProductDTO> toProductDTOs(List<Product> products);

    Product toProduct(ProductDTO productDTO);

    @Mapping(target = "id", ignore = true) // Ignorar a cópia do campo "id"
    void updateProductFromDto(ProductDTO productDto, @MappingTarget Product product);
}
