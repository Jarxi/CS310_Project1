package main;



import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;

public class FlightMap {
    //store origin
    private String origin;
    //store origin,destination and distance
    private HashMap<String, HashMap<String, Integer>> adj; //adjacent nodes list

    public HashMap<String, HashMap<String, Integer>> getAdj() {
        return adj;
    }

    /**
     * add edge between origin and destination with distance
     * @param o origin
     * @param d destination
     * @param distance
     */
    private void addEdge(String o, String d, Integer distance){
        if(adj.containsKey(o)){
            adj.get(o).put(d,distance);
        }else{
            HashMap<String, Integer> temp = new HashMap<>();
            temp.put(d, distance);
            adj.put(o,temp);
        }
    }


    private void createMap(String filename) throws FileNotFoundException {
        System.out.println(new File(".").getAbsoluteFile());
        //read in file
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            //read line by line
            while ((line = br.readLine()) != null) {
                String[] stringArray=line.split(" ");
                if (stringArray.length<3 && stringArray!=null){
                    this.origin = stringArray[0].toLowerCase();
                    continue;
                }
                //create mapping
                if (stringArray.length == 3){
                    for (int i=0; i<stringArray.length; i++){
                        Integer dist = Integer.parseInt(stringArray[2]);
                        addEdge(stringArray[0].toLowerCase(),
                                stringArray[1].toLowerCase(), dist);
                    }
                }
            }
        } catch (FileNotFoundException fileNotFound){
            fileNotFound.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //create new flightMap
    public FlightMap(String filename) throws FileNotFoundException {
        adj = new HashMap<>();
        createMap(filename);
    }

}
