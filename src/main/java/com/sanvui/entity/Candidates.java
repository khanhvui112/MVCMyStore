package com.sanvui.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 07/11/2021-7:16 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name ="Candidates")
public class Candidates {
    @Id
    @Column(name = "ca_Id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int caId;

    @NonNull
    @Column(name = "date_Of_Birth")
    @Past(message = "Please input date of birth smaller date present")
    private LocalDate dateOfBirth;

    @NonNull
    @Column(name = "full_Name", columnDefinition = "nvarchar(50)", nullable = false)
    @Size(min = 3, max = 50, message = "{name.size}")
    @NotNull(message = "Name can't be null.")
    private String fullName;

    @NonNull
    @Column(name = "address", columnDefinition = "nvarchar(100)" )
    @Size(max = 100, message = "{name.max}")
    private String address;

    @NonNull
    @Size(max = 12, message = "{name.max}")
    @Pattern(regexp = "^([\\+84|84|0]+(3|5|7|8|9|1[2|6|8|9]))+([0-9]{8})$", message = "{phone.valid}")
    @Column(name = "phone", columnDefinition = "varchar(12)", unique = true, nullable = false)
    private String phone;

    @NonNull
    @Email(message = "{email}")
    @NotNull(message = "{email.null}")
    @Column(name = "email", columnDefinition = "varchar(100)", unique = true, nullable = false)
    private String email;

    @NonNull
    @Column(name = "gender", columnDefinition = "nvarchar(10)")
    @Pattern(regexp = "^(male|female|nam|Nữ)", message = "Gender only accept male female nam nữ")
    private String gender;

    @NonNull
    @NotNull(message = "skill can't be null.")
    @Column(name = "skill", columnDefinition = "nvarchar(200)", nullable = false)
    @Size(max = 200, message = "{name.max}")
    private String skill;

    @NonNull
    @Column(name = "foreign_Language", columnDefinition = "nvarchar(100)")
    @Size(max = 100, message = "{name.max}")
    private String foreignLanguage;

    @NonNull
    @Column(name = "interview_id",nullable = false)
    private int interview_Id;

//    mapper to EntryTest
    @OneToMany(mappedBy = "candidates",orphanRemoval = true
            , fetch =FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Entrytest> entrytestList;

//    mapping to Interview
    @ManyToOne
    @JoinColumn(name = "interview_Id", columnDefinition = "interview_Id"
            , insertable = false, updatable = false)
    private Interview interview;

    @Override
    public String toString() {
        return "Candidates{" +
                "caId=" + caId +
                ", dateOfBirth=" + dateOfBirth +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", skill='" + skill + '\'' +
                ", foreignLanguage='" + foreignLanguage + '\'' +
                '}';
    }
}
