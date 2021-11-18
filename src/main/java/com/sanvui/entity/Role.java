package com.sanvui.entity;

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
@RequiredArgsConstructor
@Entity
@Indexed
@Table(name = "Role")
public class Role {
    @Id
    @Column(name = "role_Code")
    private String roleCode;

    @NonNull
    @Field(termVector = TermVector.YES)
    @Column(name = "role_Name", unique = true)
    @NotNull(message = "Role Name can't be null")
    @Size(min = 3 , max = 100, message = "{name.size}")
    private String roleName;

    @OneToMany(mappedBy = "role", orphanRemoval = true
            , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employeeList;
    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + roleCode +
                ", permissionName='" + roleName + '\'' +
                '}';
    }
}
