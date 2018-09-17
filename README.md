##Commands

> init doc/dist/bin   
`ant init`  

>compile SearchMap.java and FlightMap.java  
`ant compile-main`

>compile test classes and main classes   
`ant compile`

>convert classes to jar file   
`ant dist`

>generate documentation   
`ant doc`

>run tests   
`ant test`

>clean doc/dist/bin   
`ant clean`

## Run Program
```diff
- Please add input files under input/. Output files are under output/.
```

####How to run SearchMap.txt


```
cd bin
java main.SearchMap ../input/inputfile.txt ../output/outputfile.txt
```  

#### How to run jar file
```
cd dist
java -jar SearchMap.jar ../input/inputfile.txt ../output/output.txt
```
