package api.test;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndPoints2;
import api.payload.User;
import api.utilities.FileDelete;
import io.restassured.response.Response;

public class UserTests extends FileDelete{

	Faker faker;
	User userPayload;

	public org.apache.logging.log4j.Logger logger;
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
//		logs
		logger = LogManager.getLogger(this.getClass());
		
//		Old log file deletion
//		fileDelete(System.getProperty("user.dir")+"//logs//app.log");
//		logger.info("File delted read");
	}

	@Test(priority = 1)
	public void testPostUser() {
		Response response = userEndPoints2.createUser(userPayload);
		response.then().log().all();
		logger.debug("Creating User");
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.debug("User is created");
	}

	@Test(priority = 2)
	public void testGetUserByName() {
		logger.debug("Reading user info");
		Response response = userEndPoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.debug("Reading user completed");
	}

	@Test(priority = 3)
	public void testUpdateUserByName() {
		logger.debug("Updating user");
//		Update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response response = userEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);

//		Checking data after update
		Response responseAfterUpdate = userEndPoints2.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		logger.debug("Updating user completed");
	}

	@Test(priority = 4)
	public void testDeleteUser() {
		logger.debug("Deleting user");
		Response response = userEndPoints2.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.debug("Deleting user completed");
	}

}
