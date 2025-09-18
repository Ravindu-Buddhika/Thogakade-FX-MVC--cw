package modle;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Cart {
    private String id;
    private String name;
    private int qty;
    private int dis;
    private String price;

}
