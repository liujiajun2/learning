package xin.liujiajun.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

/**
 * @author liujiajun
 * @create 2019-07-28 14:49
 **/
public class Document {

    public static void main(String[] args) throws IOException {

//        add();

        get();

//        delete();

//        update();
    }


    public static void add() {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        //插入一条数据

        IndexRequest request = new IndexRequest("testes3").id("1");
        String jsonString = "{" +
                "\"user\":\"kchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        //source的重载方法有好几种
        request.source(jsonString, XContentType.JSON);
        //同步操作
//        client.index(request,RequestOptions.DEFAULT);
        //异步执行
        ActionListener<IndexResponse> listener = new ActionListener<IndexResponse>() {

            @Override
            public void onResponse(IndexResponse indexResponse) {
                if (indexResponse.status().equals(RestStatus.OK)) {
                    System.out.println("index request is ok");
                }
                System.out.println(indexResponse);
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("error ");
                e.printStackTrace();
            }
        };
        client.indexAsync(request, RequestOptions.DEFAULT, listener);
        //如果是异步的话,需要把close注释掉
//        client.close();
    }

    public static void get() {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
        GetRequest getRequest = new GetRequest("testes3", "1");

        //还可以指定一些请求的参数

        ActionListener<GetResponse> listener = new ActionListener<GetResponse>() {
            @Override
            public void onResponse(GetResponse getResponse) {
                String index = getResponse.getIndex();
                String id = getResponse.getId();
                if (getResponse.isExists()) {
                    long version = getResponse.getVersion();
                    String value = getResponse.getSourceAsString();
                    System.out.println(value);
                }else{
                    System.out.println("value is not exist");
                }
                System.out.println(getResponse);

            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("error");
                e.printStackTrace();
            }
        };
        client.getAsync(getRequest, RequestOptions.DEFAULT, listener);
    }

    public static void delete(){

        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
        DeleteRequest deleteRequest = new DeleteRequest("testes3","1");
        //还可以设置一些请求的参数

        client.deleteAsync(deleteRequest, RequestOptions.DEFAULT, new ActionListener<DeleteResponse>() {
            @Override
            public void onResponse(DeleteResponse deleteResponse) {
                ReplicationResponse.ShardInfo shardInfo = deleteResponse.getShardInfo();
                if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
                    //成功的分片数小于 总的分片数
                    System.out.println("成功的分片数小于 总的分片数");
                }
                if (shardInfo.getFailed() > 0) {
                    System.out.println("有失败的记录");
                }
                System.out.println(deleteResponse);
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("error");
            }
        });
    }

    public static void update(){
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
        UpdateRequest updateRequest = new UpdateRequest("testes3","1");

        HashMap<String, Object> map = new HashMap<>();
        map.put("updated",new Date());
        map.put("reason","daily update");

        updateRequest.doc(map);
        //还可以设置一些其他参数

        client.updateAsync(updateRequest, RequestOptions.DEFAULT, new ActionListener<UpdateResponse>() {
            @Override
            public void onResponse(UpdateResponse updateResponse) {
                DocWriteResponse.Result result = updateResponse.getResult();
                if (result == DocWriteResponse.Result.CREATED) {
                    //the document was created for the first time (upsert)
                }else if (result == DocWriteResponse.Result.UPDATED){
                    //he document was updated
                }else if (result == DocWriteResponse.Result.DELETED){
                    //the document was deleted
                }else if (result == DocWriteResponse.Result.NOOP){
                    //the document was not impacted by the update, ie no operation (noop) was executed on the document
                }
                System.out.println(updateResponse);
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("error");
            }
        });



    }
}
