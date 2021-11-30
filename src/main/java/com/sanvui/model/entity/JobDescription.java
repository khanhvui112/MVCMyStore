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
 * @created: 04/11/2021-1:11 PM
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
@Table(name = "jobdescription")
public class JobDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_description_id")
    private int job_description_id;

    @Column(name = "job_id", nullable = false)
    private int job_id;

    @NonNull
    @Column(name = "description", columnDefinition = "nvarchar(1000)")
    @Size(min = 3, message = "Input description bigger than 3")
    @Field(termVector = TermVector.YES)
    private String description;

    @NonNull
    @Column(name = "comment", columnDefinition = "nvarchar(2000)")
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id", referencedColumnName = "job_id"
            , insertable = false, updatable = false)
    @JsonBackReference(value = "jobDescriptions")
    @ToString.Exclude
    private Jobs jobs;

}
