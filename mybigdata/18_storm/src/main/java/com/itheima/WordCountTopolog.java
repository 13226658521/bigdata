package com.itheima;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

public class WordCountTopolog {
    public static void main(String[] args) {

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout",new MySpout(),2);
        builder.setBolt("myBolt1",new MySplitBolt(),2).shuffleGrouping("spout");
        builder.setBolt("myBolt2",new MyCountBolt(),6).shuffleGrouping("myBolt1");

        Config config = new Config();
        config.setNumWorkers(3);

        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("mywordcount",config,builder.createTopology());
    }
}
