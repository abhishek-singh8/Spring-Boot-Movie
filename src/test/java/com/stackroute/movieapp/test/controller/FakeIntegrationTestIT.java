package com.stackroute.movieapp.test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class FakeIntegrationTestIT {

    @Test
    public void fakeTest(){
        assertTrue(true);
    }
}
