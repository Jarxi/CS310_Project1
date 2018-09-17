package main;

import javax.print.attribute.standard.Destination;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.addAll;

public class SearchMap {

    private FlightMap map;
    private HashMap <String,Integer> cityCost;
    private HashMap <String, String> cityPrevious;

    /**
     * @return city and the costs of flight
     */
    public HashMap<String, Integer> getCityCost() {
        return cityCost;
    }

    public HashMap<String, String> getCityPrevious() {
        return cityPrevious;
    }
    public String rightpad(String text, int length) {
        return String.format("%-" + length + "." + length + "s", text);
    }
    /**
     * put all cities in a map and give the boolean value to represent
     * visited or not
     * @param adj input adjacent cities
     * @return all cities with false value
     */
    public HashMap<String, Boolean> mergeCity(HashMap<String, HashMap<String, Integer>> adj){
        Set<String> citySet1 = adj.keySet();
        HashMap<String, Boolean> visited = new HashMap<>();
        for (String key : citySet1){
            visited.put(key,false);
            for (String key2 : adj.get(key).keySet())
            visited.put(key2, false);
        }
        return visited;
    }

    /**
     * search the map to find possible destinations and the costs and routes
     * @param origin the origin city
     * @param visited the map stores whether the city is visited
     * @param adj cities that can be reached and the cost
     */
    public void search(String origin, HashMap<String, Boolean> visited, HashMap<String, HashMap<String, Integer>> adj){
        this.cityCost = new HashMap<>();
        this.cityPrevious = new HashMap<>();
        Deque<String> queue = new ArrayDeque<String>();

        if (origin == null || origin.equals("")){
            return;
        }
        queue.push(origin);
        cityPrevious.put(origin, origin);
        cityCost.put(origin, 0);
        while(queue.size() != 0){
            String currentCity = queue.getFirst();
            queue.removeFirst();
            if (adj.containsKey(currentCity)){
                for (String key : adj.get(currentCity).keySet()){
                    if (!visited.get(key)){
                        cityCost.put(key, cityCost.get(currentCity)+adj.get(currentCity).get(key));
                        cityPrevious.put(key, cityPrevious.get(currentCity)+", "+key);
                        visited.put(key,true);
                        queue.addLast(key);
                    }
                }
            }
        }
    }
    /**
     * Search through the map and giving the output
     * @param map search the map
     */
    public SearchMap(FlightMap map){
        this.map = map;
        HashMap<String, HashMap<String, Integer>> adj = map.getAdj();
        HashMap<String, Boolean> visited = mergeCity(adj);
        String origin = map.getOrigin();
        search(origin, visited, map.getAdj());
    }

    /**
     * output destination flight and cost to arg
     * @param arg output file name
     */
    public void output(String arg){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(arg));
            String destination = rightpad("Destination", 15);
            String flightRoute = rightpad("Flight Route from "+ map.getOrigin(),22);
            String flightCost = rightpad("Total Cost", 10);
            String title = destination + flightRoute + flightCost+"\n";
            writer.write(title);
            for (String key : cityPrevious.keySet()){
                if (key!= map.getOrigin()){
                    String line = rightpad(key,15);
                    line = line+rightpad(cityPrevious.get(key), 22);
                    line = line+rightpad(cityCost.get(key).toString(),10);
                    writer.write(line+"\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String [] args) throws IOException {
        if (args.length<2){
            System.out.println("Please enter two filenames.");
        }else{

            FlightMap flightMap = new FlightMap(args[0]);
            if (flightMap.getOrigin() != null){
                SearchMap searchMap = new SearchMap(flightMap);
                searchMap.output(args[1]);
            }
        }
    }
}
