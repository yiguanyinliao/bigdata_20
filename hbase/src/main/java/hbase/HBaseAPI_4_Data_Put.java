package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.List;


public class HBaseAPI_4_Data_Put {

    public static void main(String[] args) throws Exception {

        final Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "hadoop102,hadoop103,hadoop104");
        final Connection connection = ConnectionFactory.createConnection(conf);

        Admin admin = connection.getAdmin();

        TableName tn = TableName.valueOf("hgc", "student");
        if (admin.tableExists(tn)) {
            System.out.println("======= student 表存在 =======");

            // TODO 插入数据
            //  表中数据的操作，一般使用表对象完成
            Table table = connection.getTable(tn);

            // 插入数据
            //  构建put对象时需要rowkey.
            String rowkey = "1001";
            Put put = new Put(Bytes.toBytes(rowkey));

            // 增加指定列的数据
            //  其中包含：列族、列名、（时间戳）、列值.
            String cf = "info";
            String cn = "name";
            String cn1 = "age";
            String cv = "zhangsan";
            String cv1 = "30";

            put.addColumn(Bytes.toBytes(cf),Bytes.toBytes(cn),Bytes.toBytes(cv));
            put.addColumn(Bytes.toBytes(cf),Bytes.toBytes(cn1),Bytes.toBytes(cv1));
            table.put(put);
//            table.put(List<String>);  // 批量插入
            System.out.println("student表增加数据成功");


            table.close();


        }


        admin.close();
        connection.close();
    }
}
