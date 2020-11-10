package com.ss.apitesting.pet.negative;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.builder.PetBuilder;
import com.ss.apitesting.client.PetClient;
import com.ss.apitesting.models.pet.StringPetModel;
import com.ss.apitesting.util.ValuesGenerator;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Operation with pets tests")
@Feature("Delete pet negative test suite")
public class DeletePetNegativeTest {

    protected PetClient petClient;

    @BeforeClass
    public void init() {
        petClient = new PetClient(ContentType.JSON);
    }

    @DataProvider(name = "invalidId")
    public Object[][] invalidIdData() {
        return new Object [][] {
                {
                        "0"
                },
                {
                        "-10"
                },
                {
                        String.valueOf(Long.MIN_VALUE)
                },
                {
                        "ade"
                },
                {
                        "120332093029302930129301903894812"
                },
                {
                        "-12338928392832830820809480280218"
                }
        };
    }

    @Test(dataProvider = "invalidId")
    public void deleteInvalidId(String id) {
        Response deleteResponse = petClient.deleteById(String.valueOf(id));
        BaseAssertion deleteAssertion = new BaseAssertion(deleteResponse);
        deleteAssertion.statusCode(404);
    }

    @Test
    public void deleteByFreeId() {
        // Precondition
        long suitableId = ValuesGenerator.generateId(1000, 10000);
        Response createResponse = petClient.createPet(PetBuilder.petWith().id(suitableId).name("hundo").status("sold").build());
        BaseAssertion createAssertion = new BaseAssertion(createResponse);
        createAssertion.statusCode(200);

        petClient.deleteById(String.valueOf(suitableId));
        Response getResponse = petClient.getById(String.valueOf(suitableId));
        BaseAssertion getAssertion = new BaseAssertion(getResponse);
        getAssertion.statusCode(404);

        // Deleting free id
        Response deleteResponse = petClient.deleteById(String.valueOf(suitableId));
        BaseAssertion deleteAssertion = new BaseAssertion(deleteResponse);
        deleteAssertion.statusCode(404);
    }
}
