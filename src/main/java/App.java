import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        //Fazer conexão http e buscar os top 250 filmes

//        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
//        ExtratorDeConteudoDoIMDB extrator = new ExtratorDeConteudoDoIMDB();

        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-07-15&end_date=2022-07-19";
        ExtratorDeConteudoDaNasa extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClientHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados

        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 4; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlimagem()).openStream();
            String nomeDoArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeDoArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();

        }
    }
}
