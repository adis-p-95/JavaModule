import org.testng.Assert;
import org.testng.annotations.Test;

public class SolutionTests {

    private static Solution solution = new Solution();

    @Test(testName = "Text contains numbers.", expectedExceptions = {SolutionException.class}, expectedExceptionsMessageRegExp = "Input text contains numbers.")
    public void TestSolutionWhenInputContainsNumber() throws SolutionException
    {
        int repeat_counter = solution.solution("adis23", "ABC".toCharArray());
    }

    @Test(testName = "Given S = \"BAONXXOLL\", the function should return 1.")
    public void ValidTestRepeatCountIs1() throws SolutionException {
        int repeat_counter = solution.solution("BAONXXOLL", "BALLOON".toCharArray());
        Assert.assertEquals(repeat_counter, 1);
    }

    @Test(testName = "Given S = \"BAOOLLNNOLOLGBAX\", the function should return 2")
    public void ValidTestRepeatCountIs2() throws SolutionException {
        int repeat_counter = solution.solution("BAOOLLNNOLOLGBAX", "BALLOON".toCharArray());
        Assert.assertEquals(repeat_counter, 2);
    }

    @Test(testName = "Lenght of input charachters", expectedExceptions = {SolutionException.class}, expectedExceptionsMessageRegExp = "Input is larger than 5.")
    public void TestSolutionWhenInputIsLarger() throws SolutionException{
        int repeat_counter = solution.solution("BAOOLLNNOLOLGBAX", "BALLOON".toCharArray(), 5);
    }

    @Test(testName = "Input contains lower case letters", expectedExceptions = {SolutionException.class}, expectedExceptionsMessageRegExp = "Input text contains lower case letters.")
    public void TestSolutionWhenInputContainsLowerCaseLetters() throws SolutionException {
        int repeat_counter = solution.solution ("balosn", "BALLOON".toCharArray());
    }

    @Test(testName = "Given S = \"QAWABAWONL\", the function should return 0.")
    public void ValidTestReapeatCount0() throws SolutionException {
        int repeat_counter = solution.solution("QAWABAWON", "BALLOON".toCharArray());
        Assert.assertEquals(repeat_counter, 0);
    }
}
