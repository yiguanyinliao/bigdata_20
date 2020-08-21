package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;


public class HBaseAPI_3_Table {

    public static void main(String[] args) throws Exception {

        final Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "hadoop102,hadoop103,hadoop104");
        final Connection connection = ConnectionFactory.createConnection(conf);

        Admin admin = connection.getAdmin();


        // TODO 表
        //  判断表是否存在
        //  需要构建TableName对象.
        TableName tn = TableName.valueOf("hgc", "student");
        if (admin.tableExists(tn)) {
            System.out.println("======= student 表存在 =======");

//            // TODO 清空数据
//            // 为了保证数据没有冲突，会在清空数据前，禁用表，这个操作自动完成.
//            admin.truncateTable(tn,true);
//
//            // TODO 删除表
//            // 在表删除前必须先禁用表，必须手动完成，如果不禁用，会发生错误
//            admin.disableTable(tn);
//            admin.deleteTable(tn);
        }else {
            // 创建表 -(表名 + 列族)
            TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(tn);

            String cf = "info";
//            byte[] cfbs = cf.getBytes(); // 编码
            // Hbase 底层存储的数据都是按照字节码存储，所以有可以将字符串转换为byte数组的工具类
            // 一般情况下，不会采用。
            byte[] cfbs = Bytes.toBytes(cf);
            ColumnFamilyDescriptor cfd = ColumnFamilyDescriptorBuilder.newBuilder(cfbs).build();
            // 增加列族
            tableDescriptorBuilder.setColumnFamily(cfd);


            TableDescriptor td = tableDescriptorBuilder.build();
            admin.createTable(td);
            System.out.println("======= 表格创建成功 =======");
        }


        // 删除命名空间
//        admin.deleteNamespace("hgc");



        admin.close();
        connection.close();


    }
}
