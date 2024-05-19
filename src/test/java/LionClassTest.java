import com.example.Feline;
import com.example.Lion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(MockitoJUnitRunner.class)
public class LionClassTest {
    @Mock
    Feline feline;

    @Test
    public void testGetFoodLion() throws Exception {
        List<String> expectedValues = List.of("Животные", "Птицы", "Рыба");

        Mockito.when(feline.eatMeat()).thenReturn(expectedValues);

        Lion myLion = new Lion("Самец", feline);
        List<String> foodList = myLion.getFood();

        assertThat(foodList).containsAll(expectedValues);
        Mockito.verify(feline).eatMeat();
    }

    /**
     * Проверяем кейс, что если feline выбрасывает исключение, то Lion пробрасывает его дальше
     */
    @Test
    public void testGetFoodException() throws Exception {
        Mockito.doThrow(new Exception("Неизвестный вид животного, используйте значение Травоядное или Хищник"))
                .when(feline).eatMeat();

        Lion myLion = new Lion("Самец", feline);

        assertThatThrownBy(myLion::getFood)
                .isInstanceOf(Exception.class)
                .hasMessageContaining("Неизвестный вид животного");

        Mockito.verify(feline).eatMeat();
    }

    @Test
    public void testGetKittens() throws Exception {
        int expected = 1;
        Mockito.when(feline.getKittens()).thenReturn(1);
        Lion myLion = new Lion("Самец", feline);
        assertThat(myLion.getKittens()).isEqualTo(expected);
        Mockito.verify(feline, Mockito.times(1)).getKittens();
    }

    @Test
    public void testHasMale() throws Exception {
    Lion myLion = new Lion("Самец",feline);
    assertThat(myLion.doesHaveMane()).isTrue();
    }
    @Test
    public void testHasNoMale() throws Exception {
        Lion myLion = new Lion("Самка",feline);
        assertThat(myLion.doesHaveMane()).isFalse();
    }
    @Test
    public void testDoesHasMaleException() {
        assertThatThrownBy(() -> new Lion("Транс",feline))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("Используйте допустимые значения пола");

    }
}
