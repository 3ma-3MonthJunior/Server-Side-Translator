
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import redis.clients.jedis.Jedis;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Iterator;
import java.util.Set;


/**
 * Created by a_pan on 28.06.2016.
 */
public class RedisJava {
    static Jedis jedis = new Jedis("localhost");
    public static void main(String[] args) {
        System.out.println("Connection to server sucessfully");
        System.out.println("Server is running: " + jedis.ping());


        //addData();

        //delAllData();

//        String key = "1000";
//        getDataByKey(key);
//
//        String randomKey = jedis.randomKey();
//        System.out.println("2. key - " + randomKey + "; data - " + jedis.get(randomKey));
//
//        printAllDataFromBase();

    }

    private static void getDataByKey(String key){
        System.out.println("1. key - " + key + "; data - " + jedis.get(key));
    }

    private static void printAllDataFromBase(){
        System.out.println("-------------------------------------------------");
        for (int i = 1; i <= jedis.dbSize(); i++){
            String key = Integer.toString(i);
            System.out.println("key - " + i + "; data - " + jedis.get(key));
        }
        System.out.println("-------------------------------------------------");
    }


    private static void delAllData(){
        Set <String> set= jedis.keys("*");
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            jedis.del(iterator.next().toString());
        }
    }

    private static void addData() {

        try {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            f.setValidating(false);
            DocumentBuilder builder = f.newDocumentBuilder();
            Document doc = builder.parse(new File("src/redis-data.xml"));
            NodeList nodeList = doc.getElementsByTagName("record");

            for (int i = 0; i < nodeList.getLength(); i++){
               Element element = (Element) nodeList.item(i);
                Node node = element.getElementsByTagName("id").item(0);
                Node node2 = element.getElementsByTagName("bitcoin").item(0);
                jedis.set(node.getTextContent(), node2.getTextContent());
            }
        } catch (Exception exception) {
            System.out.println("XML parsing error!");
        }
        System.out.println("Size base = " + jedis.dbSize());
    }
}
