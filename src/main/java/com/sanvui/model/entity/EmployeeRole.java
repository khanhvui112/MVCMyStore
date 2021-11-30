package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;

/**
 * @author: VuiSK
 * @created: 30/11/2021-7:17 AM
 * @mailto: sanvankhanh@gmail.com
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "employeerole")
public class EmployeeRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_role_id")
    private int employeeRoleId;

    private Integer emp_id;

    private Integer role_code;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id"
            , insertable = false, updatable = false, nullable = false)
    @JsonBackReference(value = "employeeRoles" )
    @ToString.Exclude
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_code", referencedColumnName = "role_code"
            , insertable = false, updatable = false, nullable = false)
    @JsonBackReference(value = "role" )
    @ToString.Exclude
    private Role role;

}
