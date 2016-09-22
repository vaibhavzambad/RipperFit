package resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class OAuthUtils {

	public static OAuthDetails createOAuthDetails(Properties config){

		OAuthDetails oauthDetails = new OAuthDetails();
		oauthDetails.setAccess_token((String) config.get(OAuthConstants.ACCESS_TOKEN));
		oauthDetails.setAccess_type_key((String) config.get(OAuthConstants.ACCESS_TYPE_KEY));
		oauthDetails.setAccess_type_value((String) config.get(OAuthConstants.ACCESS_TYPE_VALUE));
		oauthDetails.setApproval_prompt_key((String) config.get(OAuthConstants.APPROVAL_PROMPT_KEY));
		oauthDetails.setApproval_prompt_value((String) config.get(OAuthConstants.APPROVAL_PROMPT_VALUE));
		oauthDetails.setAuthentication_server_url((String) config.get(OAuthConstants.AUTHENTICATION_SERVER_URL));
		oauthDetails.setClient_id((String) config.get(OAuthConstants.CLIENT_ID));
		oauthDetails.setClient_secret((String) config.get(OAuthConstants.CLIENT_SECRET));
		oauthDetails.setGrant_type((String) config.get(OAuthConstants.GRANT_TYPE));
		oauthDetails.setUsername((String) config.get(OAuthConstants.USERNAME));
		oauthDetails.setPassword((String) config.get(OAuthConstants.PASSWORD));
		oauthDetails.setRedirect_uri((String) config.get(OAuthConstants.REDIRECT_URI));
		oauthDetails.setRefresh_token((String) config.get(OAuthConstants.REFRESH_TOKEN));
		oauthDetails.setResource_server_url((String) config.get(OAuthConstants.RESOURCE_SERVER_URL));
		oauthDetails.setScope((String) config.get(OAuthConstants.SCOPE));
		oauthDetails.setState((String) config.get(OAuthConstants.STATE));
		oauthDetails.setToken_endpoint_url((String) config.get(OAuthConstants.TOKEN_ENDPOINT_URL));

		return oauthDetails;
	}

	public static Properties getClientConfigProperties(String path){

		InputStream inputStream = OAuthUtils.class.getClassLoader().getResourceAsStream(path);
		Properties config = new Properties();
		try {
			config.load(inputStream);

		} catch (IOException e) {
			System.out.println("Could not load properties from " + path);
			e.printStackTrace();
			return null;
		}
		return config;
	}

	@SuppressWarnings("deprecation")
	public static String getAuthorizationCode(OAuthDetails oauthDetails){

		HttpPost httpPost = new HttpPost(oauthDetails.getAuthentication_server_url());
		String location = null;

		String state = oauthDetails.getState();

		List<BasicNameValuePair> parametersBody = new ArrayList<BasicNameValuePair>();

		parametersBody.add(new BasicNameValuePair(OAuthConstants.RESPONSE_TYPE, OAuthConstants.CODE));
		parametersBody.add(new BasicNameValuePair(OAuthConstants.CLIENT_ID,oauthDetails.getClient_id()));
		parametersBody.add(new BasicNameValuePair(OAuthConstants.REDIRECT_URI,oauthDetails.getRedirect_uri()));

		if(isValid(oauthDetails.getScope())){
			parametersBody.add(new BasicNameValuePair(OAuthConstants.SCOPE, oauthDetails.getScope()));
		}

		if(isValid(oauthDetails.getApproval_prompt_value())){
			parametersBody.add(new BasicNameValuePair(oauthDetails.getApproval_prompt_key(), oauthDetails.getApproval_prompt_value()));
		}

		if (isValid(oauthDetails.getAccess_type_value())) {
			parametersBody.add(new BasicNameValuePair(
					oauthDetails.getAccess_type_key(), oauthDetails.getAccess_type_value()));
		}

		if (isValid(state)) {
			parametersBody.add(new BasicNameValuePair(OAuthConstants.STATE,
					oauthDetails.getState()));
		}

		@SuppressWarnings("deprecation")
		DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
		HttpResponse httpResponse = null;
		String accessToken = null;

		try{

			httpPost.setEntity(new UrlEncodedFormEntity(parametersBody,HTTP.UTF_16));

			httpResponse = defaultHttpClient.execute(httpPost);
			int code = httpResponse.getStatusLine().getStatusCode();
			System.out.println("Code: "+code);
			if(OAuthConstants.HTTP_SEND_REDIRECT == code){
				location = getHeader(httpResponse.getAllHeaders(), OAuthConstants.LOCATION_HEADER);
			}

			if(location == null){
				System.out.println("The endpoint did not pass any valid header");
				throw new RuntimeException("The endpoint did not pass any valid header");
			}else{

				System.out.println(location);
			}

		}catch(Exception ex){

			ex.printStackTrace();
		}

		return location;
	}

	@SuppressWarnings("unchecked")
	public static Map<String,String> getAccessToken(OAuthDetails oAuthDetails , String authorizationCode){

		HttpPost httpPost = new HttpPost(oAuthDetails.getToken_endpoint_url());
		String clientId = oAuthDetails.getClient_id();
		String clientSecret = oAuthDetails.getClient_secret();
		//String scope = oAuthDetails.getScope();
		Map<String, String> map = new HashMap<String, String>();

		List<BasicNameValuePair> parametersBody = new ArrayList<BasicNameValuePair>();
		parametersBody.add(new BasicNameValuePair(OAuthConstants.GRANT_TYPE,oAuthDetails.getGrant_type()));
		parametersBody.add(new BasicNameValuePair(OAuthConstants.CODE ,authorizationCode));
		parametersBody.add(new BasicNameValuePair(OAuthConstants.CLIENT_ID,clientId));

		if(isValid(clientSecret)){

			parametersBody.add(new BasicNameValuePair(OAuthConstants.CLIENT_SECRET,clientSecret));
		}

		parametersBody.add(new BasicNameValuePair(OAuthConstants.REDIRECT_URI,oAuthDetails.getRedirect_uri()));

		@SuppressWarnings("deprecation")
		DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
		HttpResponse httpResponse = null ;
		String accessToken = null;

		try{
			httpPost.setEntity(new UrlEncodedFormEntity(parametersBody, HTTP.UTF_16));
			httpResponse = defaultHttpClient.execute(httpPost);
			int code = httpResponse.getStatusLine().getStatusCode();
			System.out.println("code in access token: "+code);

			map = handleResponse(httpResponse);
			accessToken = map.get(OAuthConstants.ACCESS_TOKEN);
			System.out.println(accessToken);

		}catch(Exception ex){

			ex.printStackTrace();
		}

		return map;

	}

	public static Map<String, String> refreshAccessToken(
			OAuthDetails oauthDetails) {
		HttpPost post = new HttpPost(oauthDetails.getToken_endpoint_url());
		String clientId = oauthDetails.getClient_id();
		String clientSecret = oauthDetails.getClient_secret();
		String scope = oauthDetails.getScope();
		String refreshToken = oauthDetails.getRefresh_token();
		Map<String, String> map = new HashMap<String, String>();

		if (!isValid(refreshToken)) {
			throw new RuntimeException(
					"Please provide valid refresh token in config file");
		}

		List<BasicNameValuePair> parametersBody = new ArrayList<BasicNameValuePair>();

		parametersBody.add(new BasicNameValuePair(OAuthConstants.GRANT_TYPE,
				OAuthConstants.REFRESH_TOKEN));

		parametersBody.add(new BasicNameValuePair(OAuthConstants.REFRESH_TOKEN,
				oauthDetails.getRefresh_token()));

		if (isValid(clientId)) {
			parametersBody.add(new BasicNameValuePair(OAuthConstants.CLIENT_ID,
					clientId));
		}

		if (isValid(clientSecret)) {
			parametersBody.add(new BasicNameValuePair(
					OAuthConstants.CLIENT_SECRET, clientSecret));
		}

		if (isValid(scope)) {
			parametersBody.add(new BasicNameValuePair(OAuthConstants.SCOPE,
					scope));
		}

		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(parametersBody, HTTP.UTF_8));

			response = client.execute(post);
			int code = response.getStatusLine().getStatusCode();
			System.out.println("code in refresh access token: "+code);

			map = handleResponse(response);
			System.out.println("refresh token: "+refreshToken);

		} catch(Exception ex){
			ex.printStackTrace();
		}

		return map;
	}

	public static Map handleResponse(HttpResponse response) {

		String contentType = OAuthConstants.JSON_CONTENT;

		if (response.getEntity().getContentType() != null) {
			contentType = response.getEntity().getContentType().getValue();
		}
		if (contentType.contains(OAuthConstants.JSON_CONTENT)) {
			return handleJsonResponse(response);
		} else if (contentType.contains(OAuthConstants.URL_ENCODED_CONTENT)) {
			return handleURLEncodedResponse(response);
		} else if (contentType.contains(OAuthConstants.XML_CONTENT)) {
			return handleXMLResponse(response);
		} else {
			// Unsupported Content type
			throw new RuntimeException(
					"Cannot handle "
							+ contentType
							+ " content type. Supported content types include JSON, XML and URLEncoded");
		}

	}

	@SuppressWarnings("unchecked")
	public static Map handleJsonResponse(HttpResponse response) {
		Map<String, String> oauthLoginResponse = null;
		// String contentType =
		// response.getEntity().getContentType().getValue();
		try {
			oauthLoginResponse = (Map<String, String>) new JSONParser()
			.parse(EntityUtils.toString(response.getEntity()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		} catch (Exception e) {
			System.out.println("Could not parse JSON response");
			throw new RuntimeException(e.getMessage());
		}
		System.out.println();
		System.out.println("********** Response Received **********");
		for (Map.Entry<String, String> entry : oauthLoginResponse.entrySet()) {
			System.out.println(String.format("  %s = %s", entry.getKey(),
					entry.getValue()));
		}
		return oauthLoginResponse;
	}

	public static Map handleURLEncodedResponse(HttpResponse response) {
		Map<String, Charset> map = Charset.availableCharsets();
		Map<String, String> oauthResponse = new HashMap<String, String>();
		Set<Map.Entry<String, Charset>> set = map.entrySet();
		Charset charset = null;
		HttpEntity entity = response.getEntity();

		System.out.println();
		System.out.println("********** Response Received **********");

		for (Map.Entry<String, Charset> entry : set) {
			System.out.println(String.format("  %s = %s", entry.getKey(),
					entry.getValue()));
			if (entry.getKey().equalsIgnoreCase(HTTP.UTF_8)) {
				charset = entry.getValue();
			}
		}

		try {
			List<NameValuePair> list = URLEncodedUtils.parse(
					EntityUtils.toString(entity), Charset.forName(HTTP.UTF_8));
			for (NameValuePair pair : list) {
				System.out.println(String.format("  %s = %s", pair.getName(),
						pair.getValue()));
				oauthResponse.put(pair.getName(), pair.getValue());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Could not parse URLEncoded Response");
		}

		return oauthResponse;
	}

	public static Map handleXMLResponse(HttpResponse response) {
		Map<String, String> oauthResponse = new HashMap<String, String>();
		try {

			String xmlString = EntityUtils.toString(response.getEntity());
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			InputSource inStream = new InputSource();
			inStream.setCharacterStream(new StringReader(xmlString));
			Document doc = db.parse(inStream);

			System.out.println("********** Response Receieved **********");
			parseXMLDoc(null, doc, oauthResponse);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"Exception occurred while parsing XML response");
		}
		return oauthResponse;
	}

	public static void parseXMLDoc(Element element, Document doc,
			Map<String, String> oauthResponse) {
		NodeList child = null;
		if (element == null) {
			child = doc.getChildNodes();

		} else {
			child = element.getChildNodes();
		}
		for (int j = 0; j < child.getLength(); j++) {
			if (child.item(j).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
				org.w3c.dom.Element childElement = (org.w3c.dom.Element) child
						.item(j);
				if (childElement.hasChildNodes()) {
					System.out.println(childElement.getTagName() + " : "
							+ childElement.getTextContent());
					oauthResponse.put(childElement.getTagName(),
							childElement.getTextContent());
					parseXMLDoc(childElement, null, oauthResponse);
				}

			}
		}
	}


	public static String getHeader(Header[] headers, String name) {

		String header = null;
		if (headers != null) {
			for (Header h : headers) {
				if (h.getName().equalsIgnoreCase(name)) {
					header = h.getValue();
					break;
				}
			}
		}
		return header;
	}
	
	public static Properties getClientConfigProps(String path) {
		InputStream is = OAuthUtils.class.getClassLoader().getResourceAsStream(path);
		Properties config = new Properties();
		try {
			config.load(is);
		} catch (IOException e) {
			System.out.println("Could not load properties from " + path);
			e.printStackTrace();
			return null;
		}
		return config;
	}

	public static boolean isValid(String str) {
		return (str != null && str.trim().length() > 0);
	}
	
	public static List<String> validateInput(OAuthDetails input) {

		List<String> invalidProps = new ArrayList<String>();

		if (input == null) {
			invalidProps
					.add("The OAuth2Details bean itself is null. Please check the OAuth2Client code");
			return invalidProps;
		}

		String grantType = input.getGrant_type();

		if (!isValid(grantType)) {
			System.out.println("Please provide valid value for grant_type");
			invalidProps.add(OAuthConstants.GRANT_TYPE);
		}

		if (!isValid(input.getAuthentication_server_url())) {
			System.out
					.println("Please provide valid value for authentication server url");
			invalidProps.add(OAuthConstants.AUTHENTICATION_SERVER_URL);
		}

		if (!isValid(input.getToken_endpoint_url())) {
			System.out
					.println("Please provide valid value for token endpoint url");
			invalidProps.add(OAuthConstants.TOKEN_ENDPOINT_URL);
		}

		if (!isValid(input.getApproval_prompt_value())) {
			System.out
					.println("Please provide valid value for approval prompt value");
			invalidProps.add(OAuthConstants.APPROVAL_PROMPT_VALUE);
		}

		if (!isValid(input.getApproval_prompt_key())) {
			System.out
					.println("Please provide valid value for approval prompt key");
			invalidProps.add(OAuthConstants.APPROVAL_PROMPT_KEY);
		}

		if (!isValid(input.getRedirect_uri())) {
			System.out.println("Please provide valid value for redirect uri");
			invalidProps.add(OAuthConstants.REDIRECT_URI);
		}

		return invalidProps;

	}
	
	public static Map<String, String> getProtectedResource(
			OAuthDetails oauthDetails) {
		String resourceURL = oauthDetails.getResource_server_url();

		Map<String, String> map = new HashMap<String, String>();

		HttpGet get = new HttpGet(resourceURL);
		get.addHeader(OAuthConstants.AUTHORIZATION,getAuthorizationHeaderForAccessToken(oauthDetails.getAccess_token()));
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		String accessToken = null;
		int code = -1;
		try {
			response = client.execute(get);
			code = response.getStatusLine().getStatusCode();
			if (code == OAuthConstants.HTTP_UNAUTHORIZED
					|| code == OAuthConstants.HTTP_FORBIDDEN) {
				// Access token is invalid or expired.Regenerate the access
				// token
				System.out
						.println("Access token is invalid or expired. Refreshing access token....");
				map = refreshAccessToken(oauthDetails);
				accessToken = map.get(OAuthConstants.ACCESS_TOKEN);

				if (isValid(accessToken)) {
					// update the access token
					System.out.println("New access token: " + accessToken);
					oauthDetails.setAccess_token(accessToken);
					get.removeHeaders(OAuthConstants.AUTHORIZATION);
					get.addHeader(OAuthConstants.AUTHORIZATION,
							getAuthorizationHeaderForAccessToken(oauthDetails.getAccess_token()));
					get.releaseConnection();
					response = client.execute(get);
					code = response.getStatusLine().getStatusCode();
					if (code >= 400) {
						throw new RuntimeException(
								"Could not access protected resource. Server returned http code: "
										+ code);

					}

				} else {
					throw new RuntimeException("Could not refresh access token");
				}

			}

			map = handleResponse(response);

		}catch(Exception ex){
			
			ex.printStackTrace();
		}
		finally {
			get.releaseConnection();
		}

		return map;
	}
	
	public static String getAuthorizationHeaderForAccessToken(String accessToken) {
		return OAuthConstants.BEARER + " " + accessToken;
	}

}
