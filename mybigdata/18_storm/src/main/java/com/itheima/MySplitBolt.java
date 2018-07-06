package com.itheima;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

public class MySplitBolt extends BaseRichBolt {

    private OutputCollector outputCollector;
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
    }

    //相当于map，一次读取一行数据
    public void execute(Tuple tuple) {
        String line = tuple.getString(0);
        String[] words = line.split(" ");
        for (String word : words){
            outputCollector.emit(new Values(word,1));
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        //发出数据，数组
        outputFieldsDeclarer.declare(new Fields("单词","个数"));
    }
}
