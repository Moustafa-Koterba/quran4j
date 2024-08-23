package quran4j.infra;

import com.pulumi.Context;
import com.pulumi.Pulumi;
import quran4j.infra.data.DataService;

public class InfraApplication {

    public static void main(String[] args) {
        Pulumi.run(InfraApplication::stack);
    }

    public static void stack(Context ctx) {
        var resourceGroup = DataService.resourceGroup("quran4j-data-resource-group");
        var server = DataService.server("quran4j-data-database-server", resourceGroup.location(), resourceGroup.name());
        var database = DataService.database("quran4j-data-database-quran", resourceGroup.name(), server.name());
    }
}