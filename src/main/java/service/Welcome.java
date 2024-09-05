package main.java.service;

import com.sun.tools.javac.Main;

import java.util.Scanner;

public class Welcome {
    public static double screen() {
        Scanner scan = new Scanner(System.in);
        System.out.println("---------------------------------");
        System.out.println("Welcome To Stocks Tracer");
        System.out.println("Determine your Risk Factor");
        String greenLemon = "(2000)";
        String green = " (1500) ";
        String orange = " (1000) ";
        String red = " (500) ";
        System.out.println(greenLemon + green + orange + red);
        System.out.println("---------------------------------");
        double factorRisk = scan.nextDouble();
        return factorRisk;
    }
}
