package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
/*
 * UserEndPoints.java
 * created for perform Create,Read,Update,Delete requests the user API
 */

public class userEndPoints2 {

	public static ResourceBundle getURL() {
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}

	public static Response createUser(User payload) {

		String post_Url = getURL().getString("post_url");

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(post_Url);
		return response;
	}

	public static Response readUser(String userName) {
		String get_Url = getURL().getString("get_url");
		Response response = given().pathParam("username", userName).when().get(get_Url);
		return response;
	}

	public static Response updateUser(String userName, User payload) {
		String update_Url = getURL().getString("update_url");
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", userName).body(payload).when().put(update_Url);
		return response;
	}

	public static Response deleteUser(String userName) {
		String delete_Url = getURL().getString("delete_url");
		Response response = given().pathParam("username", userName).when().delete(delete_Url);
		return response;
	}
}
