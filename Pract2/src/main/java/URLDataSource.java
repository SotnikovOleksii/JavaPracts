import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;


public class URLDataSource implements DataSource {
    private final URL url;

    public URLDataSource(String urlPath) throws IOException {
        this.url = new URL(urlPath);
    }

    @Override
    public List<String> readLines() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.toList());
        }
    }
}