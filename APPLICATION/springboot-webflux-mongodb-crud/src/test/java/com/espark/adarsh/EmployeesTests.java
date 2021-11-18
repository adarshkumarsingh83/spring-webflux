package com.espark.adarsh;

import com.espark.adarsh.bean.EmployeeBean;
import com.espark.adarsh.entity.EmployeeEntity;
import com.espark.adarsh.handler.EmployeeHandler;
import com.espark.adarsh.repository.EmployeeRepository;
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
@Import({EmployeeHandler.class, EmployeeService.class, EmployeeRepository.class})
public class EmployeesTests {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    void contextLoads() {
    }


    @Test
    void saveEmployeeTest() {
        EmployeeEntity employee = new EmployeeEntity(1, "employee");
        Mockito.when(employeeRepository.save(employee)).thenReturn(Mono.just(employee));

        Flux<EmployeeBean> responseBody = webClient.post()
                .uri("/router/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(employee))
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(EmployeeBean.class)
                .getResponseBody();

        Mockito.verify(employeeRepository, Mockito.times(1)).save(employee);

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(employeeBean -> employeeBean.getId().equals(1) && employeeBean.getName().equals("employee"))
                .verifyComplete();
    }

    @Test
    void updateEmployeeTest() {
        EmployeeEntity employee = new EmployeeEntity(1, "employee");
        Mockito.when(employeeRepository.findById(Mockito.anyInt())).thenReturn(Mono.just(employee));
        Mockito.when(employeeRepository.save(employee)).thenReturn(Mono.just(employee));

        Flux<EmployeeBean> responseBody = webClient.put()
                .uri("/router/employee/1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(employee))
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(EmployeeBean.class)
                .getResponseBody();

        Mockito.verify(employeeRepository,  Mockito.times(1)).findById(Mockito.anyInt());
        Mockito.verify(employeeRepository,  Mockito.times(1)).save(employee);
        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(employee.getBean())
                .verifyComplete();
    }

    @Test
    void fetchEmployeeTest() {
        EmployeeEntity employeeEntity = new EmployeeEntity(101, "adi");
        Mono<EmployeeEntity> employeeEntityMono = Mono.just(employeeEntity);
        Mockito.when(employeeRepository.findById(Mockito.anyInt())).thenReturn(employeeEntityMono);

        Flux<EmployeeBean> responseBody = webClient.get()
                .uri("/router/employee/101")
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(EmployeeBean.class)
                .getResponseBody();

        Mockito.verify(employeeRepository,  Mockito.times(1)).findById(Mockito.anyInt());

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(employeeBean -> employeeBean.getId().equals(101) && employeeBean.getName().equals("adi"))
                .verifyComplete();
    }


    @Test
    void fetchAllEmployeeTest() {
        List<EmployeeEntity> employeeList = Arrays.asList(new EmployeeEntity(101, "adi"),
                new EmployeeEntity(102, "radha"));
        Flux<EmployeeEntity> employeeBeanFlux = Flux.fromIterable(employeeList);
        Mockito.when(employeeRepository.findAll()).thenReturn(employeeBeanFlux);

        Flux<EmployeeBean> responseBody = webClient.get()
                .uri("/router/employees")
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(EmployeeBean.class)
                .getResponseBody();

        Mockito.verify(employeeRepository,  Mockito.times(1)).findAll();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(employeeList.get(0).getBean())
                .expectNext(employeeList.get(1).getBean())
                .verifyComplete();
    }




    @Test
    void deleteEmployeeTest() {
        Mono<Void> voidReturn = Mono.empty();
        Mockito.when(employeeRepository.deleteById(1))
                .thenReturn(voidReturn);

        webClient.delete()
                .uri("/router/employee/{id}", 1)
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(employeeRepository,  Mockito.times(1)).deleteById(Mockito.anyInt());
    }
}
