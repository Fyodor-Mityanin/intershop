package ru.yandex.practicum.intershop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "order_time", nullable = false)
    private LocalDateTime orderTime;

    @Column(name = "customer", nullable = false, length = Integer.MAX_VALUE)
    private String customer;

    @Column(name = "session", nullable = false, length = Integer.MAX_VALUE)
    private String session;

    @Column(name = "status", nullable = false, length = Integer.MAX_VALUE)
    private String status;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new LinkedHashSet<>();
}