package com.devcalc;

import com.devcalc.service.CalculatorServiceImpl;
import io.javalin.Javalin;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        CalculatorServiceImpl calculatorService = new CalculatorServiceImpl();

        Javalin app = Javalin.create(config -> {
            config.http.defaultContentType = "text/plain";
            config.showJavalinBanner = false;
        }).start(7070);

        // Endpoint GET /add
        app.get("/add", ctx -> {
            try {
                int a = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("a")));
                int b = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("b")));
                int result = calculatorService.add(a, b);
                ctx.result(String.valueOf(result));
            } catch (NumberFormatException e) {
                ctx.status(400).result("Error: Invalid parameters. Use integers for a and b.");
            }
        });

        // Endpoint GET /subtract
        app.get("/subtract", ctx -> {
            try {
                int a = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("a")));
                int b = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("b")));
                int result = calculatorService.subtract(a, b);
                ctx.result(String.valueOf(result));
            } catch (NumberFormatException e) {
                ctx.status(400).result("Error: Invalid parameters. Use integers for a and b.");
            }
        });

        // Endpoint GET /multiply
        app.get("/multiply", ctx -> {
            try {
                int a = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("a")));
                int b = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("b")));
                int result = calculatorService.multiply(a, b);
                ctx.result(String.valueOf(result));
            } catch (NumberFormatException e) {
                ctx.status(400).result("Error: Invalid parameters. Use integers for a and b.");
            }
        });

        // Endpoint GET /divide
        app.get("/divide", ctx -> {
            try {
                int a = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("a")));
                int b = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("b")));

                if (b == 0) {
                    ctx.status(400).result("Error: Division by zero is not allowed.");
                    return;
                }

                int result = calculatorService.divide(a, b);
                ctx.result(String.valueOf(result));
            } catch (NumberFormatException e) {
                ctx.status(400).result("Error: Invalid parameters. Use integers for a and b.");
            } catch (IllegalArgumentException e) {
                ctx.status(400).result("Error: " + e.getMessage());
            }
        });

        // Endpoint GET /health para verificação
        app.get("/health", ctx -> {
            ctx.result("API is running");
        });

        // Endpoint GET / para documentação
        app.get("/", ctx -> {
            ctx.result("DevCalc API\n\nAvailable endpoints:\n" +
                    "GET /add?a=10&b=5\n" +
                    "GET /subtract?a=10&b=5\n" +
                    "GET /multiply?a=10&b=5\n" +
                    "GET /divide?a=10&b=5\n" +
                    "GET /health\n\n" +
                    "Example: curl \"http://localhost:7070/add?a=10&b=5\"");
        });

        System.out.println("DevCalc API running on http://localhost:7070");
    }
}