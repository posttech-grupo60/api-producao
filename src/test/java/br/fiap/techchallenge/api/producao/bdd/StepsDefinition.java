package br.fiap.techchallenge.api.producao.bdd;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import br.fiap.techchallenge.api.producao.dto.OrderDTO;
import br.fiap.techchallenge.api.producao.model.Order;
import br.fiap.techchallenge.api.producao.util.TestUtils;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;

public class StepsDefinition {

	    private Response response;

	    private Order orderResponse;

	    private String ENDPOINT_MENSAGENS = "http://localhost:8080/order";

	    @Quando("registrar um novo Order")
	    public Order submeterNovoOrder() {
	        OrderDTO fakeOrderDTO = TestUtils.createFakeOrderDTO();
	        response = given()
	                .contentType(MediaType.APPLICATION_JSON_VALUE)
	                .body(fakeOrderDTO)
	                .when().post(ENDPOINT_MENSAGENS);
	        return response.then().extract().as(Order.class);
	    }

	    @Então("é registrado com sucesso")
	    public void orderRegistradoComSucesso() {
	        response.then()
	                .statusCode(HttpStatus.CREATED.value())
	                .body(matchesJsonSchemaInClasspath("./schemas/OrderResponseSchema.json"));
	    }

	    @Dado("que um order ja foi inserido")
	    public void orderJaPublicado() {
	    	orderResponse = submeterNovoOrder();
	    }

	    @Quando("efetuar a busca dele")
	    public void requisitarBuscarOrder() {
	        response = given()
	                .contentType(MediaType.APPLICATION_JSON_VALUE)
	                .when()
	                .get("/order/{id}", orderResponse.getId().toString());
	    }

	    @Então("order é retornado com sucesso")
	    public void orderRetornadoComSucesso() {
	        response.then()
	                .statusCode(HttpStatus.OK.value())
	                .body(matchesJsonSchemaInClasspath("./schemas/OrderResponseSchema.json"));
	    }

	    @Quando("requisitar a lista de order")
	    public void requisitarListaOrders() {
	        response = given()
	                .contentType(MediaType.APPLICATION_JSON_VALUE)
	                .when()
	                .get("/order");
	    }

	    @Então("os orders são exibidas com sucesso")
	    public void ordersSaoExibidosComSucesso() {
	        response.then()
	                .statusCode(HttpStatus.OK.value())
	                .body(matchesJsonSchemaInClasspath("./schemas/OrderAllSchema.json"));
	    }

	    @Quando("atualizar um novo Order")
	    public void requisitarAlteracaoDoOrder() {
	        orderResponse.setCustomerId(new Random().nextLong());
	        response = given()
	                .contentType(MediaType.APPLICATION_JSON_VALUE)
	                .body(orderResponse)
	                .when()
	                .put("/order/{id}", orderResponse.getId().toString());
	    }

	    @Então("é atualizado com sucesso")
	    public void mensagemAtualizadaComSucesso() {
	        response.then()
	                .statusCode(HttpStatus.OK.value())
	                .body(matchesJsonSchemaInClasspath("./schemas/OrderResponseSchema.json"));
	    }

	    @Quando("a requisição for para remover um order")
	    public void requisitarExclusaoDaMensagem() {
	        response = given()
	                .contentType(MediaType.APPLICATION_JSON_VALUE)
	                .when()
	                .delete("/order/{id}", orderResponse.getId().toString());
	    }

	    @Então("order é removido com sucesso")
	    public void mensagemRemovidaComSucesso() {
	        response.then()
	                .statusCode(HttpStatus.NO_CONTENT.value());
    }

}
