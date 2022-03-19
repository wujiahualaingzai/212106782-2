package com.nbac;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;

public class Product {
    private String share_content;//产品详细信息
    private double market_price;//产品原价
    private double price;//产品折扣价
    private String spec;//产品规格
    private String name;//产品名称
    //该方法获取数据
    public  void getProduct() throws IOException {
        //根据fiddler抓取到的URL来获取数据
        URL url = new URL("https://j1.pupuapi.com/client/product/storeproduct/detail/deef1dd8-65ee-46bc-9e18-8cf1478a67e9/09906e40-9900-48e4-98fd-2ea45521cd3c");
        //用字符处理流获取url的数据
        BufferedReader reaDer = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder builder = new StringBuilder();
        String str = "";
        //使数据添加入StringBuilder里
        while ((str = reaDer.readLine()) != null) {
            builder.append(str);
        }
        String string = builder.toString();
        //解析成JSONObject
        JSONObject object = (JSONObject) JSONObject.parse(string);
        name = (String) object.getJSONObject("data").get("name");
        spec = (String) object.getJSONObject("data").get("spec");
        price = (double) ((Integer) object.getJSONObject("data").get("price")) / 100;
        market_price = (double) ((Integer) object.getJSONObject("data").get("market_price")) / 100;
        share_content = (String) object.getJSONObject("data").get("share_content");
    }
    //该方法在控制台输出信息
    public void show(){
        System.out.println("---------------------商品：" + name + "---------------------");
        System.out.println("规格：" + spec);
        System.out.println("价格：" + price);
        System.out.println("原价/折扣价："+market_price+"/"+price);
        System.out.println("详细信息：" + share_content);
        System.out.println("------------------" + name + "的价格波动------------------");
    }
    //该方法获取价格的波动
    public void fluctuate() throws InterruptedException, IOException {
//使用while（true）可以使while一直进行下去，可以让我们看到每个时间的产品价格
        while (true){
            getProduct();//得到数据
            LocalDateTime now = LocalDateTime.now();//获取现在的时间
            System.out.println("当前时间为"+ now+" ,"+name+":价格为"+price);
            Thread.sleep(6000);//间隔时间
        }
    }
}