# Checkout Service Solution

## Introduction
This service is designed and developed as a microservice. Here the MVC software design pattern is followed. There two features available at the moment for this services, which are as below :
+ customer can add a product to a basket
+ customer can remove a product from basket

## Feature(s)
+ Add Product : To use the add product feature the following rest endpoint is developed.
  ```/api/v1/checkout/addProduct``` </br>
  </br>
  This endpoint takes two request parameters ```customerId``` and ```productId```.
  It uses a ```POST``` Http method. 
  </br>
+ Remove Product :To use the add product feature the following rest endpoint is developed.
  ```/api/v1/checkout/removeProduct``` </br>
  </br>
  This endpoint takes two request parameters ```customerId``` and ```productId```.
  It uses a ```PUT``` Http method. </br>
  </br>
  More details can be found on Swagger API documentation via
  ```/swagger``` endpoint on port ```8083```.
  
## Steps to run the service
 Note : To run this service and get expected out there is a dependency of another service named ```products-service```. </br>
  So, before running the ```checkout-service```, ```products-service``` has to be running. </br>
  </br>
  Hence, to achieve that a ```docker-compose``` file is needed. As per the given resource already a ```docker-compose.yml``` was present in the working directory named ```java-spring-take-home-sumanas27```. 
  </br>
  So, this file is updated with one more service information in the name of ```checkout```.
  </br>
  </br>
  To build and run the docker-compose file following commands are necessary:
  ```dockerfile
docker-compose up --build
```
For already existing images, the following commands are necessary:
```dockerfile
docker-compose rm -f
docker-compose up --build
```



  
### Running the service independently

The following commands are useful if the service has to run independently. 
</br>To ensure smooth build and running processes, a Dockerfile is created in the source code directory. </br>
</br>To build this application following command is necessary:</br>
```dockerfile
docker build -t checkout-service .
```  

To run this application following command is necessary
```dockerfile
docker run -p 8083:8083 checkout-service
```

To ensure that docker image is running, following command will give the overview of the image.
```dockerfile
docker ps
```

