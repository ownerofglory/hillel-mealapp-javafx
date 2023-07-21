package ua.ithillel.mealapp.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ua.ithillel.mealapp.client.MealClient;
import ua.ithillel.mealapp.dao.FavouriteMealDao;
import ua.ithillel.mealapp.exception.MealAppException;
import ua.ithillel.mealapp.model.dto.MealResponseDto;
import ua.ithillel.mealapp.model.entity.MealEntity;
import ua.ithillel.mealapp.model.mapper.MealMapper;
import ua.ithillel.mealapp.model.mapper.MealMapperDefault;
import ua.ithillel.mealapp.model.vm.MealItemVm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class MealServiceDefaultTest {
    private MealSearchService mealSearchService;

    @Mock
    private MealClient mealClientMock;
    @Mock
    private FavouriteMealDao favouriteMealDaoMock;
    private MealMapper mealMapper = new MealMapperDefault();

    private MealResponseDto mockSearchResponse;

    @BeforeEach
    public void setUp() {
        // dependencies: client and dao
        initializeMockResponse();

        openMocks(this);

//        mealClientMock = mock();
//        favouriteMealDaoMock = mock();

        mealSearchService = new MealSearchServiceDefault(mealClientMock, mealMapper, favouriteMealDaoMock);
    }

    @Test
    public void searchMealsTest_returnsListOfMeals() {
        System.out.println("Starint test");

        MealResponseDto mockResponse = new MealResponseDto();
        mockResponse.setMeals(new ArrayList<>());

        when(mealClientMock.searchMealByName(anyString())).thenReturn(mockResponse);

        String testName = "test name";

        List<MealItemVm> mealItemVms = mealSearchService.searchMeals(testName);

        assertNotNull(mealItemVms);
    }

    @Test
    public void searchMealsTest_returnsNonEmptyListOfMeals() {
        System.out.println("Starint test");


        when(mealClientMock.searchMealByName(anyString())).thenReturn(mockSearchResponse);

        String testName = "test name";

        List<MealItemVm> mealItemVms = mealSearchService.searchMeals(testName);

        assertNotNull(mealItemVms);
        assertNotEquals(mealItemVms.size(), 0);
    }

    @Test
    public void getFavouriteMealsTest_returnsListOfMeals() throws MealAppException {
        when(favouriteMealDaoMock.findAll(anyBoolean())).thenReturn(new ArrayList<>());

        List<MealItemVm> favouriteMeals = mealSearchService.getFavouriteMeals(true);

        assertNotNull(favouriteMeals);
    }

    @Test
    public void getFavouriteMealsTest_returnsNonEmptyListOfMeals() throws MealAppException {
        MealEntity mockMeal = new MealEntity();
        mockMeal.setName("mock name");
        mockMeal.setRecipe("Mock recipe");
        mockMeal.setImageUrl("Mock image");
        mockMeal.setIngredients(new ArrayList<>());

        List<MealEntity> mockMeals = List.of(mockMeal);

        when(favouriteMealDaoMock.findAll(anyBoolean())).thenReturn(mockMeals);

        List<MealItemVm> favouriteMeals = mealSearchService.getFavouriteMeals(true);

        assertNotNull(favouriteMeals);
        assertNotEquals(favouriteMeals.size(), 0);
    }

    private void initializeMockResponse() {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("mealclient-mock-response.json");
        try (InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
             BufferedReader br = new BufferedReader(inputStreamReader);
        ) {
            StringBuffer stringBuffer = new StringBuffer();
            br.lines().forEach(stringBuffer::append);

            String fileContent = stringBuffer.toString();

            ObjectMapper objectMapper = new ObjectMapper();
            mockSearchResponse = objectMapper.readValue(fileContent, MealResponseDto.class);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
