package xin.liujiajun.redis.filter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author liujiajun
 * @date 2020-01-13 19:48
 **/
public class AccessLog {

    /**
     * log 名称
     */
    private String name;

    /**
     * http method
     */
    private String method;

    /**
     * 调用的url
     */
    private String url;

    /**
     * 调用者ip
     */
    private String ip;

    /**
     * 调用参数
     */
    private String params;

    /**
     * body部分
     */
    private String body;

    /**
     * 用户代理
     */
    private String userAgent;

    /**
     * 访问时间点
     */
    private String accessDate;


    private String token;


    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public AccessLog() {

    }


    public AccessLog(String name, ServletRequest request) {
        this.name = name;
        this.url = getUrl(request);
        this.params = getParams(request);
        this.method = getMethod(request);
        this.userAgent = getRequest(request).getHeader("User-Agent");
        this.accessDate = LocalDateTime.now().format(formatter);
        //Nginx的话就不对了
        this.ip = getRequest(request).getRemoteAddr();
        this.body = getBody(request);
    }

    public String toLogStr() {

        StringBuilder sb = new StringBuilder();
        sb.append("\n=================[" + name + "]=================\n");
        sb.append("url : " + url + "\n");
        sb.append("method : " + method + "\n");
        sb.append("ip : " + ip + "\n");
        sb.append("params : " + params + "\n");
        sb.append("body : " + body + "\n");
        sb.append("token : " + token + "\n");
        sb.append("userAgent : " + userAgent + "\n");
        sb.append("accessDate : " + accessDate + "\n");

        sb.append("=================[/" + name + "]=================\n");
        return sb.toString();
    }

    private String getBody(ServletRequest re) {
        HttpServletRequest request = getRequest(re);
        try {
            BufferedReader buff = request.getReader();
            StringWriter out = new StringWriter();
            copy(buff, out);
            return out.toString();
        } catch (IOException e) {
            return null;
        }
    }

    public static int copy(Reader input, Writer output) throws IOException {
        char[] buffer = new char[16384];

        int count;
        int read;
        for (count = 0; (read = input.read(buffer, 0, 16384)) >= 0; count += read) {
            output.write(buffer, 0, read);
        }

        output.flush();
        return count;
    }

    private String getUrl(ServletRequest request) {
        return ((HttpServletRequest) request).getRequestURI();
    }

    private String getMethod(ServletRequest request) {
        return ((HttpServletRequest) request).getMethod();
    }

    private HttpServletRequest getRequest(ServletRequest request) {
        return (HttpServletRequest) request;
    }

    private String getParams(ServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        if (map.isEmpty()) {
            return "";
        }

        List<String> keys = new ArrayList(map.keySet());

        Collections.sort(keys);

        List<String> params = new ArrayList<>();
        for (String key : keys) {
            // 仅计算参数不为空的
            String value = join(map.get(key), ",");
            if (value != null) {
                params.add(key + "=" + value);
            }
        }

        return join(params.toArray(), "&");
    }

    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return "";
        } else if (array.length == 1) {
            return String.valueOf(array[0]);
        } else {
            StringBuilder sb = new StringBuilder(array.length * 16);

            for (int i = 0; i < array.length; ++i) {
                if (i > 0) {
                    sb.append(separator);
                }

                sb.append(array[i]);
            }

            return sb.toString();
        }
    }

    public String getName() {
        return name;
    }

    public AccessLog setName(String name) {
        this.name = name;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public AccessLog setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public AccessLog setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public AccessLog setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getParams() {
        return params;
    }

    public AccessLog setParams(String params) {
        this.params = params;
        return this;
    }

    public String getBody() {
        return body;
    }

    public AccessLog setBody(String body) {
        this.body = body;
        return this;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public AccessLog setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public String getAccessDate() {
        return accessDate;
    }

    public AccessLog setAccessDate(String accessDate) {
        this.accessDate = accessDate;
        return this;
    }

    public String getToken() {
        return token;
    }

    public AccessLog setToken(String token) {
        this.token = token;
        return this;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public AccessLog setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
        return this;
    }
}
