package es.upm.miw.apaw_ep_fernanda_guerra.order_resource;

import es.upm.miw.apaw_ep_fernanda_guerra.ApiTestConfig;
import es.upm.miw.apaw_ep_fernanda_guerra.croqueta_resource.CroquetaBasicDto;
import es.upm.miw.apaw_ep_fernanda_guerra.croqueta_resource.CroquetaResource;
import es.upm.miw.apaw_ep_fernanda_guerra.operator_data.Operator;
import es.upm.miw.apaw_ep_fernanda_guerra.operator_resource.OperatorDto;
import es.upm.miw.apaw_ep_fernanda_guerra.operator_resource.OperatorResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@ApiTestConfig
public class OrderResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    String createOrder(Double price) {
        OperatorDto operatorDto =
                new OperatorDto("Fernanda", "Guerra", "909090X");
        String operatorId = this.webTestClient
                .post().uri(OperatorResource.OPERATORS)
                .body(BodyInserters.fromObject(operatorDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(OperatorDto.class)
                .returnResult().getResponseBody().getId();
        CroquetaBasicDto croquetaBasicDto =
                new CroquetaBasicDto();
        String croquetaId = this.webTestClient
                .post().uri(CroquetaResource.CROQUETAS)
                .body(BodyInserters.fromObject(croquetaBasicDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(CroquetaBasicDto.class)
                .returnResult().getResponseBody().getId();
        return this.webTestClient
                .post().uri(OrderResource.ORDERS)
                .body(BodyInserters.fromObject(new OrderCreationDto(price, operatorId, croquetaId)))
                .exchange()
                .expectStatus().isOk()
                .expectBody(OrderBasicDto.class)
                .returnResult().getResponseBody().getId();
    }

    @Test
    void testCreate() {
        this.createOrder(90.00);
    }



}