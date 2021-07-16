package com.example.test713.recycler;

import java.util.ArrayList;
import java.util.List;

public class TestDataSet {

    public static List<TestData> getData() {
        List<TestData> result = new ArrayList();
        result.add(new TestData("今天上课的xjj真好看", "680.1w"));
        result.add(new TestData("教育部辟谣寒暑假", "524.6w"));
        result.add(new TestData("工友做客拐走孩子", "433.6w"));
        result.add(new TestData("儿子被拐27岁郭刚堂白头", "357.8w"));
        result.add(new TestData("原粮食局副局长徐鸣被查", "333.6w"));
        result.add(new TestData("中国空间站凌日瞬间", "285.6w"));
        result.add(new TestData("奥运会技术人员吸毒被捕", "183.2w"));
        result.add(new TestData("刘德华祝福郭刚堂找到儿子", "139.4w"));
        result.add(new TestData("郭刚堂说会", "75.6w"));
        result.add(new TestData("女儿突然坠楼被妈妈拽住腿", "55w"));
        result.add(new TestData("赵立坚回击美方设疆谎言", "43w"));
        return result;
    }

}
