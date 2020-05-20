package com.challenge.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Embeddable;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
@EntityListeners(AuditingEntityListener.class)
public class CandidateId implements Serializable {

    @ManyToOne
    @NotNull
    private User user_id;

    @ManyToOne
    @NotNull
    private Acceleration acceleration_id;

    @ManyToOne
    @NotNull
    private Company company_id;

}
