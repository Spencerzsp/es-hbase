package com.daqsoft;

import com.daqsoft.bean.Doc;
import com.daqsoft.util.DocUtil;
import com.google.gson.Gson;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppMain2 {

    private static final String tableName = "doc";
    private static final String famalyName = "cf";
    private  static final String title = "title";
    private  static final String author = "author";
    private  static final String content = "content";
    private  static final String describe = "describe";

    private static Admin admin = null;
    private static Configuration configuration = null;
    private static Connection connection = null;

    /**
     * 初始化hbase连接的一些配置
     */
    static {
        try {
            configuration = HBaseConfiguration.create();
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据关键词查询es，返回文档对应的id
     * @param client
     * @param keyWord
     * @return
     */
    public static List<String> getByKeyWord(TransportClient client, String keyWord){
        ArrayList<String> strings = new ArrayList<String>();
        SearchResponse searchResponse = client.prepareSearch("zsp")
                .setTypes("doc")
//                .setQuery(QueryBuilders.matchAllQuery()).get();
                .setQuery(QueryBuilders.matchQuery("title.keyword", keyWord)).get();
        SearchHits hits = searchResponse.getHits();
        for(SearchHit hit: hits){
            String s = hit.getSourceAsString();
            System.out.println(s);

            String id = hit.getId();
            // 获取数据系统的id
//            System.out.println(id);
            System.out.println("---------------------------");
            strings.add(id);
        }
        return strings;
    }

    /**
     * 将数据存入hbase
     * @param docInfo
     * @param table
     */
    private static void save2Hbase(List<Doc> docInfo, Table table){
        ArrayList<Put> puts = new ArrayList<Put>();
        for(Doc doc: docInfo){
            System.out.println(doc.getTitle());
            Put put = new Put(Bytes.toBytes(doc.getId()+ ""));
            if(doc.getTitle() != null && doc.getTitle() != ""){
                put.addColumn(Bytes.toBytes(famalyName), Bytes.toBytes(title), Bytes.toBytes(doc.getTitle()));
                put.addColumn(Bytes.toBytes(famalyName), Bytes.toBytes(describe), Bytes.toBytes(doc.getDescribe()));
                put.addColumn(Bytes.toBytes(famalyName), Bytes.toBytes(author), Bytes.toBytes(doc.getAuthor()));
                put.addColumn(Bytes.toBytes(famalyName), Bytes.toBytes(content), Bytes.toBytes(doc.getContent()));
                puts.add(put);
            }
        }
        try {
            table.put(puts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取表名
     * @return
     */
    private static Table getTable() throws IOException {
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(famalyName);
        hTableDescriptor.addFamily(hColumnDescriptor);
        if(!admin.tableExists(TableName.valueOf(tableName))){
            admin.createTable(hTableDescriptor);
        }
        return connection.getTable(TableName.valueOf(tableName));

    }

    /**
     * 查询hbase表数据
     * @param tableName
     * @throws IOException
     */
    public static void queryTable(String tableName) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        ResultScanner scanner = table.getScanner(new Scan());
        for(Result result: scanner){
            byte[] row = result.getRow();
            System.out.println("row key is:" + new String(row));
            List<Cell> cells = result.listCells();
            for(Cell cell: cells){
                byte[] familyArray = cell.getFamilyArray();
                byte[] qualifierArray = cell.getQualifierArray();
                byte[] valueArray = cell.getValueArray();
                System.out.println("row value is:" + new String(familyArray) + new String(qualifierArray) + new String(valueArray));
            }
        }
    }

    /**
     * 连接es集群
     * @return
     * @throws UnknownHostException
     */
    public static TransportClient getEsClient() throws UnknownHostException {
        Settings settings = Settings.builder().put("cluster.name", "my-application")
                .put("client.transport.sniff", true)
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("daqsoft-bdsp-01"),9300));
        return client;
    }

//    private static void save2es(List<Doc> docInfo, TransportClient client){
//        BulkRequestBuilder bulk = client.prepareBulk();
//        for(Doc doc: docInfo){
//            IndexRequestBuilder indexRequestBuilder = client.prepareIndex("tftj", "doc");
//            Gson gson = new Gson();
//            String jsonStr = gson.toJson(doc);
//            indexRequestBuilder.setSource(jsonStr, XContentType.JSON);
//            bulk.add(indexRequestBuilder);
//        }
//        // 触发数据真正保存早es中去
//        BulkResponse bulkItemResponses = bulk.get();
//        client.close();
//    }

    /**
     *向es中添加数据创建索引
     * @param index
     * @param type
     * @param doc
     */
    public static String save2es(String index, String type, Doc doc) throws Exception {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id", doc.getId());
        hashMap.put("title", doc.getTitle());
        hashMap.put("author", doc.getAuthor());
        hashMap.put("describe", doc.getDescribe());

        IndexResponse response = getEsClient().prepareIndex(index, type, String.valueOf(doc.getId())).setSource(hashMap).execute().actionGet();
        return response.getId();

    }

    /**
     * 程序入口
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        List<Doc> docInfo = DocUtil.getDocInfo();
        TransportClient client = getEsClient();
        Table table = getTable();
//        for (Doc doc: docInfo){
//            System.out.println(doc);
//            String id = save2es("zsp", "doc", doc);
//            System.out.println(id);
//        }

//        save2Hbase(docInfo, table);
//        queryTable("doc");

        System.out.println("---------------------------------------");
        // 通过关键字对es进行搜索,返回id(id对应hbase中的行键)
        List<String> keyWords = getByKeyWord(client, "对象");
        System.out.println(keyWords);
        for(String keyWord: keyWords){
            Get get = new Get(Bytes.toBytes(keyWord));
            Result result = table.get(get);
            Cell[] cells = result.rawCells();
            for(Cell cell: cells){
                byte[] value = cell.getValue();
                System.out.println(Bytes.toString(value));
            }
            System.out.println("-------------------------------");
        }


    }

}
