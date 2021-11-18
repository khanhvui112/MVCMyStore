package com.sanvui.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

/**
 * @author: VuiSK
 * @created: 11/11/2021-11:39 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Avatar")
public class Avatar {

    @Id
    @Column(name = "emp_Id")
    private int emp_Id;

    @NonNull
    @Column(name = "avatar_Name", nullable = false, unique = true)
    private String avatarName;


    public Avatar(int emp_Id, @NonNull String avatarName) {
        this.emp_Id = emp_Id;
        this.avatarName = avatarName;
    }

    //    Mapping to Employee
    @OneToOne
    @JoinColumn(name = "emp_Id", referencedColumnName = "emp_Id"
            , updatable = false, insertable = false)
    @JsonManagedReference
    private Employee employee;

    @Override
    public String toString() {
        return "Avatar{" +
                "emp_Id=" + emp_Id +
                ", avatarName='" + avatarName + '\'' +
                '}';
    }
}
