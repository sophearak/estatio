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
package org.estatio.integtest.testing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;

import org.estatio.dom.geography.Country;
import org.estatio.dom.geography.State;

public class GeographyIntegrationTest extends AbstractEstatioIntegrationTest {


    @Test
    public void countryIsNL() throws Exception {
        assertThat(countries.findCountryByReference("NLD").getReference(), is("NLD"));
    }

    @Test
    public void stateCanBeFound() throws Exception {
        final Country country = countries.findCountryByReference("NLD");
        final List<State> statesInCountry = states.findStatesByCountry(country);
        assertThat(statesInCountry.size(), Matchers.greaterThanOrEqualTo(1));
        for (State state : statesInCountry) {
            assertThat(state.getCountry(), is(country));
        }
    }
}
