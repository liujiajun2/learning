package xin.liujiajun.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @author liujiajun
 * @create 2019-07-28 14:31
 **/
public class Index {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        IndicesClient indices = client.indices();
        //创建索引
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("testes2");

        RequestOptions  options = RequestOptions.DEFAULT;
        indices.create(createIndexRequest,options);

        client.close();

    }
}
