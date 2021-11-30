package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 06/11/2021-8:51 AM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "color")
public class Color {
    @Id
    @Column(name="color_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int colorId;

    @Column(name = "color_name", unique = true, columnDefinition = "nvarchar(30)")
    @Size(min = 3, max = 30, message = "{name.size}")
    private String colorName;

    @OneToMany(mappedBy = "color", cascade = CascadeType.ALL
            , orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value="color")
    @ToString.Exclude
    private List<Products> productsList;


}
