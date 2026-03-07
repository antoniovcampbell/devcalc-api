package com.devcalc;

import com.devcalc.service.CalculatorServiceImpl;
import io.javalin.Javalin;

import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        createApp().start(7070);
    }

    public static Javalin createApp() {
        CalculatorServiceImpl calculatorService = new CalculatorServiceImpl();

        Javalin app = Javalin.create(config -> {
            config.http.defaultContentType = "text/plain";
            config.showJavalinBanner = false;
        });

        app.get("/add", ctx -> {
            try {
                int a = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("a")));
                int b = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("b")));
                ctx.result(String.valueOf(calculatorService.add(a, b)));
            } catch (Exception e) {
                ctx.status(400).result("Error: Invalid parameters. Use integers for a and b.");
            }
        });

        app.get("/subtract", ctx -> {
            try {
                int a = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("a")));
                int b = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("b")));
                ctx.result(String.valueOf(calculatorService.subtract(a, b)));
            } catch (Exception e) {
                ctx.status(400).result("Error: Invalid parameters. Use integers for a and b.");
            }
        });

        app.get("/multiply", ctx -> {
            try {
                int a = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("a")));
                int b = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("b")));
                ctx.result(String.valueOf(calculatorService.multiply(a, b)));
            } catch (Exception e) {
                ctx.status(400).result("Error: Invalid parameters. Use integers for a and b.");
            }
        });

        app.get("/divide", ctx -> {
            try {
                int a = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("a")));
                int b = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("b")));
                ctx.result(String.valueOf(calculatorService.divide(a, b)));
            } catch (IllegalArgumentException e) {
                ctx.status(400).result("Error: " + e.getMessage());
            } catch (Exception e) {
                ctx.status(400).result("Error: Invalid parameters. Use integers for a and b.");
            }
        });

        app.get("/sqrt", ctx -> {
            try {
                double x = Double.parseDouble(Objects.requireNonNull(ctx.queryParam("x")));
                double result = calculatorService.sqrt(x);
                if (result == (long) result) {
                    ctx.result(String.valueOf((long) result));
                } else {
                    ctx.result(String.valueOf(result));
                }
            } catch (IllegalArgumentException e) {
                ctx.status(400).result("Error: " + e.getMessage());
            } catch (Exception e) {
                ctx.status(400).result("Error: Invalid parameter. Use a number for x.");
            }
        });

        app.get("/health", ctx -> ctx.result("API is running"));

        app.get("/", ctx -> ctx.result(
                "DevCalc API\n\n" +
                        "GET /add?a=10&b=5\n" +
                        "GET /subtract?a=10&b=5\n" +
                        "GET /multiply?a=10&b=5\n" +
                        "GET /divide?a=10&b=5\n" +
                        "GET /sqrt?x=16\n" +
                        "GET /health"
        ));

        return app;
    }
}