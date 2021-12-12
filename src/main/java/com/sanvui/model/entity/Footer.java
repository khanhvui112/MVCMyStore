package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 17/11/2021-2:11 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "footer")
public class Footer {
    @Id
    @Column(name = "footer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int footerId;

    @Column(name = "footer_name", columnDefinition = "nvarchar(255)", nullable = false)
    @Size(max = 255)
    @NotNull(message = "Vui lòng nhập tên footer.")
    private String footerName;

    @Column(name = "status", columnDefinition = "bit")
    @NotNull(message = "Trạng thái không thể null.")
    private int status;

    @Column(name = "create_date", columnDefinition = "datetime")
    private LocalDateTime create_Date;

    public Footer(String footerName, int status, LocalDateTime create_Date) {
        this.footerName = footerName;
        this.status = status;
        this.create_Date = create_Date;
    }

    @OneToMany(mappedBy = "footer", orphanRemoval = true
            , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "footerDetails")
    @ToString.Exclude
    private List<FooterDetail> footerDetails;
}
