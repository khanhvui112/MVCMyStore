package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sanvui.model.dao.ValidAfterDate;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 04/11/2021-11:31 AM
 * @mailto: sanvankhanh@gmail.com
 */
@ValidAfterDate
@Entity
@Table(name = "jobs")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Indexed
public class Jobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private int jobId;

    @NonNull
    @Column(name = "job_name", columnDefinition = "nvarchar(255)", unique = true)
    @NotNull(message = "{job.name.null}")
    @Size(min = 3, message = "{job.name.min}")
    @Field(termVector = TermVector.YES)
    private String jobName;

    @NonNull
    @Column(name = "quantity")
    @Range(min = 0, message = "Please input quantity bigger than 0")
    private int quantity;

    @NonNull
    @Column(name = "start_date", columnDefinition = "date")
    @NotNull(message = "Start Date can't be empty")
    private LocalDate startDate;

    @NonNull
    @Column(name = "end_date", columnDefinition = "date")
    @NotNull(message = "End Date can't be empty")
    private LocalDate endDate;

    @NonNull
    @Column(name = "location", columnDefinition = "nvarchar(255)")
    @NotNull(message = "{job.location.null}")
    @Size(min = 3, message = "{job.location.min}")
    @Field(termVector = TermVector.YES)
    private String location;

    @NonNull
    @Column(name = "salary", columnDefinition = "decimal(15,2)")
    private double salary;

    @NonNull
    @Column(name = "comment", columnDefinition = "nvarchar(2000)")
    @Size(max = 2000, message = "{job.comment.max}")
    private String comment;


    @OneToMany(mappedBy = "jobs", orphanRemoval = true
            , cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference(value = "jobRequirement")
    @ToString.Exclude
    private List<JobRequirement> jobRequirement;


    @OneToMany(mappedBy = "jobs", orphanRemoval = true, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference(value = "jobDescriptions")
    @ToString.Exclude
    private List<JobDescription> jobDescriptions;

}
