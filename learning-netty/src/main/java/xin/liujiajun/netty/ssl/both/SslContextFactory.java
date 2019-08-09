package xin.liujiajun.netty.ssl.both;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.security.KeyStore;

/**
 * @author liujiajun
 * @date 2019-08-08 14:11
 **/
public class SslContextFactory {

    private static final String PROTOCOL = "TLS";
    private static final String PASSWORD = "123456";

    public static SslContext getServerContext() {
        try {
            KeyStore ks = KeyStore.getInstance("JKS");
            FileInputStream input = new FileInputStream("data/ssl/both/serverKeys.jks");

            ks.load(input, PASSWORD.toCharArray());


            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, PASSWORD.toCharArray());

            //truststore
            KeyStore ts = KeyStore.getInstance("JKS");
            input = new FileInputStream("data/ssl/both/clientKeys.jks");
            ts.load(input, PASSWORD.toCharArray());

            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ts);


            if (input != null) {
                input.close();
            }
            return SslContextBuilder.forServer(kmf).trustManager(tmf).build();

        } catch (Exception e) {
            throw new Error("Failed to initialize the server SSLContext", e);
        }

    }

    public static SslContext getClientContext() {
        try {

            FileInputStream input = new FileInputStream("data/ssl/both/clientKeys.jks");

            // keystore
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(input, PASSWORD.toCharArray());

            // Set up key manager factory to use our key store
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, PASSWORD.toCharArray());

            // truststore
            KeyStore ts = KeyStore.getInstance("JKS");

            input = new FileInputStream("data/ssl/both/serverKeys.jks");

            ts.load(input, PASSWORD.toCharArray());

            // set up trust manager factory to use our trust store
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ts);

            input.close();
            return SslContextBuilder.forClient().keyManager(kmf).trustManager(tmf).build();

        } catch (Exception e) {
            throw new Error("Failed to initialize the client-side SSLContext", e);
        }
    }

}
