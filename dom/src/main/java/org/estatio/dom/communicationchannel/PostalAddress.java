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
package org.estatio.dom.communicationchannel;

import java.util.List;

import javax.jdo.annotations.InheritanceStrategy;

import org.estatio.dom.geography.Countries;
import org.estatio.dom.geography.Country;
import org.estatio.dom.geography.State;
import org.estatio.dom.geography.States;

import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.util.TitleBuffer;

@javax.jdo.annotations.PersistenceCapable
@javax.jdo.annotations.Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
@javax.jdo.annotations.Query(name = "findByAddress", language = "JDOQL", value = "SELECT FROM org.estatio.dom.communicationchannel.CommunicationChannel WHERE (address1 == :address1 && postalCode == :postalCode && city == :city && country == :country)")

public class PostalAddress extends CommunicationChannel {

    /**
     * This is NOT the <tt>@Title</tt>, because the title omits the
     * <i>address2</i> attribute.
     * 
     * TODO: is this inconsistency intentional?
     */
    @Override
    public String getName() {
        TitleBuffer title = new TitleBuffer(getAddress1());
        title.append(", ", getAddress2());
        title.append(", ", getPostalCode());
        title.append(", ", getCity());
        return title.toString();
    }

    // //////////////////////////////////////

    private String address1;

    @Title(sequence = "1", append = ", ")
    @MemberOrder(sequence = "1")
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(final String address1) {
        this.address1 = address1;
    }

    public String disableAddress1() {
        return getStatus().isLocked() ? "Cannot modify when locked" : null;
    }

    // //////////////////////////////////////

    private String address2;

    @Optional
    @MemberOrder(sequence = "2")
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(final String address2) {
        this.address2 = address2;
    }

    public String disableAddress2() {
        return getStatus().isLocked() ? "Cannot modify when locked" : null;
    }

    // //////////////////////////////////////

    private String postalCode;

    @Title(sequence = "2", append = ", ")
    @MemberOrder(sequence = "3")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

    public String disablePostalCode() {
        return getStatus().isLocked() ? "Cannot modify when locked" : null;
    }

    // //////////////////////////////////////

    private String city;

    @Title(sequence = "3")
    @MemberOrder(sequence = "4")
    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String disableCity() {
        return getStatus().isLocked() ? "Cannot modify when locked" : null;
    }

    // //////////////////////////////////////

    // TODO: Country does not render as dropdown
    // TODO: When no country has been selected, the UI renders
    // "no objects returned" and an ok button. After that it's impossible to to
    // a search again.
    @javax.jdo.annotations.Column(name = "COUNTRY_ID")
    private Country country;

    @Optional
    @MemberOrder(sequence = "5")
    public Country getCountry() {
        return country;
    }

    public void setCountry(final Country country) {
        this.country = country;
    }

    public String disableCountry() {
        return getStatus().isLocked() ? "Cannot modify when locked" : null;
    }

    public List<Country> choicesCountry() {
        return countries.allCountries();
    }

    public void modifyCountry(final Country country) {
        setCountry(country);
        if (getState() != null && getState().getCountry() != country) {
            setState(null);
        }
    }
    
    public void clearCountry() {
        setCountry(null);
        setState(null);
    }
    
    // //////////////////////////////////////

    @javax.jdo.annotations.Column(name = "STATE_ID")
    private State state;

    @Optional
    @MemberOrder(sequence = "6")
    public State getState() {
        return state;
    }

    public void setState(final State state) {
        this.state = state;
    }

    public String disableState() {
        return getStatus().isLocked() ? "Cannot modify when locked" : null;
    }

    public List<State> choicesState() {
        return states.findStatesByCountry(getCountry());
    }
    
    // //////////////////////////////////////

    private States states;

    public void injectStates(final States states) {
        this.states = states;
    }

    private Countries countries;

    public void injectCountries(Countries countries) {
        this.countries = countries;
    }

}
