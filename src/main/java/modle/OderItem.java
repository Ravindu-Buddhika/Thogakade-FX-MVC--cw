package modle;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OderItem {
    private String oderID;
    private String itemID;
    private int qty;
    private double dis;
    private double price;
}
