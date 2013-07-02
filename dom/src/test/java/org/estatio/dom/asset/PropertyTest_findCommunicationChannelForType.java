/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.estatio.dom.asset;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.apache.isis.core.unittestsupport.jmocking.JUnitRuleMockery2;
import org.apache.isis.core.unittestsupport.jmocking.JUnitRuleMockery2.Mode;

import org.estatio.dom.communicationchannel.CommunicationChannelType;
import org.estatio.dom.communicationchannel.EmailAddress;
import org.estatio.dom.communicationchannel.PostalAddress;

public class PropertyTest_findCommunicationChannelForType {

    private Property p ;
    
    @Mock
    Properties mockProperties;

    @Rule
    public JUnitRuleMockery2 context = JUnitRuleMockery2.createFor(Mode.INTERFACES_AND_CLASSES);

    private PostalAddress pa;

    private EmailAddress ea;

    @Before
    public void setup() {
        p =  new Property();
        
        pa = new PostalAddress();
        pa.setType(CommunicationChannelType.POSTAL_ADDRESS);
        pa.setCity("Amsterdam");
        
        ea = new EmailAddress();
        ea.setType(CommunicationChannelType.EMAIL_ADDRESS);
        ea.setAddress("joe@bloggs.com");
    }

    @Test
    public void happyCase() {
        p.getCommunicationChannels().add(pa);
        p.getCommunicationChannels().add(ea);

        PostalAddress pa = (PostalAddress) p.findCommunicationChannelForType(CommunicationChannelType.POSTAL_ADDRESS);
        assertThat(pa.getCity(), is("Amsterdam"));

        EmailAddress ea = (EmailAddress) p.findCommunicationChannelForType(CommunicationChannelType.EMAIL_ADDRESS);
        assertThat(ea.getAddress(), is("joe@bloggs.com"));
    }

    @Test
    public void noneAvailable() {

        PostalAddress pa = (PostalAddress) p.findCommunicationChannelForType(CommunicationChannelType.POSTAL_ADDRESS);
        assertThat(pa, is(nullValue()));
    }


}
