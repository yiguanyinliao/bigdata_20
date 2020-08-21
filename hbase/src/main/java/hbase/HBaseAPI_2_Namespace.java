package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;


public class HBaseAPI_2_Namespace {

    public static void main(String[] args) throws Exception {


        // TODO 在创建过程中会获取hbase的配置文件，详情查看create()源码
        final Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "hadoop102,hadoop103,hadoop104");
        final Connection connection = ConnectionFactory.createConnection(conf);

        // 使用DDL API操作HBASE的时候，需要进行授权
        Admin admin = connection.getAdmin();
        // TODO 命名空间
        // 创建
        NamespaceDescriptor nd = NamespaceDescriptor.create("hgc").build();  // 无法直接构建对象，一般会有static对象可以使用
        admin.createNamespace(nd);

        // 删除命名空间
//        admin.deleteNamespace("hgc");


        connection.close();


    }
}
