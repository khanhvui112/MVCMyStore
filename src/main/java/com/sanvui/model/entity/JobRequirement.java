package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author: VuiSK
 * @created: 04/11/2021-1:01 PM
 * @mailto: sanvankhanh@gmail.com
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@ToString
@Indexed
@Table(name = "jobrequirement")
public class JobRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_requirement_id")
    private int job_requirement_id;

    @NonNull
    @Column(name = "job_id", nullable = false)
    private int job_id;

    @NonNull
    @Column(name = "requirement", columnDefinition = "nvarchar(1000)")
    @Size(min = 3, message = "Input requirement bigger than 3")
    @Field(termVector = TermVector.YES)
    private String requirement;

    @NonNull
    @Column(name = "comment", columnDefinition = "nvarchar(2000)")
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id", referencedColumnName = "job_id"
            , insertable = false, updatable = false)
    @JsonBackReference(value = "jobRequirement")
    @ToString.Exclude
    private Jobs jobs;

}
