package com.sanvui.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

@Entity
@Table( name = "FooterDetail")
public class FooterDetail {
    @Id
    @Column(name = "footer_Detail_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int footerDetailId;

    @Column(name = "footer_Id", nullable = false)
    private int footer_Id;

    @Column(name = "footer_Detail_Name", columnDefinition = "nvarchar(50)")
    @Size(max = 50, message = "Vui lòng nhập tên footer nhỏ hơn 50")
    @NotNull(message = "Vui lòng nhập tên footer")
    private String footerDetailName;

    @Column(name = "footer_Detail_Link",columnDefinition = "nvarchar(255)")
    @Size(max = 255, message = "Vui lòng nhập link footer nhỏ hơn 255")
    @NotNull(message = "Vui lòng nhập link footer")
    private String footerDetailLink;

    @Column(name = "create_Date", columnDefinition = "datetime")
    private LocalDateTime create_Date;

    @Column(name = "status", columnDefinition = "bit")
    @NotNull(message = "Trạng thái không thể null.")
    private int status;

    public FooterDetail(int footer_Id, String footerDetailName, String footerDetailLink, LocalDateTime create_Date, int status) {
        this.footer_Id = footer_Id;
        this.footerDetailName = footerDetailName;
        this.footerDetailLink = footerDetailLink;
        this.create_Date = create_Date;
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "footer_Id", columnDefinition = "footer_Id"
            , insertable = false, updatable = false)
    @JsonBackReference
    private Footer footer;

    @Override
    public String toString() {
        return "FooterDetail{" +
                "footerDetailId=" + footerDetailId +
                ", footer_Id=" + footer_Id +
                ", footerDetailName='" + footerDetailName + '\'' +
                ", footerDetailLink='" + footerDetailLink + '\'' +
                ", create_Date=" + create_Date +
                ", status=" + status +
                '}';
    }
}
