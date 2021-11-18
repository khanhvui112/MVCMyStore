package com.sanvui.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 07/11/2021-6:55 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Setter @Getter @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@Entity
@Table(name = "Interview")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_id")
    private int interviewId;

    @NonNull
    @Column(name = "roomName",columnDefinition = "varchar(10)",nullable = false)
    @Size(min = 1, max = 10, message = "{name.size}")
    private String roomName;

    @NonNull
    @Column(name = "emp_Id", nullable = false)
    private int emp_Id;

    @NonNull
    @Column(name = "comments",columnDefinition = "nvarchar(255)", nullable = false)
    @Size(max = 100, message = "{name.max}")
    private String comments;

    @NonNull
    @Column(name = "remark", columnDefinition = "nvarchar(255)")
    private String remark;

//    mapper to Candidates
    @OneToMany(mappedBy = "interview", orphanRemoval = true, fetch =FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Candidates> candidatesList;

    @ManyToOne
    @JoinColumn(name = "emp_Id",referencedColumnName = "emp_Id"
            , insertable = false, updatable = false)
    private Employee employee;
    @Override
    public String toString() {
        return "Interview{" +
                "interviewId=" + interviewId +
                ", roomName='" + roomName + '\'' +
                ", emp_Id=" + emp_Id +
                ", comments='" + comments + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
