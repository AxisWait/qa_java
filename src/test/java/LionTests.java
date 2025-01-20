import com.example.Feline;
import com.example.Lion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;


@ExtendWith(MockitoExtension.class)
public class LionTests {

@Spy
  private Feline feline;

    static Stream<Arguments> sexTestArguments() {
        return Stream.of(
                arguments("Самец", true),
                arguments("Самка", false),
                arguments("Чужой", false)
        );
    }

    @ParameterizedTest
    @MethodSource("sexTestArguments")
    public void doesHaveManeTest(String sex, boolean hasMane) throws Exception {
        Lion lion = null;
        try {
            lion = new Lion(sex, feline);
            assertEquals(hasMane, lion.doesHaveMane());
        } catch (Exception e) {
            Exception exception =
                    assertThrows(Exception.class, () ->  new Lion(sex, feline));
            assertEquals("Используйте допустимые значения пола животного - самец или самка", exception.getMessage());
        }
    }
    @Test
    void getKittensWithParamTest() throws Exception{
        Lion lion = new Lion("Самец", feline);
        assertEquals(feline.getKittens(), lion.getKittens());
        Mockito.verify(feline, Mockito.times(2)).getKittens();
    }
    @Test
    void getKittensNoParamInvokeSetKittensWith1Test() {
        feline.getKittens();
        Mockito.verify(feline, Mockito.times(1)).getKittens(1);
    }
    @Test
    void eatMeatFelineTest() throws Exception {
        Mockito.when(feline.getFood()).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        Lion lion = new Lion("Самец", feline);
        assertEquals(lion.eatMeat(),feline.getFood());
        Mockito.verify(feline, Mockito.times(3)).getFood();
        Mockito.verify(feline, Mockito.times(1)).eatMeat();
    }
}
