package com.cenyol.study.drools;

import com.cenyol.study.drools.models.AirData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Cenyol mail: mr.cenyol@gmail.com
 * @date 01/11/2017 11:00
 */
public class DroolsExample {
    static Logger logger = LoggerFactory.getLogger(DroolsExample.class);

    public static void main(String[] args) {
        AirData[] sensorDatas = {
                new AirData(41.0, 46.0),
                new AirData(39.0, 71.0),
                new AirData(30.0, 51.0),
        };

        new RuleRunner().runRules( new String[] {"whenTempHighThan40C.drl"}, sensorDatas);
    }

    public static void airDataValid(com.cenyol.study.drools.models.raw.AirData airRawData) {
        logger.debug("op[DroolsExample.airDataValid()] Enter airDataValid(airRawData = {})", airRawData);

        if (airRawData == null) {
            logger.error("op[DroolsExample.airDataValid()] airRawData is null");
            return;
        }
        AirData[] sensorDatas = {
                new AirData(airRawData.getData()[0].getValue(), airRawData.getData()[1].getValue()),
        };

        new RuleRunner().runRules( new String[] {"whenTempLowThan25C.drl"}, sensorDatas);

        logger.debug("op[DroolsExample.airDataValid()] Leaving by return void");
    }
}