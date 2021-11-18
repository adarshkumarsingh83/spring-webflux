package com.espark.adarsh;

import com.espark.adarsh.bean.EmployeeBean;
import com.espark.adarsh.handler.EmployeeHandler;
import com.espark.adarsh.router.EmployeeRouter;
import com.espark.adarsh.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.util.Arrays;
import java.util.List;


@ExtendWith(SpringExtension.class)
@WebFluxTest(EmployeeRouter.class)
@Import({EmployeeService.class, EmployeeHandler.class})
public class EmployeeTests {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void contextLoads() {
    }


    @Test
    void saveEmployeeTest() {
        EmployeeBean employee = new EmployeeBean(1, "employee");
        Mockito.when(employeeService.saveEmployee(employee)).thenReturn(Mono.just(employee));

        Flux<EmployeeBean> responseBody = webClient.post()
                .uri("/router/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(employee))
                .exchange()
                .expectStatus().isOk()
                .returnResult(EmployeeBean.class)
                .getResponseBody();

        Mockito.verify(employeeService, Mockito.times(1)).saveEmployee(employee);

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(employeeBean -> employeeBean.getId().equals(1) && employeeBean.getName().equals("employee"))
                .verifyComplete();
    }


    @Test
    void updateEmployeeTest() {
        EmployeeBean employeeBean = new EmployeeBean(101, "adi");
        Mono<EmployeeBean> employeeBeanMono = Mono.just(employeeBean);
        Mockito.when(employeeService.updateEmployee(101, employeeBean)).thenReturn(employeeBeanMono);

        Flux<EmployeeBean> responseBody = webClient.put()
                .uri("/router/employee/101")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(employeeBean))
                .exchange()
                .expectStatus().isOk()
                .returnResult(EmployeeBean.class)
                .getResponseBody();

        Mockito.verify(employeeService, Mockito.times(1)).updateEmployee(101, employeeBean);

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(employeeBean)
                .verifyComplete();
    }


    @Test
    void fetchEmployeeTest() {
        Mono<EmployeeBean> employeeBeanMono = Mono.just(new EmployeeBean(101, "adi"));
        Mockito.when(employeeService.getEmployee(Mockito.any())).thenReturn(employeeBeanMono);

        Flux<EmployeeBean> responseBody = webClient.get().uri("/router/employee/101")
                .exchange()
                .expectStatus().isOk()
                .returnResult(EmployeeBean.class)
                .getResponseBody();

        Mockito.verify(employeeService, Mockito.times(1)).getEmployee(Mockito.anyInt());

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(employeeBean -> employeeBean.getId().equals(101) && employeeBean.getName().equals("adi"))
                .verifyComplete();
    }

    @Test
    void fetchAllEmployeeTest() {
        List<EmployeeBean> employeeList = Arrays.asList(new EmployeeBean(101, "adi"),
                new EmployeeBean(102, "radha"));
        Flux<EmployeeBean> employeeBeanFlux = Flux.fromIterable(employeeList);
        Mockito.when(employeeService.getEmployees()).thenReturn(employeeBeanFlux);

        Flux<EmployeeBean> responseBody = webClient.get().uri("/router/employees")
                .exchange()
                .expectStatus().isOk()
                .returnResult(EmployeeBean.class)
                .getResponseBody();

        Mockito.verify(employeeService, Mockito.times(1)).getEmployees();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(employeeList.get(0))
                .expectNext(employeeList.get(1))
                .verifyComplete();
    }



    @Test
    void deleteEmployeeTest() {
        Mono<Void> voidReturn = Mono.empty();
        Mockito.when(employeeService.deleteEmployee(1))
                .thenReturn(voidReturn);

        webClient.delete().uri("/router/employee/{id}", 1)
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(employeeService, Mockito.times(1)).deleteEmployee(Mockito.anyInt());
    }
}
