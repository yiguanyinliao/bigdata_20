package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;


public class HBaseAPI_8_Table_Split {

    public static void main(String[] args) throws Exception {

        final Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "hadoop102,hadoop103,hadoop104");
        final Connection connection = ConnectionFactory.createConnection(conf);

        Admin admin = connection.getAdmin();

        TableName tn = TableName.valueOf("hgc", "staff5");
        if (admin.tableExists(tn)) {
            System.out.println("======= student 表存在 =======");

        }else {
            // 创建表 -(表名 + 列族)
            TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(tn);

            String cf = "info";
            byte[] cfbs = Bytes.toBytes(cf);
            ColumnFamilyDescriptor cfd = ColumnFamilyDescriptorBuilder.newBuilder(cfbs).build();
            // 增加列族
            tableDescriptorBuilder.setColumnFamily(cfd);
            TableDescriptor td = tableDescriptorBuilder.build();

            // 对表进行预分区，需要提前准备多个rowkey
            // hbase保存rowkey其实是采用byte[]保存的
            byte[][] bss = new byte[3][];

            bss[0] = Bytes.toBytes("a");
            bss[1] = Bytes.toBytes("b");
            bss[2] = Bytes.toBytes("c");

            admin.createTable(td,bss);
            System.out.println("======= 表格创建成功 =======");
        }

        admin.close();
        connection.close();


    }
}
