import com.example.Cat;
import com.example.Feline;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CatTests {
    @Mock
    private Feline felineMock;

    @InjectMocks
    private Cat cat;

    @Test
    void getSoundCatTest() {
        new Cat(felineMock);
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    void getFoodCatTest() throws Exception {
        // Определение поведения мок-объекта
        Mockito.when(felineMock.eatMeat()).thenReturn(Arrays.asList("Мясо", "Молоко")); // Пример
        new Cat(felineMock);
        List<String> food = cat.getFood();
        assertTrue(food.contains("Мясо"));
        assertTrue(food.contains("Молоко"));
    }
}
