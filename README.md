```diff
-if you want a clear readme, please visit
https://github.com/jjjjjia11/CS310_Project1
```
## Commands

init doc/dist/bin   
`ant init`  

compile SearchMap.java and FlightMap.java  
`ant compile-main`

compile test classes and main classes   
`ant compile`

convert classes to jar file   
`ant dist`

generate documentation, generated file is under doc/main/   
`ant doc`

run tests   
`ant test`

clean doc/dist/bin   
`ant clean`

## Run Program
```diff
- Please add input files under input/. Output files are under output/.
```

#### How to run SearchMap.txt


```
cd bin
java main.SearchMap ../input/inputfile.txt ../output/outputfile.txt
```  

#### How to run jar file
```
cd dist
java -jar SearchMap.jar ../input/inputfile.txt ../output/output.txt
```

### Others
Test files are outside src/ and inside test/. I asked the professor on 
Piazza and he said its fine.

Do not delete input/ and output/, these are very important. My test files takes input
from these two folders.