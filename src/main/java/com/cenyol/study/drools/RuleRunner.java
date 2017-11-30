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

import com.cenyol.study.utils.HttpRequest;
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
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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


    public void runRulesFromDB(Object[] facts) {
        InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        ArrayList<String> ruleContents = this.loadRulesFromDB();
        for (String ruleContent : ruleContents) {
            Resource r = ResourceFactory.newReaderResource(new StringReader(ruleContent));
            kbuilder.add( r, ResourceType.DRL );
        }
        if(kbuilder.hasErrors()){
            logger.error("规则中存在错误，错误消息如下：");
            KnowledgeBuilderErrors kbuidlerErrors=kbuilder.getErrors();
            String errorMsg = "";
            for(Iterator iter = kbuidlerErrors.iterator(); iter.hasNext();) {
                logger.error(iter.next().toString());
                errorMsg += iter.next().toString();
            }
            String postJson = "{\"num\":\"rune-engine-0001\", \"name\":\"规则引擎\",\"level\":3,\"describtion\":\"" + errorMsg + "\"}";
            HttpRequest.sendPost("http://agriot-api.cenyol.com/operate-log/save", "data=" + postJson);
            return ;
        }
        Collection<KiePackage> pkgs = kbuilder.getKnowledgePackages();
        kbase.addPackages( pkgs );
        KieSession ksession = kbase.newKieSession();

        for ( int i = 0; i < facts.length; i++ ) {
            Object fact = facts[i];
            logger.info( "Inserting fact: {}", fact );
            ksession.insert( fact );
        }
        ksession.fireAllRules();
    }

    public ArrayList<String> loadRulesFromDB() {
        return new MySQL().queryRule();
    }
}
