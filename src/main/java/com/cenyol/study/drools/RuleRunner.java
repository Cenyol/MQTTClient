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

import com.cenyol.study.callback.SensorDataCbk;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.definition.KiePackage;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class RuleRunner {
    Logger logger = LoggerFactory.getLogger(RuleRunner.class);

    private String ruleBaseDirectory = "rules/";

    public RuleRunner() {
    }

    public void runRules(String[] rules,
                         Object[] facts) {

        InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        logger.debug("Starting to add the rule files.");
        for ( int i = 0; i < rules.length; i++ ) {
            String ruleFile = rules[i];
            kbuilder.add( ResourceFactory.newClassPathResource( ruleBaseDirectory + ruleFile ),
                                  ResourceType.DRL );
        }
        logger.debug("Ending to add the rule files.");

        Collection<KiePackage> pkgs = kbuilder.getKnowledgePackages();
        kbase.addPackages( pkgs );
        KieSession ksession = kbase.newKieSession();

        for ( int i = 0; i < facts.length; i++ ) {
            Object fact = facts[i];
            ksession.insert( fact );
        }

        ksession.fireAllRules();
    }
}
