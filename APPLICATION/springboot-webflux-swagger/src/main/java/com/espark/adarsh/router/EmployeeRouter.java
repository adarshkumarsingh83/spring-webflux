package com.espark.adarsh.router;


import com.espark.adarsh.bean.Employee;
import com.espark.adarsh.handler.EmployeeHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class EmployeeRouter {

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/router/employee/{empId}"
                    , produces = {MediaType.APPLICATION_JSON_VALUE}
                    , method = RequestMethod.GET
                    , beanClass = EmployeeHandler.class
                    , beanMethod = "getEmployee"
                    , operation = @Operation(operationId = "getEmployee"
                    , responses =
                    {
                            @ApiResponse(responseCode = "200"
                                    , description = "fetched successfully"
                                    , content = @Content(schema = @Schema(implementation = Employee.class))
                            )
                            , @ApiResponse(responseCode = "404", description = "employee not found with given id")
                    },
                    parameters = {
                            @Parameter(in = ParameterIn.PATH, name = "empId")
                    })
            ),
            @RouterOperation(
                    path = "/router/employees"
                    , produces = {MediaType.APPLICATION_JSON_VALUE}
                    , method = RequestMethod.GET
                    , beanClass = EmployeeHandler.class
                    , beanMethod = "getEmployees"
                    , operation = @Operation(operationId = "getEmployees"
                    , responses = {@ApiResponse(responseCode = "200"
                    , description = "fetched successfully"
                    , content = @Content(schema = @Schema(implementation = Employee.class)))})
            ),
            @RouterOperation(
                    path = "/router/employee"
                    , produces = {MediaType.APPLICATION_JSON_VALUE}
                    , method = RequestMethod.POST
                    , beanClass = EmployeeHandler.class
                    , beanMethod = "saveEmployee"
                    , operation = @Operation(operationId = "saveEmployee"
                    , responses =
                    {
                            @ApiResponse(responseCode = "201"
                                    , description = "saved successfully"
                                    , content = @Content(schema = @Schema(implementation = Employee.class))
                            )
                            , @ApiResponse(responseCode = "500", description = "employee already exists with id")
                    },
                    requestBody = @RequestBody(
                            content = @Content(schema = @Schema(
                                    implementation = Employee.class
                            ))
                    )
            )),
            @RouterOperation(
                    path = "/router/employee/{empId}"
                    , produces = {MediaType.APPLICATION_JSON_VALUE}
                    , method = RequestMethod.PUT
                    , beanClass = EmployeeHandler.class
                    , beanMethod = "updateEmployee"
                    , operation = @Operation(operationId = "updateEmployee"
                    , responses =
                    {
                            @ApiResponse(responseCode = "200"
                                    , description = "updated successfully"
                                    , content = @Content(schema = @Schema(implementation = Employee.class))
                            )
                            , @ApiResponse(responseCode = "500", description = "employee already exists with id")
                    },
                    parameters = {
                            @Parameter(in = ParameterIn.PATH, name = "empId")
                    },
                    requestBody = @RequestBody(
                            content = @Content(schema = @Schema(
                                    implementation = Employee.class
                            ))
                    )
            )),
            @RouterOperation(
                    path = "/router/employee/{empId}"
                    , produces = {MediaType.APPLICATION_JSON_VALUE}
                    , method = RequestMethod.DELETE
                    , beanClass = EmployeeHandler.class
                    , beanMethod = "deleteEmployee"
                    , operation = @Operation(operationId = "deleteEmployee"
                    , responses =
                    {
                            @ApiResponse(responseCode = "200"
                                    , description = "deleted successfully"
                                    , content = @Content(schema = @Schema(implementation = Employee.class))
                            )
                            , @ApiResponse(responseCode = "404", description = "employee not found with given id")
                    },
                    parameters = {
                            @Parameter(in = ParameterIn.PATH, name = "empId")
                    })
            ),
    })
    public RouterFunction<ServerResponse> routerFunction(EmployeeHandler employeeHandler) {
        return RouterFunctions.route()
                .GET("/router/employee/{empId}", employeeHandler::getEmployee)
                .GET("/router/employees", employeeHandler::getEmployees)
                .POST("/router/employee", employeeHandler::saveEmployee)
                .PUT("/router/employee/{empId}", employeeHandler::updateEmployee)
                .DELETE("/router/employee/{empId}", employeeHandler::deleteEmployee)
                .build();

    }
}
