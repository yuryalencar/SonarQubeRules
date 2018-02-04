class MyClass {
    private int foo;
    private MyClass foo1;
    private int[] foo2;

    public int foo3; // Noncompliant
    public MyClass foo4; // Noncompliant
    public int[] foo5; // Noncompliant
}
