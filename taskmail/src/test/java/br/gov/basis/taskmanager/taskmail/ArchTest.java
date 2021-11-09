package br.gov.basis.taskmanager.taskmail;

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
            .importPackages("br.gov.basis.taskmanager.taskmail");

        noClasses()
            .that()
                .resideInAnyPackage("br.gov.basis.taskmanager.taskmail.service..")
            .or()
                .resideInAnyPackage("br.gov.basis.taskmanager.taskmail.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..br.gov.basis.taskmanager.taskmail.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
