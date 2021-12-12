package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.servlet.http.HttpServlet;

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
@ToString
@Table(name = "avatar")
public class Avatar extends HttpServlet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avt_id")
    private Integer avtId;

    @Column(name = "emp_id")
    private Integer emp_id;

    @NonNull
    @Column(name = "avatar_name", nullable = false, unique = true)
    private String avatarName;


    public Avatar(int emp_id, @NonNull String avatarName) {
        this.emp_id = emp_id;
        this.avatarName = avatarName;
    }

    //    Mapping to Employee
    @OneToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id"
            , updatable = false, insertable = false)
    @JsonManagedReference(value = "avatar")
    @ToString.Exclude
    private Employee employee;


}
