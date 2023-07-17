package ua.ithillel.mealapp.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.ithillel.mealapp.model.dto.AreaResponseDto;
import ua.ithillel.mealapp.model.dto.CategoryResponseDto;
import ua.ithillel.mealapp.model.dto.CategoryShortResponseDto;
import ua.ithillel.mealapp.model.dto.MealResponseDto;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TheMealDbClient implements MealClient {
    private final static String BASE_URL = "https://www.themealdb.com/api/json/v1";
    private final HttpClient client;
    private final ObjectMapper mapper;

    public TheMealDbClient(HttpClient client, ObjectMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    @Override
    public MealResponseDto getMealById(String id) {
        https://www.themealdb.com/api/json/v1/1/lookup.php?i=52772
        try {
            URI uri = new URI(BASE_URL + "/1/lookup.php?i=" + id);
            HttpRequest request = HttpRequest.newBuilder().GET().uri(uri).build();

            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            if (response.statusCode() == 200) {
                InputStream inputStream = response.body();
                return mapper.readValue(inputStream, MealResponseDto.class);
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public MealResponseDto searchMealByName(String name) {
        // https://www.themealdb.com/api/json/v1/1/search.php?s=as
        try {
            URI uri = new URI(BASE_URL + "/1/search.php?s=" + name);
            HttpRequest request = HttpRequest.newBuilder().GET().uri(uri).build();

            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            if (response.statusCode() == 200) {
                InputStream inputStream = response.body();
                return mapper.readValue(inputStream, MealResponseDto.class);
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public MealResponseDto searchMealByCategory(String categoryName) {
        // https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood
        try {
            URI uri = new URI(BASE_URL + "/1/filter.php?c=" + categoryName);
            HttpRequest request = HttpRequest.newBuilder().GET().uri(uri).build();

            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            if (response.statusCode() == 200) {
                InputStream inputStream = response.body();
                return mapper.readValue(inputStream, MealResponseDto.class);
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public MealResponseDto searchMealByArea(String areaName) {
        // // https://www.themealdb.com/api/json/v1/1/filter.php?a=Seafood
        try {
            URI uri = new URI(BASE_URL + "/1/filter.php?a=" + areaName);
            HttpRequest request = HttpRequest.newBuilder().GET().uri(uri).build();

            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            if (response.statusCode() == 200) {
                InputStream inputStream = response.body();
                return mapper.readValue(inputStream, MealResponseDto.class);
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public CategoryResponseDto getCategories() {
        // https://www.themealdb.com/api/json/v1/1/categories.php
        try {
            URI uri = new URI(BASE_URL + "/1/categories.php");
            HttpRequest request = HttpRequest.newBuilder().GET().uri(uri).build();

            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            if (response.statusCode() == 200) {
                InputStream inputStream = response.body();
                return mapper.readValue(inputStream, CategoryResponseDto.class);
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public CategoryShortResponseDto getCategoriesList() {
        // https://www.themealdb.com/api/json/v1/1/list.php?c=list
        try {
            URI uri = new URI(BASE_URL + "/1/list.php?c=list");
            HttpRequest request = HttpRequest.newBuilder().GET().uri(uri).build();

            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            if (response.statusCode() == 200) {
                InputStream inputStream = response.body();
                return mapper.readValue(inputStream, CategoryShortResponseDto.class);
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public AreaResponseDto getAreas() {
        // https://www.themealdb.com/api/json/v1/1/filter.php?a=Mexican
        try {
            URI uri = new URI(BASE_URL + "/1/list.php?a=list");
            HttpRequest request = HttpRequest.newBuilder().GET().uri(uri).build();

            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            if (response.statusCode() == 200) {
                InputStream inputStream = response.body();
                return mapper.readValue(inputStream, AreaResponseDto.class);
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
