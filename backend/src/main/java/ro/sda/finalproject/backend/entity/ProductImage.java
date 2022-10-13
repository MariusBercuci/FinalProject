package ro.sda.finalproject.backend.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Data
@Table(name = "product_images")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImage {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "image_id", nullable = false)
    private Long imageId;
    private String imageName;
    private String imageType;
    @Column(length = 50_000_000)
    private byte[] picByte;


}
