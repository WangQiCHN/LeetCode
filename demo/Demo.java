import java.util.ArrayList;
import java.util.List;

class Demo {
    void main(String[] args) {
        String num = "123";
        int target = 6;
        List<String> results = addOperators(num, target);
        System.out.println(results); // Output: ["1+2+3", "1*2*3"]
    }

    public List<String> addOperators(String num, int target) {
        List<String> results = new ArrayList<>();
        backtrack(num, target, 0, 0, 0, "", results);
        return results;
    }

    private void backtrack(String num, int target, int index, long prevOperand, long currentValue, String expression,
            List<String> results) {
        // Base case: if we've processed the entire string
        if (index == num.length()) {
            if (currentValue == target) {
                results.add(expression);
            }
            return;
        }

        // Build the current operand by considering digits from index to i кив
        long currentOperand = 0;
        StringBuilder currentOperandStr = new StringBuilder();

        // Iterate through possible operand lengths
        for (int i = index; i < num.length(); i++) {
            // Avoid leading zeros
            if (i > index && num.charAt(index) == '0') {
                break;
            }
            currentOperandStr.append(num.charAt(i));
            currentOperand = Long.parseLong(currentOperandStr.toString());

            // If this is the first operand, no operator is needed
            if (index == 0) {
                backtrack(num, target, i + 1, currentOperand, currentOperand, currentOperandStr.toString(), results);
            } else {
                // Try adding '+'
                backtrack(num, target, i + 1, currentOperand, currentValue + currentOperand,
                        expression + "+" + currentOperandStr, results);
                // Try adding '-'
                backtrack(num, target, i + 1, -currentOperand, currentValue - currentOperand,
                        expression + "-" + currentOperandStr, results);
                // Try adding '*' (adjust for precedence)
                backtrack(num, target, i + 1, prevOperand * currentOperand,
                        currentValue - prevOperand + (prevOperand * currentOperand),
                        expression + "*" + currentOperandStr, results);
            }
            // Reset the operand string for the next iteration
            // currentOperandStr.setLength(0);
        }
    }
}