import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ImageComparator {

    public static void main(String[] args) {
        try {
            // Carregar as imagens
            BufferedImage img1 = ImageIO.read(new File("C:\\Users\\Alex Rodrigues\\Desktop\\img1.png"));
            BufferedImage img2 = ImageIO.read(new File("C:\\Users\\Alex Rodrigues\\Desktop\\img2.png"));
            BufferedImage mask = ImageIO.read(new File("C:\\Users\\Alex Rodrigues\\Desktop\\mask.png"));

            // Verificar se as imagens e a máscara têm o mesmo tamanho
            if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight() || img1.getWidth() != mask.getWidth() || img1.getHeight() != mask.getHeight()) {
                System.out.println("As imagens ou a máscara têm tamanhos diferentes!");
                return;
            }

            // Criar a imagem de diferença
            BufferedImage diffImage = new BufferedImage(img1.getWidth(), img1.getHeight(), BufferedImage.TYPE_INT_ARGB);

            int totalPixels = img1.getWidth() * img1.getHeight();
            int pixelsDiferentes = 0;

            for (int y = 0; y < img1.getHeight(); y++) {
                for (int x = 0; x < img1.getWidth(); x++) {
                    // Verificar se o pixel está na área que deve ser comparada
                    int maskPixel = mask.getRGB(x, y);
                    int maskRed = (maskPixel >> 16) & 0xFF;
                    int maskGreen = (maskPixel >> 8) & 0xFF;
                    int maskBlue = maskPixel & 0xFF;

                    // Se a máscara for preta (0), ignora a comparação nesse pixel
                    if (maskRed == 0 && maskGreen == 0 && maskBlue == 0) {
                        diffImage.setRGB(x, y, img1.getRGB(x, y));  // Manter o pixel original
                        continue;
                    }

                    int pixel1 = img1.getRGB(x, y);
                    int pixel2 = img2.getRGB(x, y);

                    // Se os pixels forem diferentes, marca a diferença na imagem de diferença
                    if (pixel1 != pixel2) {
                        diffImage.setRGB(x, y, Color.RED.getRGB());  // Marca a diferença em vermelho
                        pixelsDiferentes++;
                    } else {
                        diffImage.setRGB(x, y, pixel1);  // Mantém o pixel igual
                    }
                }
            }

            // Calcular a similaridade
            double porcentagemSimilaridade = ((totalPixels - pixelsDiferentes) / (double) totalPixels) * 100;

            // Salvar a imagem de diferença
            File outputfile = new File("diff.png");
            ImageIO.write(diffImage, "PNG", outputfile);

            // Gerar o relatório HTML
            HtmlReportGenerator.generateHtmlReport(totalPixels, pixelsDiferentes, porcentagemSimilaridade);

            System.out.println("Relatório HTML gerado com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao carregar as imagens: " + e.getMessage());
        }
    }
}
