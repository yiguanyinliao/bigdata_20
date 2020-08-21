package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;


public class HBaseAPI_1_Connection {

    public static void main(String[] args) throws Exception {

        // TODO 使用HBase Api访问数据
        //  HBASE是一个数据库，访问方式和Mysql的访问方法类似


        // TODO 1、获取HBase数据库连接
        //          HBASE在zk中保存了相应的节点信息，可以通过zk查找到HBASE集群.
        final Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "hadoop102,hadoop103,hadoop104");
        final Connection connection = ConnectionFactory.createConnection(conf);

        // TODO 2、操作数据库


        // TODO 3、关闭连接
        connection.close();



    }
}
