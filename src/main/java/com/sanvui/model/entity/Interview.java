package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 07/11/2021-6:55 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@ToString
@Table(name = "interview")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_id")
    private int interviewId;

    @NonNull
    @Column(name = "room_name", columnDefinition = "varchar(10)", nullable = false)
    @Size(min = 1, max = 10, message = "{name.size}")
    private String roomName;

    @NonNull
    @Column(name = "emp_id", nullable = false)
    private int emp_id;

    @NonNull
    @Column(name = "comments", columnDefinition = "nvarchar(255)", nullable = false)
    @Size(max = 100, message = "{name.max}")
    private String comments;

    @NonNull
    @Column(name = "remark", columnDefinition = "nvarchar(255)")
    private String remark;

    //    mapper to Candidates
    @OneToMany(mappedBy = "interview", orphanRemoval = true
            , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "interview")
    @ToString.Exclude
    private List<Candidates> candidatesList;

    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id"
            , insertable = false, updatable = false)
    @JsonBackReference(value = "interview")
    @ToString.Exclude
    private Employee employee;
}
