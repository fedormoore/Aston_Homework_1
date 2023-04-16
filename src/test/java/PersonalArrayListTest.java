import aston.PersonalArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonalArrayListTest {
    private static final int five = 5;
    private static final int seven = 7;
    private static final int two = 2;

    private PersonalArrayList<Integer> list;

    @BeforeEach
    void prepare() {
        list = new PersonalArrayList<>(Integer.class);
    }

    @Test
    void checkSizeForEmptyList() {

        Assertions.assertEquals(list.size(), 0);
    }

    @Test
    void checkAddShouldReturn3() {
        list.add(five);
        list.add(seven);
        list.add(two);

        Assertions.assertEquals(list.size(), 3);
    }

    @Test
    void checkAddByIndexShouldReturn7() {
        list.add(five);
        list.add(two);
        list.add(1, seven);

        Assertions.assertEquals(list.get(1), seven);
    }

    @Test
    void checkDeleteShould7() {
        list.add(five);
        list.add(two);
        list.add(seven);
        list.delete(1);

        Assertions.assertEquals(list.get(1), seven);
    }

    @Test
    void checkContainsShouldReturnTrue() {
        list.add(five);
        list.add(seven);
        list.add(two);
        boolean contains = list.contains(seven);

        Assertions.assertTrue(contains);
    }


}
