package ru.bureau.fal.controllers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.UTF8DataInputJsonParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import ru.bureau.fal.FalApplicationTests;
import ru.bureau.fal.model.Car;
import ru.bureau.fal.service.AppService;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.bureau.fal.TestData.OBJECT_MAPPER;
import static ru.bureau.fal.TestData.carOpel;
import static ru.bureau.fal.TestData.carPathfinder;

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

    @Before
    public void setUp() throws Exception {

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
            Car car = OBJECT_MAPPER.readValue(carJ.toString(), Car.class);
            returnedCars.add(car);
        }
        Assert.assertEquals(carPathfinder, returnedCars.get(0));
        Assert.assertEquals(carOpel, returnedCars.get(1));
    }

    @Test
    public void getCarById() throws Exception {
        MvcResult result = mockMvc.perform(get(REST_URL + "cars/100002"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();
        String jsonStr = result.getResponse().getContentAsString();
        Car car = OBJECT_MAPPER.readValue(jsonStr, Car.class);
        Assert.assertEquals(carPathfinder, car);
    }

    @Test
    public void getTripsByCarId() throws Exception {
        mockMvc.perform(get(REST_URL + "trips/100002"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void PutTripsByCarId() {
    }

    @Test
    public void createOrUpdateCar() {
        Car car = carPathfinder;
        car.setDescription("New Pafick");
        Car createdCar = appService.createOrUpdateCar(car);
        Assert.assertNotNull(createdCar);
        appService.deleteCar(createdCar.getId());
    }

    @Test
    public void createOrUpdateUser() {
    }
}