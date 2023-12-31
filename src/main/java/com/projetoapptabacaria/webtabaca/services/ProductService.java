package com.projetoapptabacaria.webtabaca.services;

import com.projetoapptabacaria.webtabaca.mapper.ProductMapper;
import com.projetoapptabacaria.webtabaca.model.product.Product;
import com.projetoapptabacaria.webtabaca.model.exception.ResourceNotFoundException;
import com.projetoapptabacaria.webtabaca.repositories.ProductRepository;
import com.projetoapptabacaria.webtabaca.shared.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
   @Autowired
    private ProductMapper productMapper;


    /**
     * Metodo para retornar lista de produtos.
     *
     * @return Lista de produtos.
     */
    public List<ProductDTO> findAll() {

        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(product -> productMapper.productToProductDto(product))
                .collect(Collectors.toList());
    }

    /**
     * Metodo para retornar o produto com seu id.
     *
     * @param id do produto que sera localizado.
     * @return produto caso seja encontrado.
     */
    public Optional<ProductDTO> findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        //exception
        if (product.isEmpty()) {
            throw new ResourceNotFoundException("Product not found!");
        }
        //Convertendo optional product em productDTO
        ProductDTO dto = productMapper.productToProductDto(product.get()); // coloca o .get por ser um optional
        //Criando e retornando um optional de ProductDTO
        return Optional.of(dto);
    }

    /**
     * Metodo para adicionar produto na lista.
     *
     * @param productDto que sera adicionado.
     * @return produto que foi adicionado na lista.
     */
    public ProductDTO addProduct(ProductDTO productDto) {

        //converter o Dto em product
        Product product = productMapper.toProduct(productDto);

        //salvar no banco
        product = productRepository.save(product);


        return productDto;
    }

    /**
     * Metodo para deletar um produto na lista.
     *
     * @param id que sera deletado.
     */
    public void deleteProduct(Long id) {
        //verificar se o produto existe
       Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ResourceNotFoundException("Delete is not possible product with id: " + id + " product not exist");
        }
        productRepository.deleteById(id);
    }

    /**
     * Metodo para atualizar um produto na lista.
     *
     * @param productDto que sera atualizado.
     * @return produto atualizado.
     */

    public ProductDTO updateProduct(Long id, ProductDTO productDto) {
        // Converter o DTO em produto
        Optional<Product> existingProductOptional = productRepository.findById(id);
        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();

            productMapper.updateProductFromDto(productDto, existingProduct);

            // Salvar o produto atualizado no banco de dados
            Product updatedProduct = productRepository.save(existingProduct);

            // Converter o produto atualizado de volta para DTO e retornar
            return productMapper.productToProductDto(updatedProduct);
        } else {
            throw new ResourceNotFoundException("Product not exist!");
        }
    }

}