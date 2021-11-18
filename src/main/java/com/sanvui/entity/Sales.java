package com.sanvui.entity;

import com.sanvui.dao.ValidAfterDate;
import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 06/11/2021-10:08 AM
 * @mailto: sanvankhanh@gmail.com
 */
@ValidAfterDate
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@Entity
@Table(name = "Sales")
@Indexed
public class Sales {

    @Id
    @Column(name = "sale_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int saleId;

    @NonNull
    @Column(name = "sales_Type", columnDefinition = "nvarchar(50)")
    @NotNull(message = "Sales type can't null.")
    @Size(max = 50, message = "{name.size}")
    @Field(termVector = TermVector.YES)
    private String salesType;


    @NonNull
    @Column(name = "start_Date", columnDefinition = "date")
    @NotNull(message = "Start date cannot null.")
    private LocalDate startDate;

    @NonNull
    @Column(name = "end_Date", columnDefinition = "date")
    @NotNull(message = "End date cannot null.")
    private LocalDate endDate;

    @NonNull
    @Column(name = "min_Sales", columnDefinition = "decimal(10,2)")
    private double minSales;

    @NonNull
    @Column(name = "max_Sales", columnDefinition = "decimal(10,2)")
    private double maxSales;

    @NonNull
    @Column(name = "status", columnDefinition = "bit")
    @NotNull(message = "Status can't null.")
    @Range(min = 0,max = 1, message = "{range.size}")
    @Field(termVector = TermVector.YES)
    private int status;

//    mapper Products by sales
    @OneToMany(mappedBy = "sales", cascade = CascadeType.ALL
            , orphanRemoval = true, fetch =FetchType.LAZY)
    private List<Products> products;


    @Override
    public String toString() {
        return "Sales{" +
                "salesId=" + saleId +
                ", salesType='" + salesType + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", minSales=" + minSales +
                ", maxSales=" + maxSales +
                ", status=" + status +
                '}';
    }
}
