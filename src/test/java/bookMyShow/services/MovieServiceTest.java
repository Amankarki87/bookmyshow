package bookMyShow.services;

import bookMyShow.utils.MockDatabaseConfiguration;
import com.bookMyShow.bookMyShow.constants.ApiConstant;
import com.bookMyShow.bookMyShow.exceptions.Error;
import com.bookMyShow.bookMyShow.models.Genre;
import com.bookMyShow.bookMyShow.models.Movie;
import com.bookMyShow.bookMyShow.repositories.MovieRepository;
import com.bookMyShow.bookMyShow.services.MovieServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = {MockDatabaseConfiguration.class})
public class MovieServiceTest {
    @MockBean
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveMovieTest() {
        Movie moviedto = Movie
                .builder()
                .name("Test")
                .movieGenre(Genre.COMEDY)
                .build();

        when(movieRepository.findByName(Mockito.anyString())).thenReturn(Optional.empty());
        when(movieRepository.save(moviedto)).thenReturn(moviedto);
        Movie result = movieService.save("Test",Genre.COMEDY);
        Assert.assertEquals(result.getName(), "Test");
        Assert.assertEquals(result.getMovieGenre(),Genre.COMEDY);
    }


    @Test
    public void saveMovieTestWhenConflict() {
        Movie movie = Movie
                .builder()
                .name("Test")
                .movieGenre(Genre.COMEDY)
                .build();

        when(movieRepository.findByName(Mockito.anyString())).thenReturn(Optional.ofNullable(movie));

        Error error = assertThrows(Error.class, () -> {
            movieService.save("test1",Genre.COMEDY);
        });

        Assert.assertEquals(error.getMessage(), ApiConstant.MOVIE_ALREADY_EXISTS);
        Assert.assertEquals(error.getCode(), HttpStatus.CONFLICT);
        Assert.assertEquals(error.getStatus(),ApiConstant.ERROR);
    }
}
