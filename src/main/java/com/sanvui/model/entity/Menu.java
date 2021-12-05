package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sanvui.model.dto.CategoryDto;
import com.sanvui.model.dto.resp.MenuRespDto;
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
@SqlResultSetMapping(
        name = "menuCustomResultMapping",
        classes = {
                @ConstructorResult(
                        targetClass = MenuRespDto.class,
                        columns = {
                                @ColumnResult(name = "me_id", type = Integer.class),
                                @ColumnResult(name = "me_name", type = String.class),
                                @ColumnResult(name = "me_link", type = String.class),
                                @ColumnResult(name = "status", type = Integer.class),
                        }
                )
        }
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@Entity
@ToString
@Table(name = "menu")
public class Menu {
    @Id
    @Column(name = "me_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int meId;

    @NonNull
    @Column(name = "me_name", unique = true, columnDefinition = "nvarchar(50)")
    @Size(max = 50, message = "{name.max}")
    private String meName;

    @NonNull
    @Column(name = "me_link", unique = true, columnDefinition = "nvarchar(255)")
    @Size(max = 255, message = "{name.max}")
    private String meLink;

    @NonNull
    @Column(name = "create_date", columnDefinition = "datetime")
    private LocalDateTime createDate;

    @NonNull
    @Column(name = "status", columnDefinition = "int", nullable = false)
    @Range(min = 0, max = 1, message = "{range.max}")
    private int status;

    @NonNull
    @Column(name = "emp_id", nullable = false)
    private int emp_id;
    
//    mapper to Employee
    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id"
            , insertable = false, updatable = false)
    @JsonBackReference(value = "menu" )
    @ToString.Exclude
    private Employee employee;

}
