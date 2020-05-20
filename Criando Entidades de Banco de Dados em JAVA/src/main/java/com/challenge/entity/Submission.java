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
public class Submission {

    @EmbeddedId
    private SubmissionId submissionId;

    @NotNull
    @NotBlank
    private Double score;

    @CreatedDate
    private LocalDateTime createdAt;

    public SubmissionId getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(SubmissionId submissionId) {
        this.submissionId = submissionId;
    }

    public @NotNull @NotBlank Double getScore() {
        return score;
    }

    public void setScore(@NotNull @NotBlank Double score) {
        this.score = score;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime created_at) {
        this.createdAt = created_at;
    }
}
