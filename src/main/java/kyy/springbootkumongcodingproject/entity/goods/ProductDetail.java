package kyy.springbootkumongcodingproject.entity.goods;

import kyy.springbootkumongcodingproject.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product_detail")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    // 외래키(FK)
    @OneToOne(optional = false)
    @JoinColumn(name="product_number")
    private Product product;



}
