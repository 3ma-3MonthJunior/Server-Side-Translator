import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ElasticFiller  {

    private List<ElasticBean> arrayListFromJson = new ArrayList<>();

    public List<ElasticBean> getArrayListFromJson() {
        try{

            JsonParser jParser = new JsonFactory().createParser(new File("src\\main\\resources\\json\\elastic-data.json"));

            while (jParser.nextToken() != JsonToken.END_ARRAY){

                ElasticBean eBean = new ElasticBean();

                while (jParser.nextToken() != JsonToken.END_OBJECT) {
                    String token = jParser.getCurrentName();

                    if ("id".equals(token)) {
                        jParser.nextToken();
                        eBean.setId(jParser.getIntValue());
                    } else if ("gender".equals(token)) {
                        jParser.nextToken();
                        eBean.setGender(jParser.getValueAsString());
                    } else if ("first_name".equals(token)) {
                        jParser.nextToken();
                        eBean.setFirst_name(jParser.getValueAsString());
                    } else if ("last_name".equals(token)) {
                        jParser.nextToken();
                        eBean.setLast_name(jParser.getValueAsString());
                    } else if ("email".equals(token)) {
                        jParser.nextToken();
                        eBean.setEmail(jParser.getValueAsString());
                    } else if ("ip_address".equals(token)) {
                        jParser.nextToken();
                        eBean.setIp_address(jParser.getValueAsString());
                    }

                }

                arrayListFromJson.add(eBean);
            }

            jParser.close();

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return arrayListFromJson;
    }

    public List<ElasticBean> gettterArrayListFromJson() {
        return this.arrayListFromJson;
    }

}