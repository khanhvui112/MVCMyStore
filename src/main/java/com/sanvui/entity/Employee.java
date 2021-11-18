package com.sanvui.entity;

import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 07/11/2021-11:57 AM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Indexed
@Table(name = "Employee")
public class Employee {
    @Id
    @Column(name = "emp_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;

    @NonNull
    @Field(termVector = TermVector.YES)
    @Column(name = "first_Name", columnDefinition = "nvarchar(30)")
    @Size(max = 30, message = "{name.max}")
    private String firstName;

    @NonNull
    @Field(termVector = TermVector.YES)
    @Column(name = "last_Name", columnDefinition = "nvarchar(30)")
    @Size(max = 30, message = "{name.max}")
    private String lastName;

    @Field(termVector = TermVector.YES)
    @Column(name = "user_Name", columnDefinition = "varchar(100)", unique = true, nullable = false)
    @NotNull(message = "{username.null}")
    @Size(min = 6, max = 100, message = "{username.size}")
    private String userName;

    @Column(name = "password", columnDefinition = "varchar(100)", nullable = false)
    @Size(min = 8, max = 100, message = "{password.size}")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,100}$"
            , message = "{password.regexp}")
    @NotNull(message = "{password.null}")
    private String password;

    @NonNull
    @Field(termVector = TermVector.YES)
    @Column(name = "address", columnDefinition = "nvarchar(200)")
    private String address;

    @Field(termVector = TermVector.YES)
    @Email(message = "{email}")
    @NotNull(message = "{email.null}")
    @Size(min = 3, max = 100, message = "Email cần lớn hơn 3 và nhỏ hơn 100")
    @Column(name = "email", columnDefinition = "varchar(100)", unique = true, nullable = false)
    private String email;

    @NotNull(message = "{phone.null}")
    @Field(termVector = TermVector.YES)
    @Size(min = 10, max = 12, message = "{phone.size}")
    @Pattern(regexp = "^([\\+84|84|0]+(3|5|7|8|9|1[2|6|8|9]))+([0-9]{8})$", message = "{phone.valid}")
    @Column(name = "phone", columnDefinition = "varchar(12)", unique = true, nullable = false)
    private String phone;

    @NonNull
    @Column(name = "role_Code")
    private String role_Code;

    @NonNull
    @Column(name = "dep_Id")
    private Integer dep_Id;

    @Column(name = "date_Of_Birth", columnDefinition = "date", nullable = false)
    @Past(message = "{dateOfBirth.past}")
    @NotNull(message = "{dateOfBirth.null}")
    private LocalDate dateOfBirth;

    @NonNull
    @Column(name = "gender", columnDefinition = "nvarchar(10)", nullable = false)
    @NotNull(message = "Vui lòng chọn giới tính.")
    @Size(max = 10, message = "Length can smaller than 10.")
//    @NotNull(message = "Gender can't be null.")
    @Pattern(regexp = "^(female|male|Nam|Nữ)$", message = "Please check gender again.")
    private String gender;

    //    map to avatar
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL
            , fetch = FetchType.LAZY, orphanRemoval = true)
    private Avatar avatar;

    //    Mapping to department by dep_Id
    @ManyToOne
    @JoinColumn(name = "dep_Id", referencedColumnName = "dep_Id"
            , insertable = false, updatable = false)
    private Department department;

    //    Mapping to Menu
    @OneToMany(mappedBy = "employee", orphanRemoval = true
            , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Menu> menuList;

    //    Mapper to Order by employee
    @OneToMany(mappedBy = "employee", orphanRemoval = true
            , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Orders> orders;

    //    Mapping to Role by coleCode
    @ManyToOne
    @JoinColumn(name = "role_Code", columnDefinition = "role_Code"
            , insertable = false, updatable = false)
    private Role role;

    //    Mapper to Payments by employee
    @OneToMany(mappedBy = "employee", orphanRemoval = true, fetch = FetchType.LAZY
            , cascade = CascadeType.ALL)
    private List<Payment> payments;

    //    mapper to Rates
    @OneToMany(mappedBy = "employee", orphanRemoval = true, fetch = FetchType.LAZY
            , cascade = CascadeType.ALL)
    private List<Rates> ratesList;

    //    mapper to Interview
    @OneToMany(mappedBy = "employee", orphanRemoval = true, fetch = FetchType.LAZY
            , cascade = CascadeType.ALL)
    private List<Interview> interviewList;

    //    mapper to Salary
    @OneToMany(mappedBy = "employee", orphanRemoval = true
            , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Salary> salaryList;

    public Employee(@NonNull String firstName, @NonNull String lastName, @NonNull String userName, @NonNull String password, @NonNull String address, @NonNull String email, @NonNull String phone, int dep_Id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.dep_Id = dep_Id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", role_Code='" + role_Code + '\'' +
                ", dep_Id=" + dep_Id +
                ", role=" + role +
                '}';
    }
}
