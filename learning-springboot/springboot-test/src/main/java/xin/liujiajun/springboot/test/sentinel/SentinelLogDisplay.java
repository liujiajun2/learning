package xin.liujiajun.springboot.test.sentinel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author liujiajun
 * @date 2019-09-24 14:55
 **/
@RestController
@RequestMapping("sentinel/log")
public class SentinelLogDisplay {

    @Autowired
    private SentinelLogService sentinelLogService;

    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<Object> exchange(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        return sentinelLogService.exchange(multipartFile.getInputStream(),multipartFile.getOriginalFilename());
    }

}
