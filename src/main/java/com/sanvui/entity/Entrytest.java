package com.sanvui.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * @author: VuiSK
 * @created: 07/11/2021-7:08 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Setter @Getter @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@Entity
@Table(name = "Entrytest")
public class Entrytest {
    @Id
    @Column(name = "en_Id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int enId;

    @NonNull
    @Column(name = "ca_Id", nullable = false)
    private int ca_Id;

    @NonNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NonNull
    @Column(name = "result")
    @Pattern(regexp = "^(pass|fail)$")
    private String result;

    @NonNull
    @Column(name = "comments")
    @Size(max = 255, message = "{name.max}")
    private String comments;

    @NonNull
    @Column(name = "remark")
    @Size(max = 255, message = "{name.max}")
    private String remark;

//    mapping to Candidate
    @ManyToOne
    @JoinColumn(name = "ca_Id", referencedColumnName = "ca_Id", insertable = false, updatable = false)
    private Candidates candidates;

    @Override
    public String toString() {
        return "Entrytest{" +
                "enId=" + enId +
                ", ca_Id=" + ca_Id +
                ", date=" + date +
                ", result='" + result + '\'' +
                ", comments='" + comments + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
