package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 05/11/2021-10:34 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Indexed
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_code")
    private int roleCode;

    @NonNull
    @Field(termVector = TermVector.YES)
    @Column(name = "role_name", unique = true)
    @NotNull(message = "Role Name can't be null")
    @Size(min = 3 , max = 100, message = "{name.size}")
    private String roleName;

    @OneToMany(mappedBy = "role", orphanRemoval = true
            , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "role" )
    @ToString.Exclude
    private List<EmployeeRole> employeeRoles;

}
