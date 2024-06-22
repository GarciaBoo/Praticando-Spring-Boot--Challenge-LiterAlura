package literalura;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GutendexService {

    private static final String GUTENDEX_API_URL = "https://gutendex.com/books";
    private static final Logger logger = LoggerFactory.getLogger(GutendexService.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Book> fetchBooksFromGutendex(String searchTerm) {
        try {
            String url = GUTENDEX_API_URL + "?search=" + searchTerm.replace(" ", "%20");
            String responseBody = executeHttpGetRequest(url);
            return parseJsonResponse(responseBody);
        } catch (IOException e) {
            // Logar a exceção ou rethrow com informações contextuais
            throw new GutendexApiException("Failed to fetch books from Gutendex API", e);
        }
    }

    private String executeHttpGetRequest(String url) throws IOException {
        StringBuilder response = new StringBuilder();

        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                }
            } else {
                throw new GutendexApiException("Request failed: Status code " + responseCode);
            }
        } catch (IOException e) {
            // Logar a exceção ou rethrow com informações contextuais
            throw new GutendexApiException("Failed to execute HTTP GET request", e);
        }

        return response.toString();
    }

    private List<Book> parseJsonResponse(String responseBody) {
        List<Book> books = new ArrayList<>();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseBody).get("results");
            for (JsonNode node : jsonNode) {
                Book book = new Book();
                book.setTitle(node.get("title").asText());
                book.setAuthors(parseAuthorsNames(node.withArray("authors")));
                book.setLanguage(node.withArray("languages").get(0).asText()); // Assume-se que há apenas um idioma, pegando o primeiro da lista
                book.setDownloadCount(node.get("download_count").asInt()); // Aqui acessa diretamente o "download_count" no nó principal
                books.add(book);
            }
        } catch (IOException e) {
            logger.error("Failed to parse JSON response", e);
            throw new GutendexApiException("Failed to parse JSON response", e);
        }
        return books;
    }
    

    private List<Book.Author> parseAuthorsNames(JsonNode authorsArray) {
        List<Book.Author> authors = new ArrayList<>();
        authorsArray.forEach(authorNode -> {
            Book.Author author = new Book.Author();
            author.setName(authorNode.get("name").asText());
            // Se necessário, adicione os anos de nascimento e morte aqui
            authors.add(author);
        });
        return authors;
    }
    
}