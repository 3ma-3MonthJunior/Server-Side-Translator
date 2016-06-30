import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

/**
 * Created by a_pan on 20.06.2016.
 */
public class Start {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();


        try {

            Client client = TransportClient.builder().build()
//                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host1"), 32796));
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.99.100"), 32796));

            System.out.println(client.admin().cluster() + "+++");

            Object obj = parser.parse(new FileReader(
                    "d:/Rez/elastic-data.json"));

//            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray) obj;
            for (int i = 0; i < 10; i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
//                IndexResponse response = client.prepareIndex("twitter", "tweet")
//                        .setSource(jsonObject)
//                        .get();
//                System.out.println(response.toString() + "++");


                Long id = (Long) jsonObject.get("id");
                System.out.println(jsonObject.toJSONString());
                //excutePost(id, jsonObject);
                //getData(id);
//                String gender = (String) jsonObject.get("gender");
//                String firstName = (String) jsonObject.get("first_name");
//                String lastName = (String) jsonObject.get("last_name");
//                String email = (String) jsonObject.get("email");
//                String ipAddress = (String) jsonObject.get("ip_address");
//
//                System.out.println("id : " + id);
//                System.out.println("gender : " + gender);
//                System.out.println("first name : " + firstName);
//                System.out.println("last name : " + lastName);
//                System.out.println("e-mail : " + email);
//                System.out.println("ip address : " + ipAddress);
//                System.out.println("-----------------------------------------------------------------------------");

            }





//            String id = (String) jsonObject.get("id");
//            String gender = (String) jsonObject.get("gender");
//            String firstName = (String) jsonObject.get("first_name");
//            String lastName = (String) jsonObject.get("last_name");
//            String email = (String) jsonObject.get("email");
//            String ipAddress = (String) jsonObject.get("ip_address");







        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void excutePost(long id,JSONObject object) {


        //System.out.println(object.toJSONString());
        try {
            Client client = TransportClient.builder().build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("http://192.168.99.100"), 32769));
            System.out.println(5);



//        String url = "http://192.168.99.100:32769/" + "user/" + id;
//        URL obj = new URL(url);
//            System.out.println(obj.toString());
//
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//
//        //add reuqest header
//
//
//
//            con.setRequestMethod("POST");
//            con.setRequestProperty("User-Agent", "Mozilla/5.0");
//            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//
//            String urlParameters = object.toString();
//
//            System.out.println(urlParameters);
//
//            // Send post request
//            con.setDoOutput(true);
//            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//            wr.writeBytes(urlParameters);
//            wr.flush();
//            wr.close();
//
//            int responseCode = con.getResponseCode();
//            System.out.println("\nSending 'POST' request to URL : " + url);
//            System.out.println("Post parameters : " + urlParameters);
//            System.out.println("Response Code : " + responseCode);
//
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
//            //print result
//            System.out.println(response.toString());
        } catch (Exception ex){
//            System.out.println("++++++++++++++++++++++++");
//            ex.printStackTrace();

        }
    }

    public static void getData(long id){
        HttpURLConnection connection = null;
        try {
            //Create connection
            URL url = new URL("http://192.168.99.100:32769/" + "user/" + id );
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/json");


//            connection.setRequestProperty("Content-Length",
//                    Integer.toString(urlParameters.getBytes().length));
//            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);
                    //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+
            String line;
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            //return null;
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
    }
//    public  static void putDataToServer(String url,JSONObject returnedJObject) throws Throwable  {
//
//        HttpPost request = new HttpPost(url);
//        JSONStringer json = new JSONStringer();
//        StringBuilder sb=new StringBuilder();
//
//
//        if (returnedJObject!=null)
//        {
//            Iterator<String> itKeys = returnedJObject.keys();
//            if(itKeys.hasNext())
//                json.object();
//            while (itKeys.hasNext())
//            {
//                String k=itKeys.next();
//                json.key(k).value(returnedJObject.get(k));
//                Log.e("keys "+k,"value "+returnedJObject.get(k).toString());
//            }
//        }
//        json.endObject();
//
//
//        StringEntity entity = new StringEntity(json.toString());
//        entity.setContentType("application/json;charset=UTF-8");
//        entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
//        request.setHeader("Accept", "application/json");
//        request.setEntity(entity);
//
//        HttpResponse response =null;
//        DefaultHttpClient httpClient = new DefaultHttpClient();
//
//        HttpConnectionParams.setSoTimeout(httpClient.getParams(), Constants.ANDROID_CONNECTION_TIMEOUT*1000);
//        HttpConnectionParams.setConnectionTimeout(httpClient.getParams(),Constants.ANDROID_CONNECTION_TIMEOUT*1000);
//        try{
//
//            response = httpClient.execute(request);
//        }
//        catch(SocketException se)
//        {
//            Log.e("SocketException", se+"");
//            throw se;
//        }
//
//
//
//
//        InputStream in = response.getEntity().getContent();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//        String line = null;
//        while((line = reader.readLine()) != null){
//            sb.append(line);
//
//        }
//
//        return sb.toString();
//    }
}
