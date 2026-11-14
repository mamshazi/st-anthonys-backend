package com.stanthonys.backend;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class SubmissionController {

    record DonationRequest(
            @NotBlank @Size(max = 120) String name,
            @NotBlank @Size(max = 30) String phone,
            @NotBlank @Email @Size(max = 150) String email,
            @NotBlank @Size(max = 150) String item,
            @Min(1) @Max(100000) int quantity,
            @NotBlank @Size(max = 30) String condition,
            @Size(max = 1000) String notes) {}

    record ApplicationRequest(
            @NotBlank @Size(max = 30) String type,
            @NotBlank @Size(max = 150) String name,
            @NotBlank @Size(max = 30) String phone,
            @Email @Size(max = 150) String email,
            @NotBlank @Size(max = 200) String need,
            @NotBlank @Size(max = 2000) String circumstances) {}

    private final DonationRepository donations;
    private final AssistanceRequestRepository requests;

    public SubmissionController(DonationRepository donations, AssistanceRequestRepository requests) {
        this.donations = donations;
        this.requests = requests;
    }

    @PostMapping("/donations")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> donate(@Valid @RequestBody DonationRequest r) {
        Donation saved = donations.save(new Donation(
                r.name(), r.phone(), r.email(), r.item(), r.quantity(), r.condition(), r.notes()));
        return Map.of("id", saved.getId(), "status", saved.getStatus());
    }

    @PostMapping("/applications")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> apply(@Valid @RequestBody ApplicationRequest r) {
        AssistanceRequest saved = requests.save(new AssistanceRequest(
                r.type(), r.name(), r.phone(), r.email(), r.need(), r.circumstances()));
        return Map.of("id", saved.getId(), "status", saved.getStatus());
    }
}