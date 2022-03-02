# Pizza App

## Order Pizza Application using Spring Boot with Microservices

- The in-memory database used is H2.
- For security, JWT Token authorization using Microservices in the Cloud with Kubernetes and Istio.
- RESTApis is used for communication between Microservices.
- Services speak to each other via Istio service mesh.

## Workflow of the Application

![workflow](pizza-app.png)

## Steps to run the application on Kubernetes
##### 1. Deploy the services on defaut namespace
	
	kubectl apply -f kubernetes/order-service.yml

	kubectl apply -f kubernetes/pizza-service.yml
	
##### 2. Install Istio using the default profile
	
	istioctl install
	
##### 3. Enable Istio injection for the microservices
	
	kubectl label namespace default istio-injection=enabled
	
##### 4. Set ingress gateway on order-service
	
	kubectl apply -f kubernetes/order-gateway.yml
	
##### 5. Set request authorization and authorization policy on order-service
	
	kubectl apply -f kubernetes/order-auth.yml

##### 6. Test the Order pizza API using postman collection or cURL

Postman collection:

[https://www.getpostman.com/collections/4b390c1a904aa8315659](https://www.getpostman.com/collections/4b390c1a904aa8315659)

cURL:

	curl --location --request POST 'http://localhost:80/order' \
	--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkRIRmJwb0lVcXJZOHQyenBBMnFYZkNtcjVWTzVaRXI0UnpIVV8tZW52dlEiLCJ0eXAiOiJKV1QifQ.eyJleHAiOjQ2ODU5ODk3MDAsImZvbyI6ImJhciIsImlhdCI6MTUzMjM4OTcwMCwiaXNzIjoidGVzdGluZ0BzZWN1cmUuaXN0aW8uaW8iLCJzdWIiOiJ0ZXN0aW5nQHNlY3VyZS5pc3Rpby5pbyJ9.CfNnxWP2tcnR9q0vxyxweaF3ovQYHYZl82hAUsn21bwQd9zP7c-LS9qd_vpdLG4Tn1A15NxfCjp5f7QNBUo-KC9PJqYpgGbaXhaGx7bEdFWjcwv3nZzvc7M__ZpaCERdwU7igUmJqYGBYQ51vr2njU9ZimyKkfDe3axcyiBZde7G6dabliUosJvvKOPcKIWPccCgefSj_GNfwIip3-SsFdlR7BtbVUcqR-yv-XOxJ3Uc1MI0tz3uMiiZcyPV7sNCU4KRnemRIMHVOfuvHsU60_GhGbiSFzgPTAa9WTltbnarTbxudb_YEOx12JiwYToeX0DCPb43W1tzIBxgm8NxUg' \
	--header 'Content-Type: application/json' \
	--data-raw '{
    "name": "Margherita Pizza",
    "quantity": 2,
    "price": 200
	}'