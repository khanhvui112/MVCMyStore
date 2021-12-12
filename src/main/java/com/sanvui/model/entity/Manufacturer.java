package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sanvui.model.dto.resp.ColorResponseDto;
import com.sanvui.model.dto.resp.ManufacturerResponseDto;
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
 * @created: 06/11/2021-9:56 AM
 * @mailto: sanvankhanh@gmail.com
 */
@SqlResultSetMapping(
        name = "manufacturerCustomResultMapping",
        classes = {
                @ConstructorResult(
                        targetClass = ManufacturerResponseDto.class,
                        columns = {
                                @ColumnResult(name = "ma_id", type = Integer.class),
                                @ColumnResult(name = "ma_name", type = String.class),
                        }
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@ToString
@Indexed
@Table(name = "manufacturer")
public class Manufacturer {
    @Id
    @Column(name = "ma_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maId;

    @NonNull
    @Column(name = "ma_name", unique = true)
    @NotNull(message = "Manufacturer name can't null.")
    @Size(min = 3, max = 100, message = "{name.size}")
    @Field(termVector = TermVector.YES)
    private String maName;


    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL
            , orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "manufacturer")
    @ToString.Exclude
    private List<Products> products;

}
