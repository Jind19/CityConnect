package org.example;

import java.util.*;

public class CityGraph {

    // A map that links each city to a list of cities directly connected to it.
    private final Map<String, List<String>> roadMap;

    public CityGraph() {
        this.roadMap = new HashMap<>();
    }

    public void addCity(String cityName){
        // If the city isn't already in the map, add it with an empty list of connected cities.
        this.roadMap.putIfAbsent(cityName, new ArrayList<>());
    }

    // Ensure both cities exist in the map by calling addCity for each.
    // Then, add city2 to city1's connection list and vice versa.
    // This models an undirected road between them
    public void connectCities(String city1, String city2){
        this.roadMap.get(city1).add(city2);
        this.roadMap.get(city2).add(city1);
    }


    // Return the list of cities connected to the given city.
    // If the city doesn't exist, return an empty list.
    public List<String> getConnectedCities(String city){
        return this.roadMap.getOrDefault(city, Collections.emptyList());
    }

    //Check if the destination 1 is reachable from the source city
    public boolean isReachable(String source, String destination){
        Set<String> visitedCities = new HashSet<>();  //Initialize visited cities

        Queue<String> queue = new LinkedList<>(); //Initialize 'BFS' Search Queue
        queue.add(source); //adding the source city to start the search.

        // Process Until Queue is Empty
        // Typical BFS loop — continue until all connected nodes are visited or destination is found.

        while (!queue.isEmpty()){
            // Step1: Dequeue and Check
            String current = queue.poll();
            if(current.equals(destination))
                // If this is the target, we found a connection → return true.
                return true;
            // Mark as Visited: Avoid visiting this user again in future iterations.
            visitedCities.add(current);

            // Explore Neighbors
            for (String neighbor : getConnectedCities(current)) {
                if(!visitedCities.contains(neighbor)){
                    queue.add(neighbor);
                }
            }
        }
        // If We Exhaust All Options
        return false;
    }

}
