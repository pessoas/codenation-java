package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Candidate {

    @EmbeddedId
    private CandidateId candidateId;

    @NotNull
    @NotBlank
    private Long status;

    @CreatedDate
    private LocalDateTime createdAt;

    public CandidateId getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(CandidateId candidateId) {
        this.candidateId = candidateId;
    }

    public @NotNull @NotBlank Long getStatus() {
        return status;
    }

    public void setStatus(@NotNull @NotBlank Long status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime created_at) {
        this.createdAt = created_at;
    }
}
