import org.testng.Assert;
import org.testng.annotations.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SolutionMockitoTests {
    private static final Solution solution_mock = mock(Solution.class);

    @Test(testName = "Lenght of input charachters", expectedExceptions = {SolutionException.class}, expectedExceptionsMessageRegExp = "Input is larger than 200000.")
    public void TestSolutionWhenInputIsLarger() throws SolutionException{
        when(solution_mock.solution(anyString(), any(char[].class))).thenThrow(new SolutionException("Input is larger than 200000."));
        int repeat_counter = solution_mock.solution("BAOOLLNNOLOLGBAX", "BALLOON".toCharArray());
    }

    @Test(testName = "Any given text the function should return 50")
    public void TestSolutionAlwaysReturn50() throws SolutionException{
        when(solution_mock.solution(anyString(), any(char[].class))).thenReturn(50);
        int repeat_counter = solution_mock.solution("BAOOLLNNOLOLGBAX", "BALLOON".toCharArray());
        Assert.assertEquals(repeat_counter, 50);
    }

    @Test(testName = "Input contains lower letters", expectedExceptions = {SolutionException.class}, expectedExceptionsMessageRegExp = "Input contains lower letters.")
    public void TestSolutionWhenInputHaveLowerLetters() throws SolutionException{
        when(solution_mock.solution(anyString(), any(char[].class))).thenThrow(new SolutionException("Input contains lower letters."));
        int repeat_counter = solution_mock.solution("BALLOONballon", "BALLOON".toCharArray());
    }

    @Test(testName = "Text contains numbers", expectedExceptions = {SolutionException.class}, expectedExceptionsMessageRegExp = "Text contains numbers.")
    public void TestSolutionWhenTextContainsNumbers() throws SolutionException{
        when(solution_mock.solution(anyString(), any(char[].class))).thenThrow(new SolutionException("Text contains numbers."));
        int repeat_counter = solution_mock.solution("123ABC", "ABC".toCharArray());
    }
}
