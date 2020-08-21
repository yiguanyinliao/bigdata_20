package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;


public class HBaseAPI_6_Data_Scan {

    public static void main(String[] args) throws Exception {

        final Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "hadoop102,hadoop103,hadoop104");
        final Connection connection = ConnectionFactory.createConnection(conf);

        Admin admin = connection.getAdmin();

        TableName tn = TableName.valueOf("hgc", "student");
        if (admin.tableExists(tn)) {

            // TODO 查询数据
            Table table = connection.getTable(tn);
            Scan scan = new Scan();

            // 如果scan不增加查询范围，会全表查询，数据量非常大，不推荐使用
            // 会增加范围。

            String startRoeKey = "1001";
            String stopRoeKey = "1002";
            Scan scan1 = scan.withStartRow(Bytes.toBytes(startRoeKey)).withStopRow(Bytes.toBytes(stopRoeKey));


            ResultScanner scanner = table.getScanner(scan1);

            // 所有实现可迭代接口的对象，都可以使用for循环
            for (Result result : scanner) {
                // 获取查询的单元格
                Cell[] cells = result.rawCells();
                for (Cell cell : cells) {
                    System.out.println("查询的数据");
//                    result.getRow();
                    byte[] cfbs = CellUtil.cloneFamily(cell);
                    byte[] cnbs = CellUtil.cloneQualifier(cell);
                    byte[] cvbs = CellUtil.cloneValue(cell);


                    System.out.println("列族：" + Bytes.toString(cfbs));
                    System.out.println("列名：" + Bytes.toString(cnbs));
                    System.out.println("列值：" + Bytes.toString(cvbs));
                    System.out.println("==============================================");
                }
            }

            scanner.close();
            table.close();


        }


        admin.close();
        connection.close();
    }
}
