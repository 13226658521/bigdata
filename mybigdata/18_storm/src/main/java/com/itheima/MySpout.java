package com.itheima;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MySpout extends BaseRichSpout {
    private SpoutOutputCollector spoutOutputCollector;

    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.spoutOutputCollector = spoutOutputCollector;
    }

    /**
     * 循环产生数据
     */
    public void nextTuple() {
        List<Object> list = new ArrayList<Object>();
        list.add("i am lilei love hanmeimei");
        spoutOutputCollector.emit(list);
//        spoutOutputCollector.emit(new Values("i am lilei love hanmeimei"));
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("随便取"));
    }
}
