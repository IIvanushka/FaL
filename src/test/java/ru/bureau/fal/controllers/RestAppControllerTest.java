package ru.bureau.fal.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import ru.bureau.fal.FalApplicationTests;
import ru.bureau.fal.model.Car;
import ru.bureau.fal.model.Trip;
import ru.bureau.fal.service.AppService;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.bureau.fal.TestData.*;
import static ru.bureau.fal.Util.JacksonObjectMapper.*;

public class RestAppControllerTest extends FalApplicationTests {

    private static final String REST_URL = RestAppController.REST_URL;

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();

    static {
        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
    }

    private MockMvc mockMvc;

    @Autowired
    private AppService appService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(CHARACTER_ENCODING_FILTER)
                .build();
    }

    @Test
    public void getAllUsers() throws Exception {

        mockMvc.perform(get(REST_URL + "users"))
//                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"id\":100000,\"name\":\"Valera\",\"email\":\"valer@mail.ru\"},{\"id\":100001,\"name\":\"Vasya\",\"email\":\"vas@mail.ru\"}]"));
    }

    @Test
    public void getAllCarsByUserId() throws Exception {
        MvcResult result = mockMvc.perform(get(REST_URL + "cars"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String jsonStr = result.getResponse().getContentAsString();
        JSONArray jsonArray = new JSONArray(jsonStr);
        List<Car> returnedCars = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject carJ = jsonArray.getJSONObject(i);
            Car car = getMapper().readValue(carJ.toString(), Car.class);
            returnedCars.add(car);
        }
        Assert.assertEquals(CAR_PATHFINDER, returnedCars.get(0));
        Assert.assertEquals(CAR_OPEL, returnedCars.get(1));
    }

    @Test
    public void getCarById() throws Exception {
        MvcResult result = mockMvc.perform(get(REST_URL + "cars/100002"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();
        String jsonStr = result.getResponse().getContentAsString();
        Car car = getMapper().readValue(jsonStr, Car.class);
        Assert.assertEquals(CAR_PATHFINDER, car);
    }

    @Test
    public void getTripsByCarId() throws Exception {
        MvcResult result = mockMvc.perform(get(REST_URL + "trips/100002"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String jsonStr = result.getResponse().getContentAsString();
        JSONArray jsonArray = new JSONArray(jsonStr);
        Assert.assertEquals(2, jsonArray.length());
    }

    @Test
    public void PostTripByCarId() throws Exception {
        MvcResult result = mockMvc.perform(post(REST_URL + "/trips/" + CAR1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString(TEST_TRIP)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String jsonStr = result.getResponse().getContentAsString();
        Trip trip = getMapper().readValue(jsonStr, Trip.class);
        Assert.assertEquals(trip, TEST_TRIP);
    }

    @Test
    public void createOrUpdateCar() throws Exception {
        Car car = CAR_PATHFINDER;
        car.setDescription("New Pafick");

        MvcResult result = mockMvc.perform(post(REST_URL + "/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString(car)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String jsonStr = result.getResponse().getContentAsString();
        Car createdCar = getMapper().readValue(jsonStr, Car.class);
        Assert.assertEquals(CAR_PATHFINDER, createdCar);
        CAR_PATHFINDER.setDescription("Pathfinder");
    }

    @Test
    public void createOrUpdateUser() {
    }
}