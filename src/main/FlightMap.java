package main;



import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;

public class FlightMap {


    //store origin
    private String origin = null;
    //store origin,destination and distance
    private HashMap<String, HashMap<String, Integer>> adj;

    /**
     * get adjacent city
     * @return adjacent city map
     */
    public HashMap<String, HashMap<String, Integer>> getAdj() {
        return adj;
    }

    /**
     * get origin city
     * @return city origin
     */
    public String getOrigin() {
        return origin;
    }
    /**
     * add edge between origin and destination with distance
     * @param o origin
     * @param d destination
     * @param cost cost of the flight
     */
    private void addEdge(String o, String d, Integer cost){
        if(adj.containsKey(o)){
            adj.get(o).put(d,cost);
        }else{
            HashMap<String, Integer> temp = new HashMap<>();
            temp.put(d, cost);
            adj.put(o,temp);
        }
    }

    /**
     * create a map based on input file
     * @param filename input file name
     * @throws IOException Exception is thrown when bufferedreader cannot be closed
     */
    private void createMap(String filename) throws IOException {
        BufferedReader br = null;
        //read in file
        try{
            br = new BufferedReader(new FileReader(filename));
            String line;
            line = br.readLine();
            String[] stringArray=line.trim().split(" ");
            if (stringArray.length==1 && stringArray!=null){
                this.origin = stringArray[0].toUpperCase();
            }
            //read line by line
            while ((line = br.readLine()) != null) {
                stringArray=line.trim().split(" ");
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

    /**
     * constructor
     * @param filename Input file name
     * @throws IOException Exception appears when it has error to close BufferedReader
     */
    public FlightMap(String filename) throws IOException {
        adj = new HashMap<>();
        createMap(filename);
    }

}
