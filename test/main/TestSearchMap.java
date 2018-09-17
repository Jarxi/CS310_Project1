package main;

import org.junit.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TestSearchMap {
    SearchMap searchMap;
    @Before
    public void runBeforeTest() throws Exception{
        FlightMap flightMap = new FlightMap("input/inputfile1.txt");
        if (flightMap.getOrigin() != null){
            searchMap = new SearchMap(flightMap);
            searchMap.output("output/outputfile.txt");
        }
    }
    @Test
    public void testGetCityCost(){
        assertEquals(300, searchMap.getCityCost().get("R").intValue());
        assertEquals(200, searchMap.getCityCost().get("W").intValue());
        assertEquals(500, searchMap.getCityCost().get("X").intValue());
    }

    @Test
    public void testGetCityPrevious(){
        assertEquals("P, W", searchMap.getCityPrevious().get("W"));
        assertEquals("P, R", searchMap.getCityPrevious().get("R"));
        assertEquals("P, R, X", searchMap.getCityPrevious().get("X"));
    }
    /**
     * test if rightpad() produces the right amount of length
     */
    @Test
    public void testRightPad(){
        String line = searchMap.rightpad("",15);
        assertEquals(15, line.length());
    }

    /**
     * put all cities in a map and give false value
     */
    @Test
    public void testMergeCity(){
        HashMap<String, Integer> cityCost = new HashMap<>();
        HashMap<String, HashMap<String, Integer>> flights = new HashMap<>();
        cityCost.put("a", 10);
        cityCost.put("b", 11);
        flights.put("c", cityCost);
        HashMap<String, Boolean> visited =  searchMap.mergeCity(flights);
        assertEquals(3, visited.size());
        for (String key : visited.keySet()){
            assertEquals(false, visited.get(key));
        }
    }

    /**
     * if origin city is "" or null, cityCost and cityPrevious should have size 0
     */
    @Test
    public void testSearch(){
        HashMap<String, Integer> cityCost = new HashMap<>();
        HashMap<String, HashMap<String, Integer>> flights = new HashMap<>();
        cityCost.put("a", 10);
        cityCost.put("b", 11);
        flights.put("c", cityCost);
        HashMap<String, Boolean> visited =  searchMap.mergeCity(flights);
        searchMap.search("", visited, flights);
        assertEquals(0,searchMap.getCityCost().size());
        assertEquals(0,searchMap.getCityPrevious().size());
    }

    /**
     * with inputfile1.txt, the testoutput.txt should contain 5 lines with
     * empty 5th line
     * @throws IOException
     */
    @Test
    public void testOutput() throws IOException {
        searchMap.output("output/testoutput.txt");
        BufferedReader br = null;
        int count = 1;
        //read in file
        try{
            br = new BufferedReader(new FileReader("output/testoutput.txt"));
            String line;
            //read line by line
            while ((line = br.readLine()) != null) {
                count = count+1;
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
        assertEquals(5, count);
    }

}