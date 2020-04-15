package cn.how2j.springcloud;

import brave.sampler.Sampler;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;
import java.util.concurrent.*;

/**
 * @Description 启动类
 * @Author: fk
 * @Date: 2019/10/17 14:54
 */
@SpringBootApplication
@EnableEurekaClient
public class ProductDataServiceApplication {

    // TODO 自定义配置取不到值---待解决
//    @Autowired
//    private static DefaultValue defaultValue;

    public static void main(String[] args) {
//        System.out.println(defaultValue.getPort());
        int port = 0;
        int defaultPort = 8001;
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            int p = 0;
            @Override
            public Integer call() throws Exception {
                System.out.println("请于5秒钟内输入端口号, 推荐  8001 、 8002  或者  8003，超过5秒将默认使用 "
                        + defaultPort);
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String printPort = scanner.nextLine();
                    if(!NumberUtil.isInteger(printPort)){
                        System.out.println("只能是数字！");
                    } else {
                        p = Convert.toInt(printPort);
                        scanner.close();
                        break;
                    }
                }
                return p;
            }
        });
        try {
            port = future.get(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            port = defaultPort;
        }
        System.out.println("将使用 " + port + " 端口");
        if(!NetUtil.isUsableLocalPort(port)){
            System.out.printf("端口%d被占用了，无法启动%n", port);
            System.exit(1);
        }
        new SpringApplicationBuilder(ProductDataServiceApplication.class).properties("server.port=" + port).run(args);
    }

    @Bean
    public Sampler defaultSampler() {
        // ALWAYS_SAMPLE：持续抽样
        return Sampler.ALWAYS_SAMPLE;
    }

    @Bean
    @LoadBalanced //开启客户端负载均衡
    public RestTemplate restTemplate() {
        //restTemplate 设置超时
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(300000);
        simpleClientHttpRequestFactory.setReadTimeout(300000);
        return new RestTemplate(simpleClientHttpRequestFactory);
    }

}
