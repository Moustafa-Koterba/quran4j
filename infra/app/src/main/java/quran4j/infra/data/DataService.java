package quran4j.infra.data;

import com.pulumi.azurenative.dbformysql.Database;
import com.pulumi.azurenative.dbformysql.DatabaseArgs;
import com.pulumi.azurenative.dbformysql.Server;
import com.pulumi.azurenative.dbformysql.ServerArgs;
import com.pulumi.azurenative.dbformysql.enums.ServerVersion;
import com.pulumi.azurenative.dbformysql.enums.SkuTier;
import com.pulumi.azurenative.dbformysql.inputs.SkuArgs;
import com.pulumi.azurenative.dbformysql.inputs.StorageArgs;
import com.pulumi.azurenative.resources.ResourceGroup;
import com.pulumi.azurenative.resources.ResourceGroupArgs;
import com.pulumi.core.Output;

public final class DataService {
    private DataService() {}

    public static Database database(String name, Output<String> resourceGroupName, Output<String> serverName) {
        return new Database(name, DatabaseArgs.builder()
                .resourceGroupName(resourceGroupName)
                .serverName(serverName)
                .databaseName("quran4j")
                .build());
    }

    public static Server server(String name, Output<String> location, Output<String> resourceGroupName) {
        return new Server(name, ServerArgs.builder()
                .sku(SkuArgs.builder()
                        .name("Standard_B1ms")
                        .tier(SkuTier.Burstable)
                        .build())
                .version(ServerVersion._8_0_21)
                .administratorLogin("postgres")
                .administratorLoginPassword("postgres")
                .location(location)
                .resourceGroupName(resourceGroupName)
                .storage(StorageArgs.builder().storageSizeGB(32).build())
                .build());
    }

    public static ResourceGroup resourceGroup(String name) {
        return new ResourceGroup(name, ResourceGroupArgs.builder()
                .location("France Central")
                .build());
    }
}
