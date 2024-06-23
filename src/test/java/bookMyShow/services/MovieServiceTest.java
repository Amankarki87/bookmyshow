package bookMyShow.services;

import bookMyShow.utils.MockDatabaseConfiguration;
import com.bookMyShow.bookMyShow.constants.ApiConstant;
import com.bookMyShow.bookMyShow.exceptions.ElementAlreadyExistsException;
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

    private Movie movieDto;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        movieDto = Movie
                .builder()
                .name("Test")
                .movieGenre(Genre.COMEDY)
                .build();
    }

    @Test
    public void saveMovieTest() {

        when(movieRepository.findByName(Mockito.anyString())).thenReturn(Optional.empty());
        when(movieRepository.save(movieDto)).thenReturn(movieDto);
        Movie result = movieService.createMovie("Test",Genre.COMEDY);
        Assert.assertEquals(result.getName(), "Test");
        Assert.assertEquals(result.getMovieGenre(),Genre.COMEDY);
    }


    @Test
    public void saveMovieTestWhenConflict() {

        when(movieRepository.findByName(Mockito.anyString())).thenReturn(Optional.ofNullable(movieDto));

        ElementAlreadyExistsException error = assertThrows(ElementAlreadyExistsException.class, () -> {
            movieService.createMovie("test1",Genre.COMEDY);
        });

        Assert.assertEquals(error.getMessage(), ApiConstant.MOVIE_ALREADY_EXISTS);
    }
}
