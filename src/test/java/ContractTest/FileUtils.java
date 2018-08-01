package ContractTest;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;

public final class FileUtils {
    public static String readFromClassPath(String resourceName) throws IOException {
        return Resources.toString(Resources.getResource(resourceName), Charsets.UTF_8);
    }


    private FileUtils() {

    }
}
