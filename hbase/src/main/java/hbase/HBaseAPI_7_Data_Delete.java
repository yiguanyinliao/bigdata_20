package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;


public class HBaseAPI_7_Data_Delete {

    public static void main(String[] args) throws Exception {

        final Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "hadoop102,hadoop103,hadoop104");
        final Connection connection = ConnectionFactory.createConnection(conf);

        Admin admin = connection.getAdmin();

        TableName tn = TableName.valueOf("hgc", "student");
        if (admin.tableExists(tn)) {

            // TODO 查询数据
            Table table = connection.getTable(tn);

            String rowkey = "1001";
            Delete delete = new Delete(Bytes.toBytes(rowkey));
            // 删除数据时可以根据条件进行删除：删除一个列族、列名、指定时间戳


            table.delete(delete);


            table.close();


        }


        admin.close();
        connection.close();
    }
}
