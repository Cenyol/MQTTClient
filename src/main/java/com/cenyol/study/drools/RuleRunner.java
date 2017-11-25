/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cenyol.study.drools;

import com.cenyol.study.utils.MySQL;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.KieServices;
import org.kie.api.definition.KiePackage;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;
import java.util.Collection;

public class RuleRunner {
    Logger logger = LoggerFactory.getLogger(RuleRunner.class);

    public void runRules(String[] rules, Object[] facts) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();
        KieSession ksession = kc.newKieSession("airData-rulesKS");

        for ( int i = 0; i < facts.length; i++ ) {
            Object fact = facts[i];
            ksession.insert( fact );
        }

        ksession.fireAllRules();
    }


    public void runRulesFromDB(String[] rules,
                         Object[] facts) {

        InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        String ruleContent = this.loadRulesFromDB();
        for ( int i = 0; i < rules.length; i++ ) {
            String ruleFile = rules[i];
            System.out.println( "Loading rules from db: " + ruleFile );
            Resource r = ResourceFactory.newReaderResource(new StringReader(ruleContent));
            kbuilder.add( r, ResourceType.DRL );
        }

        Collection<KiePackage> pkgs = kbuilder.getKnowledgePackages();
        kbase.addPackages( pkgs );
        KieSession ksession = kbase.newKieSession();

        for ( int i = 0; i < facts.length; i++ ) {
            Object fact = facts[i];
            System.out.println( "Inserting fact: " + fact );
            ksession.insert( fact );
        }
        ksession.fireAllRules();
    }

    public String loadRulesFromDB() {
        return new MySQL().queryRule();

//        return "package com.cenyol.study.drools\n" +
//                "\n" +
//                "import com.cenyol.study.drools.models.AirData;\n" +
//                "import com.cenyol.study.drools.actors.AirTempActor\n" +
//                "import com.cenyol.study.drools.actors.AirHumiActor;\n" +
//                "\n" +
//                "rule \"Temp lower than 25C\"\n" +
//                "    salience 99\n" +
//                "    when\n" +
//                "        airData : AirData(airTemp < 25.0 && createdTime < \"21:00:00\")\n" +
//                "    then\n" +
//                "        AirTempActor.up();\n" +
//                "        airData.setValid(false);\n" +
//                "end\n" +
//                "\n" +
//                "rule \"Humi lower than 45%\"\n" +
//                "    salience 9\n" +
//                "    when\n" +
//                "        airData : AirData(airHumi < 45)\n" +
//                "    then\n" +
//                "        AirHumiActor.up();\n" +
//                "        airData.setValid(false);\n" +
//                "end\n";
    }
}
