package es.upm.miw.apaw_ep_fernanda_guerra.order_resource;

        import es.upm.miw.apaw_ep_fernanda_guerra.operator_data.Operator;
        import es.upm.miw.apaw_ep_fernanda_guerra.operator_resource.OperatorDto;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping(OrderResource.ORDERS)
public class OrderResource {

    public static final String ORDERS = "/orders";

    static final String ID_ID = "/{id}";

    static final String PRICE = "/price";

    static final String OPERATOR = "/operator";

    private OrderBusinessController orderBusinessController;

    @Autowired
    public OrderResource(OrderBusinessController orderBusinessController) {
        this.orderBusinessController = orderBusinessController;
    }

    @PostMapping
    public OrderBasicDto create(@RequestBody OrderCreationDto orderCreationDto) {
        orderCreationDto.validate();
        return this.orderBusinessController.create(orderCreationDto);
    }

    @GetMapping
    public List<OrderBasicDto> readAll() {
        return this.orderBusinessController.readAll();
    }

    @GetMapping(value = ID_ID + PRICE)
    public OrderBasicDto readPrice(@PathVariable String id) {
        return this.orderBusinessController.readOrder(id);
    }

    @GetMapping(value = OPERATOR)
    public OperatorDto readOperator(@PathVariable String id) {
        return this.orderBusinessController.readOperator(id);
    }

}