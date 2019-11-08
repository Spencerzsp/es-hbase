package com.daqsoft.util;


import com.daqsoft.bean.Doc;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Table;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.transport.TransportClient;

import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.elasticsearch.common.settings.Settings;

import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;


import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ElasticsearchUtil {

    private static TransportClient client = null;
    private static Settings build = null;

    /**
     * 配置连接es集群
     */
    static {
        build = Settings.builder()
                .put("cluster.name", "my-application")
                .put("client.transport.sniff", true)
                .build();

        try {
            client = new PreBuiltTransportClient(build)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("daqsoft-bdsp-01"), 9300));
            System.out.println("连接es集群成功");
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("连接es集群失败");
        }
    }

    /**
     * 查看集群健康状态
     */
    private static void clusterInfo() {

        AdminClient admin = client.admin();
        ClusterHealthResponse clusterHealthResponse = admin.cluster().prepareHealth().get();
        String clusterName = clusterHealthResponse.getClusterName();
        int numberOfDataNodes = clusterHealthResponse.getNumberOfDataNodes();
        int numberOfNodes = clusterHealthResponse.getNumberOfNodes();
        ClusterHealthStatus status = clusterHealthResponse.getStatus();

        System.out.println("集群名称：" + clusterName + "\n" + "集群数据节点数" + numberOfDataNodes +
         "\n" + "集群总节点数：" + numberOfNodes + "\n" + "集群状态" + status);
    }

    /**
     * hbase和es中插入数据并创建索引
     */
    public void createIndex(){
        List<Doc> arrayList = new ArrayList<Doc>();
        File file = new File("C:\\Users\\daqsoft\\Desktop\\doc.txt");
        try {
            List<String> list = FileUtils.readLines(file);
            for (String line: list) {
                Doc doc = new Doc();
                String[] split = line.split("\t");
                System.out.println(split[0]);
                int parseInt = Integer.parseInt(split[0].trim());
                doc.setId(parseInt);
                doc.setTitle(split[1]);
                doc.setAuthor(split[2]);
                doc.setDescribe(split[3]);
                doc.setContent(split[3]);

                arrayList.add(doc);
            }

            HBaseUtil hBaseUtil = new HBaseUtil();
            for (Doc doc: arrayList){
                hBaseUtil.put(hBaseUtil.TABLE_NAME, doc.getId().toString(), hBaseUtil.COLUMNFAMILY_1, hBaseUtil.COLUMNFAMILY_1_TITLE, doc.getTitle());
                hBaseUtil.put(hBaseUtil.TABLE_NAME, doc.getId().toString(), hBaseUtil.COLUMNFAMILY_1, hBaseUtil.COLUMNFAMILY_1_AUTHOR, doc.getAuthor());
                hBaseUtil.put(hBaseUtil.TABLE_NAME, doc.getId().toString(), hBaseUtil.COLUMNFAMILY_1, hBaseUtil.COLUMNFAMILY_1_DESCRIBE, doc.getDescribe());
                hBaseUtil.put(hBaseUtil.TABLE_NAME, doc.getId().toString(), hBaseUtil.COLUMNFAMILY_1, hBaseUtil.COLUMNFAMILY_1_CONTENT, doc.getContent());

                System.out.println("hbase插入数据成功");

//                String row = ElasticsearchUtil.addIndex("tfjt", "doc", doc);
//                System.out.println(row);
//                System.out.println("es插入数据成功");

//                hBaseUtil.get(hBaseUtil.TABLE_NAME, row);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *向es中添加数据创建索引
     * @param index
     * @param type
     * @param doc
     */
//    public static String addIndex(String index, String type, Doc doc){
//        HashMap<String, Object> hashMap = new HashMap<String, Object>();
//        hashMap.put("id", doc.getId());
//        hashMap.put("titile", doc.getTitle());
//        hashMap.put("author", doc.getAuthor());
//        hashMap.put("describe", doc.getDescribe());
//
//        IndexResponse response = client.prepareIndex(index, type).setSource(hashMap).execute().actionGet();
//        return response.getId();
//
//    }

    /**
     *获取我们需要查询的数据的文档id进行返回，用来去hbase中查询真正的数据
     * @param client
     * @param keyWord
     * @return
     */
    public static List<String> getByKeyWord(TransportClient client, String keyWord){
        ArrayList<String> strings = new ArrayList<String>();
//        SearchResponse searchResponse = client.prepareSearch("tftj").setTypes("doc")
//                .setQuery(QueryBuilders.termQuery("title", keyWord)).get();
//        SearchHits hits = searchResponse.getHits();
//        for(SearchHit hit: hits){
//            String id = hit.getId();
//            System.out.println(id);
//            strings.add(id);
//        }
        return strings;
    }

    public static void main(String[] args) {

        List<String> getAllKeyWord = getByKeyWord(client, "COS对象");
        HBaseUtil hBaseUtil = new HBaseUtil();

    }

}
