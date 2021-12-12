package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "entrytest")
public class Entrytest {
    @Id
    @Column(name = "en_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enId;

    @NonNull
    @Column(name = "ca_id", nullable = false)
    private int ca_id;

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
    @JoinColumn(name = "ca_id", referencedColumnName = "ca_id"
            , insertable = false, updatable = false)
    @JsonBackReference(value = "candidates")
    @ToString.Exclude
    private Candidates candidates;
}
