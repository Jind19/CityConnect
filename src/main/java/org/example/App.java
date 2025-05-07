package org.example;

import java.util.Scanner;

public class App
{
    public static void main( String[] args ) {

        CityGraph cityGraph = new CityGraph();
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("🌆 Welcome to CityConnect CLI 🌉");
        System.out.println("Type commands like: addcity Berlin | connect Berlin Hamburg | reachable Berlin Munich | list Berlin | exit");

        while (true) {
            System.out.print("> ");
            input = scanner.nextLine(); //parts = "Berlin Germany".split -> parts= ["Berlin", "Germany"]
            String[] parts = input.split("\\s+"); //"\\s+": use whitespace as a splitter

            if (parts.length == 0)
                continue;


            switch (parts[0].toLowerCase()) {
                case "addcity":
                    if (parts.length < 2) {
                        System.out.println("Usage: addcity <name>");
                        break;
                    }
                    String cityName = parts[1];
                    cityGraph.addCity(cityName);
                    System.out.println("Added City: " + cityName);
                    break;

                case "connect":
                    if (parts.length < 3) {
                        System.out.println("Usage: connect <city1> <city2>");
                        break;
                    }
                    String city1 = parts[1];
                    String city2 = parts[2];
                    cityGraph.connectCities(city1, city2);
                    System.out.println("Connected cities " + city1 + " and " + city2);
                    break;

                case "reachable":
                    if(parts.length < 3) {
                        System.out.println("Usage: reachable <city1> <city2>");
                        break;
                    }
                    String source = parts[1];
                    String destination = parts[2];
                    boolean reachable = cityGraph.isReachable(source, destination);
                    System.out.println(reachable ? "✅ Yes, reachable!" : "❌ No, not reachable.");
                    break;

                case "list":
                    if(parts.length < 2){
                        System.out.println("Usage: list <city>");
                        break;
                    }
                    String city = parts[1];
                    var connected = cityGraph.getConnectedCities(city);
                    if (connected.isEmpty()) {
                        System.out.println("📭 No connections found or city doesn't exist.");
                    } else {
                        System.out.println("📍 Connected cities: " + String.join(", ", connected));
                    }
                    break;

                case "exit":
                    System.out.println("👋 Goodbye!");
                    return;

                default:
                    System.out.println("Unknown command.");


            }


        }


    }


}
