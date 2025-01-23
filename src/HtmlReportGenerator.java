import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
class HtmlReportGenerator {

    public static void generateHtmlReport(int totalPixels, int pixelsDiferentes, double porcentagemSimilaridade) {
        try {
            String statusMessage = "";

            if (porcentagemSimilaridade > 90) {
                statusMessage = "<div class='status'><img src='https://img.icons8.com/emoji/48/000000/check-mark-emoji.png' alt='Aprovado' /> <span>APROVADO</span></div>";
            }

            String htmlReport = "<html><head>" +
                    "<meta charset=\"UTF-8\">" +
                    "<style>" +
                    "body { font-family: Arial, sans-serif; background-color: #f4f4f9; color: #333; margin: 0; padding: 0; min-height: 100vh; display: flex; flex-direction: column; }" +
                    "header { background-color: #2c3e50; color: white; padding: 20px; text-align: center; }" +
                    "section { padding: 20px; flex: 1; }" +
                    ".metrics { display: flex; justify-content: space-around; margin: 20px 0; }" +
                    ".metric { background: #ecf0f1; padding: 10px 20px; border-radius: 8px; text-align: center; }" +
                    ".images { display: flex; justify-content: space-around; margin-top: 20px; }" +
                    ".images img { max-width: 30%; border: 2px solid #ccc; border-radius: 8px; cursor: pointer; transition: transform 0.3s ease; }" +
                    ".images img:hover { transform: scale(1.05); }" +
                    ".footer { text-align: center; padding: 10px; background-color: #2c3e50; color: white; margin-top: auto; }" +
                    ".status { text-align: center; margin: 20px 0; font-size: 1.5em; color: green; }" +
                    ".status img { vertical-align: middle; margin-right: 10px; }" +
                    ".modal { display: none; position: fixed; z-index: 1000; left: 0; top: 0; width: 100%; height: 100%; overflow: auto; background-color: rgba(0,0,0,0.8); }" +
                    ".modal-content { margin: 10% auto; display: block; max-width: 80%; }" +
                    ".modal-content img { width: 100%; height: auto; }" +
                    ".close { position: absolute; top: 15px; right: 35px; color: white; font-size: 40px; font-weight: bold; cursor: pointer; }" +
                    "</style>" +
                    "<script>" +
                    "function openModal(imgSrc) {" +
                    "  const modal = document.getElementById('imageModal');" +
                    "  const modalImg = document.getElementById('modalImage');" +
                    "  modal.style.display = 'block';" +
                    "  modalImg.src = imgSrc;" +
                    "}" +
                    "function closeModal() {" +
                    "  document.getElementById('imageModal').style.display = 'none';" +
                    "}" +
                    "</script></head><body>" +
                    "<header><h1>Relatório de Comparação de Imagens</h1></header>" +
                    "<section>" +
                    statusMessage +
                    "<div class='metrics'>" +
                    "<div class='metric'><h3>Total de Pixels</h3><p>" + totalPixels + "</p></div>" +
                    "<div class='metric'><h3>Pixels Diferentes</h3><p>" + pixelsDiferentes + "</p></div>" +
                    "<div class='metric'><h3>Similaridade</h3><p>" + String.format("%.2f", porcentagemSimilaridade) + "%</p></div>" +
                    "</div>" +
                    "<div class='images'>" +
                    "<div><h3>Imagem 1</h3><img src='img1.png' alt='Imagem 1' onclick=\"openModal(this.src)\" /></div>" +
                    "<div><h3>Imagem 2</h3><img src='img2.png' alt='Imagem 2' onclick=\"openModal(this.src)\" /></div>" +
                    "<div><h3>Diferenças</h3><img src='diff.png' alt='Imagem de Diferenças' onclick=\"openModal(this.src)\" /></div>" +
                    "</div>" +
                    "</section>" +
                    "<div id='imageModal' class='modal'>" +
                    "  <span class='close' onclick='closeModal()'>×</span>" +
                    "  <img id='modalImage' class='modal-content' />" +
                    "</div>" +
                    "<footer class='footer'>AUTOMACAO DE TESTES E NOVA PLATAFORMA</footer>" +
                    "</body></html>";

            // Salvar o relatório HTML
            FileWriter writer = new FileWriter("relatorio_comparacao_com_mascara.html");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(htmlReport);
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("Erro ao salvar o relatório HTML: " + e.getMessage());
        }
    }
}

