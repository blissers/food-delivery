package food.domain;

import food.domain.*;
import food.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderCanceled extends AbstractEvent {

    private Long id;
    private String foodId;
    private String address;
    private String status;

    public OrderCanceled(OrderList aggregate) {
        super(aggregate);
    }

    public OrderCanceled() {
        super();
    }
}
