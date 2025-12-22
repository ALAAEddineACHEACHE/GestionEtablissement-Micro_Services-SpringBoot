package com.example.Api.Gateway.Routes;

import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;




@Configuration
public class Routes {

    // ================= STUDENT =================
    @Bean
    public RouterFunction<ServerResponse> studentServiceRouter() {
        return GatewayRouterFunctions.route("student-service")
                .route(RequestPredicates.path("/students/**"),
                        HandlerFunctions.http("http://localhost:6002"))
//                .filter(CircuitBreakerFilterFunctions.circuitBreaker(
//                        "studentServiceCB",
//                        URI.create("forward:/fallback")))
                .build();
    }

    // ================= TEACHER =================
    @Bean
    public RouterFunction<ServerResponse> teacherServiceRouter() {
        return GatewayRouterFunctions.route("teacher-service")
                .route(RequestPredicates.path("/teachers/**"),
                        HandlerFunctions.http("http://localhost:6003"))
//                .filter(CircuitBreakerFilterFunctions.circuitBreaker(
//                        "teacherServiceCB",
//                        URI.create("forward:/fallback")))
                .build();
    }

    // ================= COURSE =================
    @Bean
    public RouterFunction<ServerResponse> courseServiceRouter() {
        return GatewayRouterFunctions.route("course-service")
                .route(RequestPredicates.path("/courses/**"),
                        HandlerFunctions.http("http://localhost:6004"))
//                .filter(CircuitBreakerFilterFunctions.circuitBreaker(
//                        "courseServiceCB",
//                        URI.create("forward:/fallback")))
                .build();
    }

    // ================= SCHEDULE =================
    @Bean
    public RouterFunction<ServerResponse> scheduleServiceRouter() {
        return GatewayRouterFunctions.route("schedule-service")
                .route(RequestPredicates.path("/schedules/**"),
                        HandlerFunctions.http("http://localhost:6005"))
//                .filter(CircuitBreakerFilterFunctions.circuitBreaker(
//                        "scheduleServiceCB",
//                        URI.create("forward:/fallback")))
                .build();
    }

    // ================= EXAM =================
    @Bean
    public RouterFunction<ServerResponse> examServiceRouter() {
        return GatewayRouterFunctions.route("exam-service")
                .route(RequestPredicates.path("/exams/**"),
                        HandlerFunctions.http("http://localhost:6006"))
//                .filter(CircuitBreakerFilterFunctions.circuitBreaker(
//                        "examServiceCB",
//                        URI.create("forward:/fallback")))
                .build();
    }

    // ================= ADMIN =================
    @Bean
    public RouterFunction<ServerResponse> adminServiceRouter() {
        return GatewayRouterFunctions.route("admin-service")
                .route(RequestPredicates.path("/admin/**"),
                        HandlerFunctions.http("http://localhost:6007"))
//                .filter(CircuitBreakerFilterFunctions.circuitBreaker(
//                        "adminServiceCB",
//                        URI.create("forward:/fallback")))
                .build();
    }

    // ================= FALLBACK =================
//    @Bean
//    public RouterFunction<ServerResponse> fallbackRoute() {
//        return GatewayRouterFunctions.route("fallback")
//                .GET("/fallback",
//                        request -> ServerResponse
//                                .status(HttpStatus.SERVICE_UNAVAILABLE)
//                                .body("Service indisponible, réessayez plus tard"))
//                .build();
//    }
}
