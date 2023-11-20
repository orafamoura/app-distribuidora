package com.projetoapptabacaria.webtabaca.view.controller;


import com.projetoapptabacaria.webtabaca.mapper.ProductMapper;
import com.projetoapptabacaria.webtabaca.services.ProductService;
import com.projetoapptabacaria.webtabaca.shared.ProductDTO;
import com.projetoapptabacaria.webtabaca.view.controller.model.product.ProductRequest;
import com.projetoapptabacaria.webtabaca.view.controller.model.product.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private  ProductService productService;
    @Autowired
    private  ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        //
        List<ProductDTO> products = productService.findAll();

        List<ProductResponse> response = products.stream()
                .map(productDTO -> productMapper.productDtoToProductResponse(productDTO))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductResponse>> findById(@PathVariable Long id){
        //obtendo produto pelo id
        Optional<ProductDTO> products = productService.findById(id);

        if (products.isPresent()) {
            // Se o produto existir, realizar o mapeamento
            ProductResponse productResponse = productMapper.productDtoToProductResponse(products.get());
            return new ResponseEntity<>(Optional.of(productResponse), HttpStatus.OK);
        } else {
            // Se o produto não existir NOT_FOUND
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productReq){ //request body ele tenta converter o que escrevemos em JSON em product

        ProductDTO productDto = productMapper.productRequestToProductDTO(productReq);

        productDto = productService.addProduct(productDto);

        ProductResponse productResponse = productMapper.productDtoToProductResponse(productDto);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productReq, @PathVariable Long id){ //request body pra converter o JSON em product
        ProductDTO productDto = productMapper.productRequestToProductDTO(productReq);

        productDto = productService.updateProduct(id, productDto);

        ProductResponse productResponse = productMapper.productDtoToProductResponse(productDto);

        return new ResponseEntity<>(productResponse,HttpStatus.OK);

    }
}
