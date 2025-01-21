import com.example.Animal;
import com.example.Feline;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class FelineTests {
    @Spy
    private Feline feline;
    @Spy
    private Animal animal;

    @Test
    void getKittensWithParamTest(){
        feline.getKittens(2);
        Mockito.verify(feline, Mockito.times(1)).getKittens(2);
    }
    @Test
    void getKittensNoParamInvokeSetKittensWith1Test() {
        feline.getKittens();
        Mockito.verify(feline, Mockito.times(1)).getKittens(1);
    }
    @Test
    void getFamilyTest() {
        Mockito.when(feline.getFamily()).thenReturn("Кошачьи");
        assertEquals("Кошачьи", feline.getFamily());
        Mockito.verify(feline, Mockito.times(1)).getFamily();
    }
    @Test
    void eatMeatFelineTest() throws Exception {
        Mockito.when(feline.getFood()).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        assertEquals(feline.eatMeat(),feline.getFood());
        Mockito.verify(feline, Mockito.times(3)).getFood();
        Mockito.verify(feline, Mockito.times(1)).eatMeat();
    }
}
