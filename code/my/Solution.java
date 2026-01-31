package code.my;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String source = "abcd", target = "acbe";
        String[] original = { "a", "b", "c", "c", "e", "d" }, changed = { "b", "c", "b", "e", "b", "e" };
        int[] cost = { 2, 5, 5, 1, 2, 20 };
        // String source = "abcdefgh", target = "acdeeghh";
        // String[] original = { "bcd","fgh","thh" }, changed = { "cde","thh","ghh" };
        // int[] cost = { 1, 3, 5 };
        // String source = "fjybg", target = "apyyt";
        // String[] original = { "bg","xr","cc","ip","vq","po","ym","rh","vw","lf","lo","ee","qv","yr","f","w","i","u","g","a","e","f","s","r","p","j","o","g","i","u" }, changed = { "xr","cc","ip","vq","po","ym","rh","vw","lf","lo","ee","qv","yr","yt","w","i","u","g","a","e","f","s","r","p","a","o","g","i","u","p" };
        // int[] cost = { 97733,90086,87125,85361,75644,46301,21616,79538,52507,95884,79353,61127,58665,96031,95035,12116,41158,91096,47819,88522,25493,80186,66981,87597,56691,86820,89031,99954,41271,39699 };
        long answer = sol.minimumCost(source, target, original, changed, cost);
        System.out.println(answer);
    }

    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        return 0;
    }
}
