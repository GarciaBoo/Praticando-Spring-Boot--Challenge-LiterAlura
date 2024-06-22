package literalura;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    private final GutendexService gutendexService;

    @Autowired
    public LiteraluraApplication(GutendexService gutendexService) {
        this.gutendexService = gutendexService;
    }

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            exibirMenu();

            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar o buffer de entrada

            switch (opcao) {
                case 1:
                    buscarLivrosPorTermo(scanner);
                    break;
                case 2:
                    System.out.println("Opção de inserção de dados selecionada.");
                    // Implemente aqui a lógica para inserção de dados
                    break;
                case 3:
                    System.out.println("Opção de consulta de dados selecionada.");
                    // Implemente aqui a lógica para consulta de dados
                    break;
                case 4:
                    System.out.println("Saindo do programa...");
                    return;
                default:
                     System.out.println("Opção inválida. Por favor, escolha novamente.");
                    break;
            }
        }
    }

    private void exibirMenu() {
        System.out.println("==== Menu ====");
        System.out.println("1 - Buscar livros por autor ou título");
        System.out.println("2 - Inserir dados");
        System.out.println("3 - Consultar dados");
        System.out.println("4 - Sair");
        System.out.println("Escolha uma opção: ");
    }

    private void buscarLivrosPorTermo(Scanner scanner) {
        System.out.println("Digite o termo de busca (autor ou título): ");
        String termoBusca = scanner.nextLine();

        try {
            List<Book> livrosEncontrados = gutendexService.fetchBooksFromGutendex(termoBusca);

            System.out.println("Livros encontrados para '" + termoBusca + "':");
            for (Book livro : livrosEncontrados) {
                System.out.println("Título: " + livro.getTitle());
                System.out.println("Autores:");
                for (Book.Author autor : livro.getAuthors()) {
                    System.out.println("  - " + autor.getName());
                }
                System.out.println("Idioma: " + livro.getLanguage());
                System.out.println("Número de downloads: " + livro.getDownloadCount());
                System.out.println(""); // linha em branco para separar os livros
            }
        } catch (GutendexApiException e) {
            System.err.println("Erro ao buscar livros: " + e.getMessage());
        }
    }
}