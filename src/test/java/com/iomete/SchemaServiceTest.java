package com.iomete;

import com.iomete.sdk.SchemaService;
import com.iomete.sdk.models.ApiError;
import com.iomete.models.Namespace;
import com.iomete.models.NamespaceTable;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class SchemaServiceTest {
    // export IOMETE_SCHEMA_SERVICE_URL=http://localhost:8080
    private final SchemaService service = SchemaService.getInstance();

    @Test
    void getCatalogs() {
        assertDoesNotThrow(() -> {
            List<String> result = service.getCatalogs();
            assertNotNull(result);
            assert (!result.isEmpty());
            assert (result.contains("spark_catalog"));

            System.out.println("Catalogs: " + result);
        });
    }

    @Test
    void getNamespaces() {
        assertDoesNotThrow(() -> {
            List<Namespace> result = service.getNamespaces();
            assertNotNull(result);
            assert (!result.isEmpty());
            assert (result.stream().anyMatch(namespace ->
                    namespace.getCatalog().equals("spark_catalog")
                            && namespace.getNamespace().equals("default"))
            );

            System.out.println("Namespaces: " + result);
        });
    }

    @Test
    void getNamespacesFromSparkCatalog() {
        assertDoesNotThrow(() -> {
            List<Namespace> result = service.getNamespaces("spark_catalog");
            assertNotNull(result);
            assert (!result.isEmpty());
            assert (result.stream().allMatch(namespace -> namespace.getCatalog().equals("spark_catalog")));

            System.out.println("Namespaces from spark_catalog: " + result);
        });
    }

    @Test
    void getNamespacesFromNonExistingCatalog() {
        assertDoesNotThrow(() -> {
            List<Namespace> result = service.getNamespaces("non_existing_catalog");
            assertNotNull(result);
            assert (result.isEmpty());
        });
    }

    @Test
    void getTablesFromSparkCatalog() {
        assertDoesNotThrow(() -> {
            List<NamespaceTable> result = service.getCatalogTables("spark_catalog", true);
            assertNotNull(result);
            assert (!result.isEmpty());
            assert (result.stream().allMatch(table -> table.getCatalog().equals("spark_catalog")));

            System.out.println("Tables from spark_catalog.default: " + result);
        });
    }

    @Test
    void getTablesFromNonExistingCatalog() {
        assertThrows(ApiError.class, () -> {
            List<NamespaceTable> result = service.getCatalogTables("non_existing", false);
            assertNotNull(result);
            assert (result.isEmpty());
        });
    }
}
