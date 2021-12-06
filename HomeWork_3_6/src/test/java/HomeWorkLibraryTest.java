import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class HomeWorkLibraryTest {


    @ParameterizedTest
    @MethodSource("returnArrayAfterFourProvider")
    void shouldReturnArrayAfterLastFour_whereValidIntegerArray(int[] array){
        Assertions.assertNotNull(HomeWorkLibrary.returnArrayAfterLastFour(array));
    }
    private static Stream<Arguments> returnArrayAfterFourProvider(){
        return Stream.of(
                Arguments.of(new int[]{1,5,4,7,8,9,3,2,1}),
                Arguments.of(new int[]{1,5,3,7,8,9,3,2,1}),
                Arguments.of(new int[]{1,5,4,7,8,4,3,2,1})
        );
    }

    @ParameterizedTest
    @MethodSource("checkArrayContainsOneAndFourProvider")
    void shouldCheckArrayContainsOneAndFour_whereValidIntegerArray(int[] array){
        Assertions.assertTrue(HomeWorkLibrary.checkContainsOneAndFour(array));
    }

    private static Stream<Arguments> checkArrayContainsOneAndFourProvider(){
        return Stream.of(
                Arguments.of(new int[]{1,5,4,7,8,9,3,2,1}),
                Arguments.of(new int[]{1,5,3,7,8,9,3,2,1}),
                Arguments.of(new int[]{1,5,4,7,8,4,3,2,1})
        );
    }
}
