package com.example.calculator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculationController {
    @GetMapping("/calculate")
    public String showCalculator() {
        return "calculator";
    }

    @GetMapping("/result")
    public String calculate(@RequestParam("num1") double num1,
                            @RequestParam("num2") double num2,
                            @RequestParam("operation") String operation,
                            Model model) {
        double result;
        String operator;
        
        switch (operation) {
            case "add":
                result = num1 + num2;
                operator = "+";
                break;
            case "subtract":
                result = num1 - num2;
                operator = "-";
                break;
            case "multiply":
                result = num1 * num2;
                operator = "*";
                break;
            case "divide":
                if (num2 != 0) {
                    result = num1 / num2;
                    operator = "/";
                } else {
                    // Handle division by zero error
                    model.addAttribute("errorMessage", "Division by zero is not allowed");
                    return "error";
                }
                break;
            default:
                // Handle invalid operation error
                model.addAttribute("errorMessage", "Invalid operation");
                return "error";
        }

        // Add attributes to pass to the result view
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("operator", operator);
        model.addAttribute("result", result);

        return "result";
    }
}
