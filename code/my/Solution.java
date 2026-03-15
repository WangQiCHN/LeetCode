package code.my;

class Solution {
  public static void main(String[] args) {
    String n = "4681";
    System.out.println(new Solution().smallestGoodBase(n));
  }

  public String smallestGoodBase(String n) {
    long ln = Long.parseLong(n);
    int upper = (int) (Math.log(ln) / Math.log(2));
    for (int m = upper; m >= 2; m--) {
      int k = (int) Math.pow(ln, 1.0 / m);
      long mul = 1, sum = 1;
      for (int i = 1; i <= m; i++) {
        mul *= k;
        sum += mul;
      }

      if (sum == ln) {
        return k + "";
      }
    }

    return ln - 1 + "";
  }
}

// 4681 - 8^4 + 8^3 + 8^2 + 8 + 1
//