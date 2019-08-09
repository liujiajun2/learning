package xin.liujiajun.netty.ssl.single;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * @author liujiajun
 * @date 2019-08-09 09:47
 **/
public class SslContextFactory {
    private static final String PROTOCOL = "TLS";
    private static final String PASSWORD = "123456";
//    private static final String PASSWORD = "yealink1105";

    public static SslContext getServer(){
        try {
            KeyStore jks = KeyStore.getInstance("JKS");
            FileInputStream in = new FileInputStream("data/ssl/single/serverKeys.jks");
            jks.load(in,PASSWORD.toCharArray());
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(jks,PASSWORD.toCharArray());

            return SslContextBuilder.forServer(kmf).trustManager().build();
        } catch (Exception e) {
            throw new Error("error server");
        }
    }

    public static SslContext getClient(){
        try{
            KeyStore jks = KeyStore.getInstance("JKS");
            FileInputStream in = new FileInputStream("data/ssl/single/clientKeys.jks");
            jks.load(in,PASSWORD.toCharArray());
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(jks);

            return SslContextBuilder.forClient().trustManager(tmf).build();
        }catch (Exception e){
            throw new Error("error client");
        }
    }

}
