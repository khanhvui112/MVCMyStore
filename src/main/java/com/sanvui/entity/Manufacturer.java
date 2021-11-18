package com.sanvui.entity;

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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Indexed
@Table(name = "Manufacturer")
public class Manufacturer {
    @Id
    @Column(name = "ma_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maId;

    @NonNull
    @Column(name = "ma_Name", unique = true)
    @NotNull(message = "Manufacturer name can't null.")
    @Size(min = 3, max = 100, message = "{name.size}")
    @Field(termVector = TermVector.YES)
    private String maName;


    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL, orphanRemoval = true, fetch =FetchType.LAZY)
    private List<Products> products;

    @Override
    public String toString() {
        return "Manufacturer{" +
                "maId=" + maId +
                ", maName='" + maName + '\'' +
                '}';
    }
}
