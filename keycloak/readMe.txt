keycloak url: http://localhost:8181
credentials: admin/admin

1. select spring-boot-microservices-realm from left top drop down.
2. Realm Settings -> Endpoints -> click first link
i.e. http://localhost:8181/realms/spring-boot-microservices-realm/.well-known/openid-configuration

token_endpoint=http://localhost:8181/realms/spring-boot-microservices-realm/protocol/openid-connect/token
3. Use above URL for postman client authorization tab to generate token for /api/product call.
4. "issuer":"http://localhost:8181/realms/spring-boot-microservices-realm" //use this in api gateway application.yaml
5. For client secret:
    Clients -> spring-cloud-client -> copy Client Secret value or re-generate

product service url via api gateway: GET http://localhost:8282/api/product

Postman Authorization setup:

1. Authorization -> Type -> OAuth 2.0
2. Grant Type: Client Credentials
3. Access Token URL: above token_endpoint -> http://localhost:8181/realms/spring-boot-microservices-realm/protocol/openid-connect/token
4. Client ID: spring-cloud-client
5. Client Secret: copy value from step 5
    tTJh9Xu5cqI8edoHcHSGmDONPfZc7W3y
6. Get New Access Token
7. Use Token

order service url via api gateway: POST http://localhost:8282/api/order
body:
{
    "orderLineItemsDtoList":[
    {
        "skuCode":"iphone_13",
        "price":1200,
        "quantity":1
    }
    ]
}
