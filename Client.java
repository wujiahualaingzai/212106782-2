package com.nbac;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        Product demo = new Product();
        demo.getProduct(); //该方法获取数据
        demo.show(); //该方法在控制台输出信息
        demo.fluctuate(); //该方法获取价格的波动
    }
}
