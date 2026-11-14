package com.stanthonys.backend;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String donorName;
    private String phone;
    private String email;
    private String item;
    private int quantity;

    @Column(name = "item_condition")
    private String itemCondition;

    @Column(length = 1000)
    private String notes;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;

    private Instant createdAt;

    protected Donation() {}

    public Donation(String donorName, String phone, String email, String item,
                    int quantity, String itemCondition, String notes) {
        this.donorName = donorName;
        this.phone = phone;
        this.email = email;
        this.item = item;
        this.quantity = quantity;
        this.itemCondition = itemCondition;
        this.notes = notes;
    }

    @PrePersist
    void onCreate() { createdAt = Instant.now(); }

    public Long getId() { return id; }
    public Status getStatus() { return status; }
}