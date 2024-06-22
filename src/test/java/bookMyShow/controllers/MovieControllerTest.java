package bookMyShow.controllers;

import com.bookMyShow.bookMyShow.constants.ApiConstant;
import com.bookMyShow.bookMyShow.constants.MovieConstant;
import com.bookMyShow.bookMyShow.controllers.MovieController;
import com.bookMyShow.bookMyShow.models.Genre;
import com.bookMyShow.bookMyShow.models.Movie;
import com.bookMyShow.bookMyShow.services.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
@WebMvcTest(value=MovieController.class)
@ContextConfiguration(classes = {MovieController.class})
public class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    private final String URL = ApiConstant.API_V1_VERSIONING + MovieConstant.MOVIE_BASE_URL;

    @Test
    public void testSaveMovie() throws Exception {
        Movie movie = Movie
                .builder()
                .name("Test")
                .movieGenre(Genre.COMEDY)
                .build();

        String json = new ObjectMapper().writeValueAsString(movie);

        Mockito.when(movieService.createMovie(Mockito.anyString(),Mockito.any())).thenReturn(movie);
        mockMvc.perform(
                MockMvcRequestBuilders.post(URL)
                        .content(json).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void testSaveMovieWhenError() throws Exception {
        Movie movie = Movie
                .builder()
                .movieGenre(Genre.COMEDY)
                .build();

        String json = new ObjectMapper().writeValueAsString(movie);
        mockMvc.perform(
                MockMvcRequestBuilders.post(URL)
                        .content(json).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

}
