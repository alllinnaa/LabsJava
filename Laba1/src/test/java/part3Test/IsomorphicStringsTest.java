package part3Test;
import static org.junit.Assert.*;
import org.junit.Test;
import part3.IsomorphicStrings;

public class IsomorphicStringsTest {
    @Test
    public void testIsIsomorphic() {
        IsomorphicStrings solution = new IsomorphicStrings();

        // Test case 1: "egg" and "add" are isomorphic
        assertTrue(solution.isIsomorphic("egg", "add"));

        // Test case 2: "foo" and "bar" are not isomorphic
        assertFalse(solution.isIsomorphic("foo", "bar"));

        // Test case 3: "paper" and "title" are isomorphic
        assertTrue(solution.isIsomorphic("paper", "title"));

        // Test case 4: Empty strings are isomorphic
        assertTrue(solution.isIsomorphic("", ""));

        // Test case 5: Strings with different lengths are not isomorphic
        assertFalse(solution.isIsomorphic("abc", "abcd"));
    }
}
