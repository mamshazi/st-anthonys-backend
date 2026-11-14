package com.stanthonys.backend;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "assistance_requests")
public class AssistanceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String applicantType;
    private String applicantName;
    private String phone;
    private String email;
    private String need;

    @Column(length = 2000)
    private String circumstances;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;

    @Column(length = 2000)
    private String staffNotes;

    private Instant createdAt;

    protected AssistanceRequest() {}

    public AssistanceRequest(String applicantType, String applicantName, String phone,
                             String email, String need, String circumstances) {
        this.applicantType = applicantType;
        this.applicantName = applicantName;
        this.phone = phone;
        this.email = email;
        this.need = need;
        this.circumstances = circumstances;
    }

    @PrePersist
    void onCreate() { createdAt = Instant.now(); }

    public Long getId() { return id; }
    public Status getStatus() { return status; }
}