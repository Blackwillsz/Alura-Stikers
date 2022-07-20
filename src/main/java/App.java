import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        //Fazer conexão http e buscar os top 250 filmes
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI endereco = URI.create(url);
        var httpClient = HttpClient.newHttpClient();
        var client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        //extrair só os dados que interessam  (título, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

<<<<<<< HEAD
        // exibir e manipular os dados
        for (Map<String, String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeDoArquivo = titulo + ".png";

            var geradora = new GeradoraDeFigurinhas();
            geradora.cria(inputStream, nomeDoArquivo);

            System.out.println(titulo);
            System.out.println();

=======
        //exibir e manipular os dados
        for (Map<String, String> filmes : listaDeFilmes){
            System.out.println(filmes.get("fullTitle"));
            System.out.println(filmes.get("image"));
            System.out.println(filmes.get("imDbRating"));
            System.out.println();
>>>>>>> e17be9d68c71238dd9f8e79c8549722611b6b77a
        }
    }
}
