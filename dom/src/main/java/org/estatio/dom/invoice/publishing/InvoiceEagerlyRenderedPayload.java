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
package org.estatio.dom.invoice.publishing;

import java.util.List;

import org.apache.isis.applib.Identifier;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.services.publish.EventPayloadForActionInvocation;

import org.estatio.dom.invoice.Invoice;

public class InvoiceEagerlyRenderedPayload extends EventPayloadForActionInvocation<Invoice> {

    public InvoiceEagerlyRenderedPayload(final Identifier actionIdentifier, final Invoice target, final List<? extends Object> arguments, final Object result) {
        super(actionIdentifier, target, arguments, result);
    }

    @Override
    @Render(Type.EAGERLY)
    public Invoice getTarget() {
        return super.getTarget();
    }
    
}
