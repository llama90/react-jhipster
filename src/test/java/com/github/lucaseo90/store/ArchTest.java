package com.github.lucaseo90.store;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.github.lucaseo90.store");

        noClasses()
            .that()
                .resideInAnyPackage("com.github.lucaseo90.store.service..")
            .or()
                .resideInAnyPackage("com.github.lucaseo90.store.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.github.lucaseo90.store.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
