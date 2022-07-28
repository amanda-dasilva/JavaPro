package com.mentorama.jpaapi.controller;

import com.mentorama.jpaapi.entity.DepartmentEntity;
import com.mentorama.jpaapi.entity.ProductEntity;
import com.mentorama.jpaapi.repository.DepartmentRepository;
import com.mentorama.jpaapi.repository.ProductRepository;
import com.mentorama.jpaapi.vos.DepartmentVo;
import com.mentorama.jpaapi.vos.ProductVo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;
    private final DepartmentRepository departmentRepository;

    public ProductController(ProductRepository productRepository, DepartmentRepository departmentRepository) {
        this.productRepository = productRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public List<ProductVo> findAll(@RequestParam("page") Integer page,
                                   @RequestParam("pageSize") Integer pageSize) {
        return this.productRepository.findAll(PageRequest.of(page, pageSize,
                        Sort.by("id")))
                .stream()
                .map(this::toProductVo)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductEntity findById(@PathVariable("id") final Long id) {
        return this.productRepository.findById(id).orElse(null);
    }

    @GetMapping("description/{description}")
    public List<ProductEntity> findByDescription(@PathVariable("description")
                                                     final String description) {
        return this.productRepository.findByDescriptionContaining(description);
    }

    @PostMapping
    public void createNew(@RequestBody final ProductEntity product) {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setDescription(productEntity.getDescription());
        productEntity.setValue(productEntity.getValue());
        productEntity.setDepartment(
                departmentRepository.findById(product.getDepartment().getId())
                .orElse(null)
        );
        this.productRepository.save(productEntity);
    }

    @PutMapping
    public void update(@RequestBody final ProductEntity productEntity) {
        this.productRepository.save(productEntity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final Long id) {
        this.productRepository.deleteById(id);
    }

    private ProductVo toProductVo(ProductEntity entity) {
        ProductVo productVo = new ProductVo();
        productVo.setDepartment(toDepartmentVo(entity.getDepartment()));
        productVo.setDescription(entity.getDescription());
        productVo.setId(entity.getId());
        productVo.setValue(entity.getValue());

        return productVo;
    }

    private DepartmentVo toDepartmentVo(DepartmentEntity departmentEntity) {
        DepartmentVo departmentVo = new DepartmentVo();
        departmentVo.setId(departmentEntity.getId());
        departmentVo.setName(departmentEntity.getName());

        return departmentVo;

    }
}
