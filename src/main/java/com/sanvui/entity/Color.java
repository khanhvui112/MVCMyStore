package com.sanvui.entity;

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
@RequiredArgsConstructor
@Entity
@Table(name = "Color")
public class Color {
    @Id
    @Column(name="color_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int color_Id;

    @NonNull
    @Column(name = "color_Name", unique = true)
    @Size(min = 3, max = 30, message = "{name.size}")
    private String colorName;

    @OneToMany(mappedBy = "color", cascade = CascadeType.ALL
            , orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Products> productsList;

    public Color(int color_Id, @NonNull String colorName) {
        this.color_Id = color_Id;
        this.colorName = colorName;
    }

    @Override
    public String toString() {
        return "Color{" +
                "color_Id=" + color_Id +
                ", colorName='" + colorName + '\'' +
                '}';
    }
}
