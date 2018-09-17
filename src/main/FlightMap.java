package main;



import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;

public class FlightMap {


    //store origin
    private String origin = null;
    //store origin,destination and distance
    private HashMap<String, HashMap<String, Integer>> adj; //adjacent nodes list

    public HashMap<String, HashMap<String, Integer>> getAdj() {
        return adj;
    }

    public String getOrigin() {
        return origin;
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

    private void createMap(String filename) throws IOException {
        BufferedReader br = null;
        //read in file
        try{
            br = new BufferedReader(new FileReader(filename));
            String line;
            //read line by line
            while ((line = br.readLine()) != null) {
                String[] stringArray=line.split(" ");
                if (stringArray.length<3 && stringArray!=null){
                    this.origin = stringArray[0].toUpperCase();
                    continue;
                }
                //create mapping
                if (stringArray.length == 3){
                    for (int i=0; i<stringArray.length; i++){
                        Integer dist = Integer.parseInt(stringArray[2]);
                        addEdge(stringArray[0].toUpperCase(),
                                stringArray[1].toUpperCase(), dist);
                    }
                }
            }
        } catch (FileNotFoundException fileNotFound){
            System.out.println("File is not found. Please check " +
                    "the filename and put input files under test/ directory.");
        } catch (IOException e) {
            System.out.println("There is trouble reading the file");
        }finally {
            if(br!=null){
                br.close();
            }
        }
    }

    //create new flightMap
    public FlightMap(String filename) throws IOException {
        adj = new HashMap<>();
        createMap(filename);
    }

}
