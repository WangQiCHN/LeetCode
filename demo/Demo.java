package demo;

class Demo {
    public static void main(String[] args) {
        Demo demo = new Demo();
        String answer = demo.getAnswer();
        System.out.println(answer);
    }

    private String getAnswer() {
        return "42";
    }
}
