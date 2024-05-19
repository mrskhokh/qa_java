import com.example.Feline;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(Parameterized.class)
public class FelineTest {
    private final int arg;
    private final int expected;
    Feline feline = new Feline();

    public FelineTest(int arg, int expected) {
        this.arg = arg;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getValueData() {
        return new Object[][]{
                {0, 0},
                {1, 1},
                {5, 5},
        };
    }

    @Test
    public void eatMeatTest() throws Exception {
        List<String> expectedValues = List.of("Животные", "Птицы", "Рыба");
        assertThat(feline.eatMeat()).containsAll(expectedValues);
    }

    @Test
    public void getFamilyTest() throws Exception {
        assertThat(feline.getFamily()).isEqualTo("Кошачьи");

    }

    @Test
    public void getKittensWithoutArgumentsTest() {
        assertThat(feline.getKittens()).isEqualTo(1);
    }


    @Test
    public void getKittensWithArgumentTest() {
        assertThat(feline.getKittens(arg)).isEqualTo(expected);
    }

}