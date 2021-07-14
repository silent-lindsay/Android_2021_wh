package com.example.chapter3.homework.recycler;

import java.util.ArrayList;
import java.util.List;

public class TestDataSet {

    public static List<TestData> getData() {
        List<TestData> result = new ArrayList();
        result.add(new TestData("friend_no1", "3190104602"));
        result.add(new TestData("friend_no2", "3190105609"));
        result.add(new TestData("friend_no3", "3190100011"));
        result.add(new TestData("friend_no4", "3190100412"));
        result.add(new TestData("friend_no5", "3190100543"));
        result.add(new TestData("friend_no6", "3190105134"));
        result.add(new TestData("friend_no7", "3190106735"));
        result.add(new TestData("friend_no8", "3190106416"));
        result.add(new TestData("friend_no9", "3190105247"));
        result.add(new TestData("friend_no10", "3190105234"));
        result.add(new TestData("friend_no11", "3190107453"));
        return result;
    }

}
