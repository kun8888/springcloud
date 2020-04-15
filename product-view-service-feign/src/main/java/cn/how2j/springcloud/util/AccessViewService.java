package cn.how2j.springcloud.util;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpUtil;

/**
 * @Description 不停访问视图服务，以便观察断路器监控数据
 * @Author: fk
 * @Date: 2020/3/25 10:24
 */
public class AccessViewService {

    public static void main(String[] args) {
        while (true) {
            ThreadUtil.sleep(1000);
            access(8012);
            access(8013);
        }
    }

    public static void access(int port) {
        try {
            String html = HttpUtil.get(String.format("http://127.0.0.1:%d/products", port));
            System.out.printf("%d 地址的视图服务访问成功，返回大小是 %d%n" ,port, html.length());
        } catch (Exception e) {
            System.err.printf("%d 地址的视图服务无法访问%n",port);
        }
    }

}
