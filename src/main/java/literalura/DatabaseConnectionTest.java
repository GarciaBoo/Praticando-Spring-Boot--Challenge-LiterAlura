package literalura;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DatabaseConnectionTest {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseConnectionTest(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static void main(String[] args) {
        // Configuração para exibir apenas logs de erro
        System.setProperty("logging.level.root", "ERROR");
        SpringApplication.run(DatabaseConnectionTest.class, args);
    }

    @Component
    public static class TestRunner implements CommandLineRunner {

        private final JdbcTemplate jdbcTemplate;

        @Autowired
        public TestRunner(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        @Override
        public void run(String... args) {
            // Verificar se o nível de log está configurado como DEBUG
            if (System.getProperty("logging.level.root").equalsIgnoreCase("DEBUG")) {
                try {
                    // Inserir dados na tabela 'musicas'
                    jdbcTemplate.update("INSERT INTO musicas (nome, album, ano_lancamento) VALUES (?, ?, ?)",
                            "Believer", "Evolve", 2017);
                    jdbcTemplate.update("INSERT INTO musicas (nome, album, ano_lancamento) VALUES (?, ?, ?)",
                            "Radioactive", "Night Visions", 2012);
                    jdbcTemplate.update("INSERT INTO musicas (nome, album, ano_lancamento) VALUES (?, ?, ?)",
                            "Demons", "Night Visions", 2012);
                    jdbcTemplate.update("INSERT INTO musicas (nome, album, ano_lancamento) VALUES (?, ?, ?)",
                            "Whatever It Takes", "Evolve", 2017);

                    System.out.println("Inserção de dados na tabela 'musicas' concluída.");

                    // Consultar e imprimir os dados da tabela 'musicas' em azul
                    String query = "SELECT * FROM musicas";
                    jdbcTemplate.query(query, (rs, rowNum) -> {
                        String id = rs.getString("id");
                        String nome = rs.getString("nome");
                        String album = rs.getString("album");
                        int anoLancamento = rs.getInt("ano_lancamento");

                        // Imprimir em azul
                        System.out.println("\u001B[34mID: " + id + ", Nome: " + nome + ", Album: " + album + ", Ano de Lançamento: " + anoLancamento + "\u001B[0m");
                        return null;
                    });

                } catch (Exception e) {
                    System.err.println("Erro ao testar conexão com a tabela 'musicas': " + e.getMessage());
                }
            } else {
                System.out.println("A inserção e consulta de dados só é executada em modo DEBUG.");
            }
        }
    }
}
