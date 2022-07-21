public class Conteudo {

    private final String titulo;
    private final String urlimagem;

    public Conteudo(String titulo, String urlImagem){
        this.titulo = titulo;
        this.urlimagem = urlImagem;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getUrlimagem() {
        return urlimagem;
    }

}
