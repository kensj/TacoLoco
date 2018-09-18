# Taco Loco Backend

This Taco Loco backend is written using Spring Boot. 

### Included:
- Simple Rest Controller
- Web page to demonstrate functionality
- Unit testing

#### Building:
    mvn package install
    
![N|Solid](https://bitbucket.org/kensj/tacoloco/raw/599604974bb25fd249ad37057ca7e4a735ae0e26/bitbucket/Build.png)
#### Running:
    java -jar tacoloco-1.0.0-SNAPSHOT.jar
![N|Solid](https://bitbucket.org/kensj/tacoloco/raw/599604974bb25fd249ad37057ca7e4a735ae0e26/bitbucket/Running.png)  
#### Viewing on web:
	http://localhost:8081
![N|Solid](https://bitbucket.org/kensj/tacoloco/raw/56b152f8f3b9e98624489cb0eb9240e637f4c3a9/bitbucket/Example.png)


# Explanation
1. An object of type Order is initalized with the quantities of each type of taco added to the Order object
2. The object is converted to JSON and placed in the request body
3. An AJAX (POST) request is made (for mobile we would do an HTTP request)
4. The POST mapped function '/order' performs the necessary calculations and conversions
5. The function returns a JSON response with the keys: *"success", "price", "discount"*
    - Upon failure (if "success" == "false"), an extra key *"reason"* is returned
