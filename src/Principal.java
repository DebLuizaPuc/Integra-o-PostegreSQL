import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("Menu:");
            System.out.println("1. Listar");
            System.out.println("2. Inserir");
            System.out.println("3. Excluir");
            System.out.println("4. Atualizar");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    List<Usuario> lista = dao.listar();
                    for (Usuario usuario : lista) {
                        System.out.println(usuario.getCodigo() + " - " + usuario.getLogin() + " - " + usuario.getSexo());
                    }
                    break;
                case 2:
                    System.out.print("Login: ");
                    String login = scanner.next();
                    System.out.print("Senha: ");
                    String senha = scanner.next();
                    System.out.print("Sexo (M/F): ");
                    char sexo = scanner.next().charAt(0);
                    Usuario usuario = new Usuario(0, login, senha, sexo);
                    dao.inserir(usuario);
                    break;
                case 3:
                    System.out.print("Código do usuário a excluir: ");
                    int codigoExcluir = scanner.nextInt();
                    dao.excluir(codigoExcluir);
                    break;
                case 4:
                    System.out.print("Código do usuário a atualizar: ");
                    int codigoAtualizar = scanner.nextInt();
                    System.out.print("Novo Login: ");
                    String novoLogin = scanner.next();
                    System.out.print("Nova Senha: ");
                    String novaSenha = scanner.next();
                    System.out.print("Novo Sexo (M/F): ");
                    char novoSexo = scanner.next().charAt(0);
                    Usuario usuarioAtualizado = new Usuario(codigoAtualizar, novoLogin, novaSenha, novoSexo);
                    dao.atualizar(usuarioAtualizado);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 5);

        scanner.close();
    }
}
