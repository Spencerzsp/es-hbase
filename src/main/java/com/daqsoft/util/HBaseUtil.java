package com.daqsoft.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.List;

public class HBaseUtil {

    public  final String TABLE_NAME = "doc";
    /**
     * 列簇1 文章信息
     */
    public  final String COLUMNFAMILY_1 = "cf";
    /**
     * 列簇1中的列
     */
    public  final String COLUMNFAMILY_1_TITLE = "title";
    public  final String COLUMNFAMILY_1_AUTHOR = "author";
    public  final String COLUMNFAMILY_1_CONTENT = "content";
    public  final String COLUMNFAMILY_1_DESCRIBE = "describe";

    private static Admin admin = null;
    private static Configuration conf = null;
    private static Connection conn = null;

    static {
        try {
            conf = HBaseConfiguration.create();
//            conf.set("hbase.zookeeper.quorum", "daqsoft-bdsp-05:2181, daqsoft-bdsp-06:2181, daqsoft-bdsp-07:2181");
//            conf.set("hbase.zookeeper.property.clientPort.port", "2181");
            conn = ConnectionFactory.createConnection(conf);
            admin = conn.getAdmin();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建表
     * @param tableName
     * @param columnName
     */

    public void createTable(String tableName, String columnName) {

        try {
            if (admin.tableExists(TableName.valueOf(tableName))){
                System.out.println(tableName + "：表已经存在");
            } else {
                HTableDescriptor table = new HTableDescriptor(TableName.valueOf(tableName));
                table.addFamily(new HColumnDescriptor(columnName));
                admin.createTable(table);
                System.out.println(tableName + "：表创建成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除表
     * @param tableName
     */
    public static void deleteTable(String tableName){
        try {
            admin.disableTable(TableName.valueOf(tableName));
            admin.deleteTable(TableName.valueOf(tableName));
            System.out.println(tableName + "表删除成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param tableName
     * @param row
     * @param columnFamily
     * @param column
     * @param data
     */
    public void put(String tableName, String row, String columnFamily, String column, String data){
        try {
            Table table = conn.getTable(TableName.valueOf(tableName));
            Put put = new Put(Bytes.toBytes(row));
            put.addColumn(columnFamily.getBytes(), column.getBytes(), data.getBytes());
            table.put(put);
            System.out.println("put'" + row + "'," + columnFamily + ":" + column
                    + "','" + data + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  查询表
     * @param tableName
     * @param rowName
     */
//    public void get(String tableName, String rowName){
//        try {
//            Table table = conn.getTable(TableName.valueOf(tableName));
//            Get get = new Get(rowName.getBytes());
//            Result result = table.get(get);
//            byte[] row = result.getRow();
//            System.out.println("row key is:" + new String(row));
//            List<Cell> listCells = result.listCells();
//            for(Cell cell: listCells){
//                byte[] familyArray = cell.getFamilyArray();
//                byte[] qualifierArray = cell.getQualifierArray();
//                byte[] valueArray = cell.getValueArray();
//
//                System.out.println("row value is:"+ new String(familyArray) +
//                        new String(qualifierArray) + new String(valueArray));
//            }
//            System.out.println("---------------查行键数据结束----------");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    private static Table getTable() throws IOException {
//        //设置我们表名
//        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf());
//        HColumnDescriptor f1 = new HColumnDescriptor(familyName);
//        hTableDescriptor.addFamily(f1);
//        if(!admin.tableExists(TableName.valueOf(tableName))){
//            admin.createTable(hTableDescriptor);
//        }
//        return conn.getTable(TableName.valueOf(tableName));
//    }

}
