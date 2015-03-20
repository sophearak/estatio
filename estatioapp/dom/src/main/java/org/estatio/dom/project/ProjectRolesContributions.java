/*
 *
 *  Copyright 2012-2015 Eurocommercial Properties NV
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
package org.estatio.dom.project;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.Contributed;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.RenderType;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.estatio.dom.EstatioDomainService;
import org.estatio.dom.party.Party;
import org.joda.time.LocalDate;

@DomainService(nature=NatureOfService.VIEW_CONTRIBUTIONS_ONLY)
public class ProjectRolesContributions extends EstatioDomainService<ProjectRole> {

    public ProjectRolesContributions() {
        super(ProjectRolesContributions.class, ProjectRole.class);
    }

    // //////////////////////////////////////

    @Action(semantics=SemanticsOf.SAFE)
    @ActionLayout(contributed=Contributed.AS_ASSOCIATION)
    @CollectionLayout(render=RenderType.EAGERLY)
    public List<ProjectRole> roles(
            final Project project) {
        return allMatches("findByProject",
                "project", project);
    }
    
    public Project newProjectRole(
    		final Project project,
            final @ParameterLayout(named = "Type") ProjectRoleType type,
            final Party party,
            final @ParameterLayout(named = "Start date") @Parameter(optionality=Optionality.OPTIONAL) LocalDate startDate,
            final @ParameterLayout(named = "End date") @Parameter(optionality=Optionality.OPTIONAL) LocalDate endDate) {    	
        projectRoles.createRole(project, type, party, startDate, endDate);
        return project;
    }
    
    // //////////////////////////////////////
    
    
    public String validateNewProjectRole(
    		final Project project,
            final ProjectRoleType type,
            final Party party,
            final LocalDate startDate,
            final LocalDate endDate) {
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            return "End date cannot be earlier than start date";
        }
//        if (!Sets.filter(getRoles(), type.matchingRole()).isEmpty()) {
//            return "Add a successor/predecessor from existing role";
//        }
        return null;
    }

    @Inject
    ProjectRoles projectRoles;

}
