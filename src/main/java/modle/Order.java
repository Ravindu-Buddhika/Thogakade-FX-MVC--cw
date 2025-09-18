package modle;

import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private String orderId;
    private String customerId;
    private LocalDate orderDate;
    private double total;

}
