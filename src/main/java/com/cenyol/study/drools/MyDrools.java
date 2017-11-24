package com.cenyol.study.drools;

import com.cenyol.study.drools.models.AirData;
import com.cenyol.study.drools.models.raw.OneSensorData;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Cenyol mail: mr.cenyol@gmail.com
 * @date 01/11/2017 11:00
 */
public class MyDrools {
    static Logger logger = LoggerFactory.getLogger(MyDrools.class);

    public static void main(String[] args) {
        AirData[] sensorDatas = {
                new AirData(41.0, 46.0),
                new AirData(39.0, 71.0),
                new AirData(30.0, 51.0),
        };
//        new RuleRunner().runRules( new String[] {"whenTempHighThan25C.drl"}, sensorDatas);

        String messageString = "{\"mac\":\"5ccf7f36fa68\", \"data\":[]}";
//        messageString = "{\"mac\":\"5ccf7f3778d1\", \"data\":[{\"type_id\":1,\n" +
//                "\"value\":13},{\"type_id\":2, \"value\":24}]}";
        Gson gson = new Gson();
        com.cenyol.study.drools.models.raw.AirData airData = gson.fromJson(messageString, com.cenyol.study.drools.models.raw.AirData.class);
        MyDrools.airDataValid(airData.getData());
    }

    public static void airDataValid(OneSensorData[] oneSensorData) {
        logger.debug("Entering method(oneSensorData = {})", oneSensorData);
        if (oneSensorData.length == 0) {
            logger.error("oneSensorData is null");
        }else {
            AirData[] sensorDatas = {
                    new AirData(oneSensorData[0].getValue(), oneSensorData[1].getValue()),
            };
            new RuleRunner().runRules( new String[] {"whenTempLowThan25C.drl"}, sensorDatas);
        }
        logger.debug("Leaving by return void\n\n");
    }
}