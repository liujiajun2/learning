package xin.liujiajun.springboot.test.download;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * @author liujiajun
 * @date 2019-11-22 13:32
 **/
@Service
public class TestRestTemplateDownload {

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    public void init(){
        String baseDir = "F:\\";
        String fileName = "tesfffft.cfg";
        String url = "http://10.200.112.73:9989/api/v1/external/diagnosis/downloadConfigFile?sessionId=z9qZibrN0QuxAZSZ33F+WR3g7d2RHdrE3f1uJ4AFVWnA1CWcBqDBVAQGRMT2IwtoAdzo6M1Ue+WQj3v37x+pKA==";
        String tmpPath = baseDir + fileName;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Resource> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<byte[]> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity, byte[].class);
        if (Objects.isNull(exchange.getBody())) {
            System.out.println("body is null");
        }
        try {
            Files.write(Paths.get(tmpPath), exchange.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
