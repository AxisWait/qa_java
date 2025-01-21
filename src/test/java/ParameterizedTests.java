import com.example.Animal;
import com.example.Feline;
import com.example.Lion;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@ExtendWith(MockitoExtension.class)
public class ParameterizedTests {
    @Spy
    private Feline feline;
    @Spy
    private Animal animal;

    @ParameterizedTest
    @ValueSource(strings = {"Самец", "Самка", "Оно"})
    public void doesHaveManeTest(String sex) throws Exception {
        Lion lion = null;
        try {
            lion = new Lion(sex, feline);
            switch(sex) {
                case "Самец": assertTrue(lion.doesHaveMane());
                break;
                case "Самка": assertFalse(lion.doesHaveMane());
            }
        } catch (Exception e) {
            Exception exception =
                    assertThrows(Exception.class, () ->  new Lion(sex, feline));
            assertEquals("Используйте допустимые значения пола животного - самец или самка", exception.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"Травоядное", "Хищник"})
    void getFoodTest(String animalKind) throws Exception{
        if(animalKind.equals("Хищник"))
            assertEquals(feline.getFood(),animal.getFood(animalKind));
        else {
            Mockito.when(feline.getFood()).thenReturn(List.of("Трава", "Различные растения"));
            assertEquals(feline.getFood(),animal.getFood(animalKind));
        }
    }
}
