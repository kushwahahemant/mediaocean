Securing Restful Web Services with Spring Security and OAuth2 
===========================================================================

The flow of application will go something like this:

1) User sends a GET request to server with five parameters: grant_type, username, password, client_id, client_secret; something like this 
http://localhost:8080/retail-store-secure/oauth/token?grant_type=password&client_id=retail-store-secure&client_secret=retail-store-secure&username=hemant&password=hemant@1234


2) Server validates the user with help of spring security, and if the user is authenticated, OAuth generates a access token and send sends back to user in following format.
{
  "access_token": "ccbe97d2-dfac-4b4f-b5a0-604bfe4e67e5",
  "token_type": "bearer",
  "refresh_token": "031390e1-85ed-4519-b242-578da4126739",
  "expires_in": 119
}


Here we got access_token for further communication with server or to get some protected resourses(API’s), it mentioned a expires_in time that indicates the validation time of the token and a refresh_token that is being used to get a new token when token is expired.

3) We access protected resources by passing this access token as a parameter, the request goes something like this:

http://localhost:8080/retail-store-secure/api/product/create/?access_token=ccbe97d2-dfac-4b4f-b5a0-604bfe4e67e5
Here http://localhost:8080/retail-store-secure is the server path, and /api/product/create Is an API URL that create product and is being protected to be accessed. 

4) If the token is not expired and is a valid token, the requested resources will be returned.

5) In case the token is expired, user needs to get a new token using its refreshing token that was accepted in step(2). A new access token request after expiration looks something like this:
http://localhost:8080/retail-store-secure/oauth/token?grant_type=refresh_token&client_id=retail-store-secure&client_secret=retail-store-secure&refresh_token=031390e1-85ed-4519-b242-578da4126739

And you will get a new access token along with a new refresh token.