package com.smag.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.smag.dronesmanagement.scheduler.LowBatteryTask;
import com.smag.dronesmanagement.services.IDroneService;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "com.smag.dronesmanagement", importOptions = ImportOption.DoNotIncludeTests.class)
public class LayerRuleTest {
    @ArchTest
    static final ArchRule services_should_not_access_controllers =
            noClasses().that().resideInAPackage("..services..")
                    .should().accessClassesThat().resideInAPackage("..controllers..");
    
    @ArchTest
    static final ArchRule layer_dependencies_are_respected_with_exception = layeredArchitecture().consideringAllDependencies()

            .layer("Controllers").definedBy("com.smag.dronesmanagement.controllers..")
            .layer("Services").definedBy("com.smag.dronesmanagement.services..")
            .layer("Persistence").definedBy("com.smag.dronesmanagement.repositories..")

            .whereLayer("Controllers").mayNotBeAccessedByAnyLayer()
            .whereLayer("Services").mayOnlyBeAccessedByLayers("Controllers")
            .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Services")
            
            .ignoreDependency(LowBatteryTask.class, IDroneService.class);


}
