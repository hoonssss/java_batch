package com.example.springbatch.test;

public class thisTest {

    private String nameTest;
    private String nameTest1;

    // 두 개의 String 값을 반환하는 메서드
    public String[] setName(String testName) {
        this.nameTest = testName;
        return new String[] { nameTest, null }; // nameTest1은 null로 설정
    }

    public String[] setName(String testName, String testName1) {
        this.nameTest = testName;
        this.nameTest1 = testName1;
        return new String[] { nameTest, nameTest1 }; // 두 값 모두 반환
    }

    public static void main(String[] args) {
        thisTest thistest = new thisTest();
        String[] result1 = thistest.setName("test");
        String[] result2 = thistest.setName("jh", "jh1");

        System.out.println("Result1: " + result1[0] + ", " + result1[1]); // "test, null"
        System.out.println("Result2: " + result2[0] + ", " + result2[1]); // "jh, jh1"
    }
}
