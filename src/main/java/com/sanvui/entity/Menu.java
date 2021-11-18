package com.sanvui.entity;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * @author: VuiSK
 * @created: 07/11/2021-11:43 AM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@Entity
@Table(name = "Menu")
public class Menu {
    @Id
    @Column(name = "me_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int meId;

    @NonNull
    @Column(name = "me_Name", unique = true, columnDefinition = "nvarchar(50)")
    @Size(max = 50, message = "{name.max}")
    private String meName;

    @NonNull
    @Column(name = "me_Link", unique = true, columnDefinition = "nvarchar(255)")
    @Size(max = 255, message = "{name.max}")
    private String meLink;

    @NonNull
    @Column(name = "create_Date", columnDefinition = "datetime")
    private LocalDateTime createDate;

    @NonNull
    @Column(name = "status", columnDefinition = "int", nullable = false)
    @Range(min = 0, max = 1, message = "{range.max}")
    private int status;

    @NonNull
    @Column(name = "emp_Id", updatable = false)
    private int emp_Id;
    
//    mapper to Employee
    @ManyToOne
    @JoinColumn(name = "emp_Id", referencedColumnName = "emp_Id"
            , insertable = false, updatable = false)
    private Employee employee;

    @Override
    public String toString() {
        return "Menu{" +
                "meId=" + meId +
                ", meName='" + meName + '\'' +
                ", meLink='" + meLink + '\'' +
                ", createDate=" + createDate +
                ", status=" + status +
                '}';
    }
}
