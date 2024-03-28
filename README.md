## Using the application:

To get the services up and running use  **docker-compose up -d ** ,inside the root folder of the project.

## Docker Images for microservices:
    a.	API GATEWAY: https://hub.docker.com/r/muskan001/api-gateway
    
    b.	NAMING SERVER: https://hub.docker.com/r/muskan001/naming-server
    
    c.	IDENTITY SERVICE: https://hub.docker.com/r/muskan001/identityservice
    
    d.	ORDER SERVICE: https://hub.docker.com/r/muskan001/order-service
    
    e.	RESTRAUNT SERVICE: https://hub.docker.com/r/muskan001/restraunt-service
    
    f.	PAYMENT SERVICE: https://hub.docker.com/r/muskan001/payment-service
    
    g.	NOTIFCATION SERVICE: https://hub.docker.com/r/muskan001/notification-service

## To add new records in restaurant table after setting up the project with docker compose
After Application has been started with docker-compose, type localhost:8080 in your browser to visit Adminer UI/Console to manage all databases related to application.
    
![image](https://github.com/crazylot/Restraunt-Service-Backend/assets/63306186/bc6c2e5b-9293-4727-a9c2-4655e1b4f419)

Add the following details for login:

System: MySQL

Server: mysql

Username: admin

Password: root


Now After login, **go to restraunt_service >> t_restraunt_info >> Insert **  to add a new item to restraunt_info table like given in below example.  Adding Some Default Restaurant and Menu Items for testing functionality is necessary for Restaurant Service Application.
Similarly add menu items for this restaurant in t_menu_item table and map the corresponding menu item with restaurant using t_restraunt_info_menu items TABLE.

### t_restraunt_info TABLE:

![image](https://github.com/crazylot/Restraunt-Service-Backend/assets/63306186/780ac21d-08a1-41f8-9198-4b08aa2de931)

### t_menu_item TABLE:

![image](https://github.com/crazylot/Restraunt-Service-Backend/assets/63306186/7a6ff2d8-bd48-48e2-adac-3194c12512f6)

### t_restraunt_info_menu items TABLE:
![image](https://github.com/crazylot/Restraunt-Service-Backend/assets/63306186/834cacd6-f1da-495c-920f-0075f4f56526)

