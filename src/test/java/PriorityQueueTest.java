import java.util.PriorityQueue;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PriorityQueueTest {
    static Stream<Arguments> streamProvider() {
        return Stream.of(
                Arguments.of(new int[]{1,2,3}, new int[]{1,3,2}),
                Arguments.of(new int[]{-3,-1,-2,5}, new int[]{-3,-2,-1,5}),
                Arguments.of(new int[]{3,-2,-5,-1,2}, new int[]{-5,-2,-1,2,3}),
                Arguments.of(new int[]{-3,1,11,0,9,3}, new int[]{-3,0,1,3,9,11}),
                Arguments.of(new int[]{3,7,2,-1,-2}, new int[]{-2,-1,2,3,7})
        );
    }

    @ParameterizedTest(name="#{index} - Test with Argument={0}, {1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array){
        PriorityQueue<Integer> test = new PriorityQueue<>();
        int[] result = new int[random_array.length];

        for (int i : random_array) {
            test.add(i);
        }
        for(int i = 0; i < random_array.length; ++i){
            result[i] = test.poll();
        }

        assertArrayEquals(correct_array, result);
    }

    @Test
    public void whenExceptionThrown_thenInitialCapacityNotGreaterThanOne(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> {
            PriorityQueue<Integer> test = new PriorityQueue<>(0);
        });
    }
    @Test
    public void whenExceptionThrown_thenOfferEisNull(){
        Exception exception = assertThrows(NullPointerException.class, ()-> {
            PriorityQueue<Integer> test = new PriorityQueue<>();
            test.offer(null);
        });
    }
    @Test
    public void whenExceptionThrown_thenRemoveIfPisNull(){
        Exception exception = assertThrows(NullPointerException.class, ()-> {
            PriorityQueue<Integer> test = new PriorityQueue<>();
            test.removeIf(null);
        });
    }
}
