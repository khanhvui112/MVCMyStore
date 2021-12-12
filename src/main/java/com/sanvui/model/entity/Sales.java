package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sanvui.model.dao.ValidAfterDate;
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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@ToString
@Table(name = "sales")
@Indexed
public class Sales {

    @Id
    @Column(name = "sale_id")
    private String saleId;

    @NonNull
    @Column(name = "sales_type", columnDefinition = "nvarchar(50)")
    @NotNull(message = "Sales type can't null.")
    @Size(max = 50, message = "{name.size}")
    @Field(termVector = TermVector.YES)
    private String salesType;


    @NonNull
    @Column(name = "start_date", columnDefinition = "date")
    @NotNull(message = "Start date cannot null.")
    private LocalDate startDate;

    @NonNull
    @Column(name = "end_date", columnDefinition = "date")
    @NotNull(message = "End date cannot null.")
    private LocalDate endDate;

    @NonNull
    @Column(name = "min_sales", columnDefinition = "decimal(10,2)")
    private Double minSales;

    @NonNull
    @Column(name = "max_sales", columnDefinition = "decimal(10,2)")
    private Double maxSales;

    @NonNull
    @Column(name = "status", columnDefinition = "bit")
    @NotNull(message = "Status can't null.")
    @Range(min = 0, max = 1, message = "{range.size}")
    @Field(termVector = TermVector.YES)
    private int status;


}
