package com.sanvui.model.entity;

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
 * @created: 05/11/2021-10:03 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Indexed
@ToString
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "dep_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int depId;

    @NonNull
    @Column(name = "dep_name", columnDefinition = "nvarchar(100)", unique = true)
    @NotNull(message = "Department name can't be null.")
    @Size(min = 3, max = 100, message = "{dep.name.size}")
    @Field(termVector = TermVector.YES)
    private String depName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY
            , orphanRemoval = true)
    @JsonManagedReference(value = "department")
    @ToString.Exclude
    private List<Employee> employees;

}
