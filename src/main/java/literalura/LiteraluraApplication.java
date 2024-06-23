package literalura;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";

    private final GutendexService gutendexService;
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public LiteraluraApplication(GutendexService gutendexService, LivroRepository livroRepository, AutorRepository autorRepository) {
        this.gutendexService = gutendexService;
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            exibirMenu();

            try {
                int opcao = Integer.parseInt(scanner.nextLine().trim());

                switch (opcao) {
                    case 1:
                        buscarLivrosPorTermo(scanner);
                        break;
                    case 2:
                        listarLivrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarLivrosPorIdioma(scanner);
                        break;
                    case 5:
                        listarAutoresVivosNoAno(scanner);
                        break;
                    case 6:
                        System.out.println("Saindo do programa...");
                        return;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha novamente.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número válido.");
                scanner.nextLine(); // clear the input buffer
            }
        }
    }

    private void exibirMenu() {
        System.out.println("==== Menu ====");
        System.out.println("1 - Buscar livros por autor ou título");
        System.out.println("2 - Listar livros registrados");
        System.out.println("3 - Listar autores registrados");
        System.out.println("4 - Listar livros por idioma");
        System.out.println("5 - Listar autores vivos em determinado ano");
        System.out.println("6 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    @Transactional
    private void buscarLivrosPorTermo(Scanner scanner) {
        System.out.print("Digite o termo de busca (autor ou título): ");
        String termoBusca = scanner.nextLine();

        try {
            List<Livro> livrosEncontrados = gutendexService.fetchLivrosFromGutendex(termoBusca);

            if (livrosEncontrados.isEmpty()) {
                System.out.println("Nenhum livro encontrado para '" + termoBusca + "'.");
            } else {
                System.out.println("Livros encontrados para '" + termoBusca + "':");
                for (Livro livro : livrosEncontrados) {
                    imprimirLivro(livro);
                }
            }
        } catch (GutendexApiException e) {
            System.err.println("Erro ao buscar livros: " + e.getMessage());
        }
    }

    @Transactional
    private void listarLivrosRegistrados() {
        List<Livro> livros = livroRepository.findAll();

        if (livros.isEmpty()) {
            System.out.println("Não há livros registrados.");
        } else {
            System.out.println("Livros registrados:");
            for (Livro livro : livros) {
                imprimirLivro(livro);
            }
        }
    }

    @Transactional
    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("Não há autores registrados.");
        } else {
            System.out.println("Autores registrados:");
            for (Autor autor : autores) {
                imprimirAutor(autor);
            }
        }
    }

    @Transactional
    private void listarLivrosPorIdioma(Scanner scanner) {
        System.out.print("Digite o idioma: ");
        String idioma = scanner.nextLine();

        List<Livro> livros = livroRepository.findByIdioma(idioma);

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma '" + idioma + "'.");
        } else {
            System.out.println("Livros encontrados para o idioma '" + idioma + "':");
            for (Livro livro : livros) {
                imprimirLivro(livro);
            }
        }
    }

    @Transactional
    private void listarAutoresVivosNoAno(Scanner scanner) {
        System.out.print("Digite o ano: ");
        int ano = Integer.parseInt(scanner.nextLine());

        List<Autor> autores = autorRepository.findAutoresVivosNoAno(ano);

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado que estava vivo no ano '" + ano + "'.");
        } else {
            System.out.println("Autores vivos no ano '" + ano + "':");
            for (Autor autor : autores) {
                imprimirAutor(autor);
            }
        }
    }

    private void imprimirLivro(Livro livro) {
        System.out.println(ANSI_YELLOW + "Título: " + livro.getTitulo() + ANSI_RESET);
        System.out.println("Autores:");
        livro.getAutores().forEach(this::imprimirAutor);
        System.out.println("Idioma: " + livro.getIdioma());
        System.out.println("Número de downloads: " + livro.getNumeroDownloads());
        System.out.println(""); // linha em branco para separar os livros
    }

    private void imprimirAutor(Autor autor) {
        System.out.println(ANSI_BLUE + "  - Nome: " + autor.getNome() + ANSI_RESET);
        System.out.println(ANSI_BLUE + "    Ano de Nascimento: " + autor.getAnoNascimento() + ANSI_RESET);
        System.out.println(ANSI_BLUE + "    Ano de Falecimento: " + autor.getAnoFalecimento() + ANSI_RESET);
    }
}
