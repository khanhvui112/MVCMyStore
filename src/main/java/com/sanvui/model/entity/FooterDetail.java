package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * @author: VuiSK
 * @created: 17/11/2021-2:16 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "footerdetail")
public class FooterDetail {
    @Id
    @Column(name = "footer_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int footerDetailId;

    @Column(name = "footer_id", nullable = false)
    private int footer_id;

    @Column(name = "footer_detail_name", columnDefinition = "nvarchar(50)")
    @Size(max = 50, message = "Vui lòng nhập tên footer nhỏ hơn 50")
    @NotNull(message = "Vui lòng nhập tên footer")
    private String footerDetailName;

    @Column(name = "footer_detail_link", columnDefinition = "nvarchar(255)")
    @Size(max = 255, message = "Vui lòng nhập link footer nhỏ hơn 255")
    @NotNull(message = "Vui lòng nhập link footer")
    private String footerDetailLink;

    @Column(name = "create_date", columnDefinition = "datetime")
    private LocalDateTime createDate;

    @Column(name = "status", columnDefinition = "bit")
    @NotNull(message = "Trạng thái không thể null.")
    private int status;

    @ManyToOne
    @JoinColumn(name = "footer_Id", columnDefinition = "footer_Id"
            , insertable = false, updatable = false)
    @JsonBackReference(value = "footerDetails")
    @ToString.Exclude
    private Footer footer;

}
