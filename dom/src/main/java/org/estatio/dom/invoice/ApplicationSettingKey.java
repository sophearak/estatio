/*
 *
 *  Copyright 2012-2014 Eurocommercial Properties NV
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the
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
package org.estatio.dom.invoice;

import org.isisaddons.module.settings.dom.ApplicationSetting;
import org.isisaddons.module.settings.dom.ApplicationSettingsServiceRW;
import org.estatio.services.settings.ApplicationSettingCreator;

public enum ApplicationSettingKey implements ApplicationSettingCreator {
    reportURLInvoice(
            String.class, 
            "Invoice report", 
            "http://scapt/Reports/Pages/Report.aspx?ItemPath=%2fEstatio%sfInvoices&id={invoice.id}"),
    reportURLInvoices(
            String.class, 
            "Invoice report", 
            "http://scapt/Reports/Pages/Report.aspx?ItemPath=%2fEstatio%sfInvoices&id={invoice.id}");
    
    private final Object defaultValue;
    private final String description;
    private final Class<?> dataType;
    
    private ApplicationSettingKey(final Class<?> dataType, final String description, final Object defaultValue) {
        this.dataType = dataType;
        this.description = description;
        this.defaultValue = defaultValue;
    }
    @Override
    public void create(final ApplicationSettingsServiceRW appSettings) {
        Helper.create(this, appSettings);
    }
    @Override
    public ApplicationSetting find(final ApplicationSettingsServiceRW appSettings) {
        return Helper.find(this, appSettings);
    }
    @Override
    public Class<?> getDataType() {
        return dataType;
    }
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }
}
