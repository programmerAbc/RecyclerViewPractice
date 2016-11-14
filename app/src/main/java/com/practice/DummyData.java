package com.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaofeng on 2016-11-14.
 */

public class DummyData {
    public static List<String> generateDummyData(int size){
        ArrayList<String> data=new ArrayList<>();
        for(int i=0;i<size;++i){
            data.add("item:"+System.currentTimeMillis());
        }
        return data;
    }
}
