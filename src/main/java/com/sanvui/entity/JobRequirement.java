package com.sanvui.entity;

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
@Indexed
@Table(name = "JobRequirement")
public class JobRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_Requirement_Id")
    private int job_Requirement_Id;

    @NonNull
    @Column(name = "job_Id", nullable = false)
    private int job_Id;

    @NonNull
    @Column(name = "requirement", columnDefinition = "nvarchar(1000)")
    @Size(min = 3, message = "Input requirement bigger than 3")
    @Field(termVector = TermVector.YES)
    private String requirement;

    @NonNull
    @Column(name = "comment", columnDefinition = "nvarchar(2000)")
    private String comment;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_Id", referencedColumnName = "job_Id", insertable = false, updatable = false)
    private Jobs jobs;

    @Override
    public String toString() {
        return "JobRequirement{" +
                "job_Requirement_Id=" + job_Requirement_Id +
                ", job_Id=" + job_Id +
                ", requirement='" + requirement + '\'' +
                ", comment='" + comment + '\'' +
                ", jobs=" + jobs +
                '}';
    }
}
