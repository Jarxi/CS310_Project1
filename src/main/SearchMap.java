package main;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.addAll;

public class SearchMap {

    private FlightMap map;


    public FlightMap getMap() {
        return map;
    }

    public void setMap(FlightMap map) {
        this.map = map;
    }

    /**
     * put all cities in a map and give the boolean value to represent
     * visited or not
     * @param adj
     * @return HashMap<String, Boolean>
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
     * Search through the map and giving the output
     * @param map
     */
    public SearchMap(FlightMap map){
        this.map = map;
        HashMap<String, HashMap<String, Integer>> adj = map.getAdj();
        HashMap<String, Boolean> visited = mergeCity(adj);
    }
    public static void main(String [] args) throws FileNotFoundException {
        if (args.length<2){
            System.out.println("Please enter two filenames.");
        }else{
            FlightMap flightMap = new FlightMap(args[0]);
            SearchMap searchMap = new SearchMap(flightMap);
        }
    }
}
