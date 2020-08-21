package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;


public class HBaseAPI_5_Data_Get {

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
            Get get = new Get(Bytes.toBytes(rowkey));
            // get 默认使用rowkey进行查询，也可以增加查询条件（范围）
//            get.addFamily();
//            get.addColumn()

            // 查询返回结果
            Result result = table.get(get);

            // 获取查询的单元格
            Cell[] cells = result.rawCells();
            System.out.println("查询的数据");
            for (Cell cell : cells) {
                // 方法查询结果时，一般使用HBASE提供的工具类
                byte[] cfbs = CellUtil.cloneFamily(cell);
                byte[] cnbs = CellUtil.cloneQualifier(cell);
                byte[] cvbs = CellUtil.cloneValue(cell);


                // 使用hbase提供的工具类将字节数在转换为字符串
                System.out.println("列族：" + Bytes.toString(cfbs));
                System.out.println("列名：" + Bytes.toString(cnbs));
                System.out.println("列值：" + Bytes.toString(cvbs));
                System.out.println("==============================================");
            }



            table.close();


        }


        admin.close();
        connection.close();
    }
}
