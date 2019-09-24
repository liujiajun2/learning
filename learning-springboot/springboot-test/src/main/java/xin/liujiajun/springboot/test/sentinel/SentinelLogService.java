package xin.liujiajun.springboot.test.sentinel;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liujiajun
 * @date 2019-09-24 14:59
 **/
@Service
public class SentinelLogService {

    public static final String BLOCK = "block";
    public static final String METRICS = "metrics";


    public List<Object> exchange(InputStream inputStream, String fileName) throws IOException {
        String fileType = getType(fileName);
        List<Object> objects = new ArrayList<>();
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        while (true) {
            String s = bufferedReader.readLine();
            if (s == null || "".equalsIgnoreCase(s)) {
                break;
            }
            objects.add(handle(s, fileType));
        }
        return objects;
    }

    private String getType(String fileName) {
        if (fileName.contains(BLOCK)) {
            return BLOCK;
        } else if (fileName.contains(METRICS)) {
            return METRICS;
        }

        return "";
    }

    private Object handle(String str, String fileType) {
        if (BLOCK.equalsIgnoreCase(fileType)) {
            String[] split = str.split("\\|");
            String[] sourceSplit = split[2].split("\\,");
            String[] limitSplit = split[3].split("\\,");

            //避免newList[3]超出数组
            String[] newList = new String[4];
            System.arraycopy(sourceSplit,0,newList,0,3);

            if (sourceSplit .length < 4) {
                newList[3] = "";
            }else{
                newList[3] = sourceSplit[3];
            }

            return new BlockInfo()
                    .setTime(split[0])
                    .setSequence(Integer.parseInt(split[1]))
                    .setResource(newList[0])
                    .setLimitKind(newList[1])
                    .setDefaultLimit(newList[2])
                    .setActualLimit(newList[3])
                    .setLimit(Integer.parseInt(limitSplit[0]));

        } else if (METRICS.equalsIgnoreCase(fileType)) {
            String[] split = str.split("\\|");
            return new MetricsInfo()
                    .setTimestamp(Long.parseLong(split[0]))
                    .setTime(split[1])
                    .setResource(split[2])
                    .setPassCount(Integer.parseInt(split[3]))
                    .setBlockCount(Integer.parseInt(split[4]))
                    .setCompleteCount(Integer.parseInt(split[5]))
                    .setExceptionCount(Integer.parseInt(split[6]))
                    .setRt(Integer.parseInt(split[7]));
        }

        return null;
    }


}
