import com.example.Cat;
import com.example.Feline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CatClassTest {

    @Mock
    Feline feline;

    //Тестируем метод getFood класса Cat
    @Test
    public void testGetFoodCat() throws Exception {
        List<String> expectedValues = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(feline.eatMeat()).thenReturn(expectedValues);

        Cat myCat = new Cat(feline);
        List<String> foodList = myCat.getFood();

        assertThat(foodList).containsAll(expectedValues);
        Mockito.verify(feline).eatMeat();
    }

    //Тестируем метод getSound()
    @Test
    public void testSoundCat() {
        Cat myCat = new Cat(feline);
        assertThat(myCat.getSound()).isEqualTo("Мяу");
    }
}