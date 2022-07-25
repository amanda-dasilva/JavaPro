package com.example.product.menu.entity;


import javax.persistence.*;

@Entity
@Table(name= "orders")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @OneToOne
    @JoinColumn(name="getId")
    private Product product;
    private Integer qtyProduct;
    private Double discount;

    public OrderItem() {
        super();
        this.orderId = null;
        this.product = null;
        this.qtyProduct = null;
        this.discount = null;
    }

    public OrderItem(Long orderId, Product product, Integer qtyProduct, Double discount) {
        this.orderId = orderId;
        this.product = product;
        this.qtyProduct = qtyProduct;
        this.discount = discount;
    }
    public Integer qtyProduct() {
        return product.getQtyOfProductStock(qtyProduct);
    }
    public Double totalPrice() {
        return product.getPriceWithDiscount(discount) * qtyProduct();
    }

    public Long getOrderId() {
        return orderId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQtyProduct() {
        return qtyProduct;
    }

    public void setQtyProduct(Integer qtyProduct) {
        this.qtyProduct = qtyProduct;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OrderItem updateWith(OrderItem orderItem) {
        return new OrderItem(
                this.orderId,
                orderItem.product,
                orderItem.qtyProduct,
                orderItem.discount
        );
    }
}
