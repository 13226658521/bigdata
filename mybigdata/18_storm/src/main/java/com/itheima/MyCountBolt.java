package com.itheima;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

public class MyCountBolt extends BaseRichBolt {
    private OutputCollector outputCollector;
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
    }

    Map<String,Integer> map = new HashMap<String, Integer>();

    //接收数据
    public void execute(Tuple tuple) {
        String word = tuple.getString(0);
        Integer num = tuple.getInteger(1);
        if (map.containsKey(word)){
            Integer count = map.get(word);
            map.put(word,count + 1);
        }else {
            map.put(word,1);
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-------------------------------------");
        System.out.println(map.toString());
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        System.out.println(map);
    }
}
