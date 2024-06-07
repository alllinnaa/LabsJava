package part3;

public class Main {
    public static void main(String[] args) {
        IsomorphicStrings solution = new IsomorphicStrings();

        String s1 = "egg";
        String t1 = "add";
        System.out.println(solution.isIsomorphic(s1, t1));

        String s2 = "foo";
        String t2 = "bar";
        System.out.println(solution.isIsomorphic(s2, t2));

        String s3 = "paper";
        String t3 = "title";
        System.out.println(solution.isIsomorphic(s3, t3));
    }
}
