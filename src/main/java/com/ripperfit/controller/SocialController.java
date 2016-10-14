package com.ripperfit.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.ripperfit.model.Employee;


@RequestMapping(value="/social")
@RestController
public class SocialController {

	@RequestMapping(value = "/getDetails", method = RequestMethod.POST)
	public ResponseEntity<Employee> getDetails(@RequestBody String authCode) throws FileNotFoundException, IOException {

		String CLIENT_SECRET_FILE = getClass().getClassLoader().getResource("client_secret.json").getFile();

		// Exchange auth code for access token
		GoogleClientSecrets clientSecrets =
				GoogleClientSecrets.load(
						JacksonFactory.getDefaultInstance(), new FileReader(CLIENT_SECRET_FILE));
		GoogleTokenResponse tokenResponse =
				new GoogleAuthorizationCodeTokenRequest(
						new NetHttpTransport(),
						JacksonFactory.getDefaultInstance(),
						"https://www.googleapis.com/oauth2/v4/token",
						clientSecrets.getDetails().getClientId(),
						clientSecrets.getDetails().getClientSecret(),
						authCode,
						"http://localhost:8080").execute();

		// Specify the same redirect URI that you use with your web
		// app. If you don't have a web version of your app, you can
		// specify an empty string.


		String accessToken = tokenResponse.getAccessToken();

		// Use access token to call API
		GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);

		// Get profile info from ID token
		GoogleIdToken idToken = tokenResponse.parseIdToken();
		GoogleIdToken.Payload payload = idToken.getPayload();
		String userId = payload.getSubject();  // Use this value as a key to identify a user.
		String email = payload.getEmail();
		boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
		String name = (String) payload.get("name");
		String pictureUrl = (String) payload.get("picture");
		if(userId != null){
			Employee employee = new Employee();
			if(email != null){
				employee.setEmail(email);
			}
			if(name != null){
				String lastName = "";
				String[] fullName = name.split(" ");
				employee.setFirstName(fullName[0]);
				for(int i = 1 ; i < fullName.length ; ++i){
					lastName += fullName[i]+" ";
				}
				employee.setLastName(lastName);
			}
			if(pictureUrl != null){
				employee.setProfilePicture(pictureUrl);
			}
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}else{
			return new ResponseEntity<Employee>(HttpStatus.UNAUTHORIZED);
		}
	}
}