package com.ss.apitesting.pet;

import com.ss.apitesting.BaseTest;
import com.ss.apitesting.client.PetClient;
import com.ss.apitesting.util.ValuesGenerator;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;

public abstract class PetBaseTest extends BaseTest {
    protected PetClient petClient;
    protected long suitableId;

    @Override
    protected String getLoggerName() {
        return "PetLogger";
    }

    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        petClient = new PetClient(ContentType.JSON);
        suitableId = ValuesGenerator.generateId(1000, 10000);
    }
}
