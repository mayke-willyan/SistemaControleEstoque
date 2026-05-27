import java.util.Scanner;
import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int max = 100;
        String[] nomes = new String[max];
        int[] codigos = new int[max];
        double[] precos = new double[max];
        int[] quantidades = new int[max];

        String option;
        int totalProdutos = 0;
        int proxCodigo = 1;

        String menu = "=== SISTEMA DE CONTROLE DE ESTOQUE ===\n\n" +
                      "1. Cadastrar novo produto\n" +
                      "2. Listar todos os produtos\n" +
                      "3. Buscar produto por nome\n" +
                      "4. Registrar entrada de estoque (Compra)\n" +
                      "5. Registrar saída de estoque (Venda)\n" +
                      "6. Excluir produto do sistema\n" +
                      "7. Listar produtos com estoque zerado\n" +
                      "8. Calcular valor total do estoque\n" +
                      "9. Atualizar preço do produto\n" +
                      "10. Sair\n\n" +
                      "Escolha uma opção:";

        do {

            try{
                option = JOptionPane.showInputDialog(menu);
                int optionInteger = Integer.parseInt(option);

                if (optionInteger > 10 || optionInteger < 1){
                    JOptionPane.showMessageDialog(null, "Opção Invalida","Erro", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                if (option == null){
                    JOptionPane.showMessageDialog(null, "Programa Encerrado");
                    break;
                }

                switch (optionInteger) {

                    case 1: 

                        try{

                            String nome = JOptionPane.showInputDialog("Digite o NOME do produto:");

                            if (nome == null){
                                JOptionPane.showMessageDialog(null, "Cadastro cancelado, nome não pode ser vazio","Erro", JOptionPane.ERROR_MESSAGE);
                                break;
                            }

                            String preco = JOptionPane.showInputDialog("Digite o PREÇO do produto:");

                            double precoNumero = Double.parseDouble(preco);

                            if (precoNumero < 0 || preco == null){
                                JOptionPane.showMessageDialog(null, "Erro Digite o valor do preço corretamente", "Erro", JOptionPane.ERROR_MESSAGE);
                                break;
                            }


                            codigos[totalProdutos] = proxCodigo;
                            nomes[totalProdutos] = nome;
                            precos[totalProdutos] = precoNumero;
                            quantidades[totalProdutos] = 0;

                            JOptionPane.showMessageDialog(null, 
                                "Produto cadastrado com sucesso!\n" +
                                "Nome: " + nomes[totalProdutos] + "\n" +
                                "Código Gerado: " + codigos[totalProdutos], "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                            totalProdutos ++;
                            proxCodigo ++;

                            

                        }

                        catch (NumberFormatException e ){
                            JOptionPane.showMessageDialog(null, "Preço inválido! Digite apenas números e use pontos para centavos ", "Erro", JOptionPane.ERROR_MESSAGE);
                        }

                        break;
                    
                    case 2:
                        
                }

                
                System.out.println(codigos[0] + " || " + nomes[0] + " || " + precos[0] + " || " + quantidades[0] );
                
                break;
            }

            catch (NumberFormatException e ){
                JOptionPane.showMessageDialog(null, "Erro digite apenas numeros", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        }

        while (true);
    }
}
