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
        //String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-07-15&end_date=2022-07-19";
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


        // exibir e manipular os dados
        for (int i = 0; i < 5; i++) {

            Map<String, String> filme = listaDeFilmes.get(i);

            //String urlImagem = filme.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");
            String urlImagem = filme.get("url").replaceAll("(@+)(.*).jpg$", "$1.jpg");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeDoArquivo = "saida/" + titulo + ".png";

            var geradora = new GeradoraDeFigurinhas();
            geradora.cria(inputStream, nomeDoArquivo);

            System.out.println(titulo);
            System.out.println();


        //exibir e manipular os dados
        for (Map<String, String> filmes : listaDeFilmes) {
            System.out.println(filmes.get("fullTitle"));
            System.out.println(filmes.get("image"));
            System.out.println(filmes.get("imDbRating"));
            System.out.println();
        }

        }
    }
}
