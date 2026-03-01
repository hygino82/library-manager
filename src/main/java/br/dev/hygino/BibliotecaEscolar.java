package br.dev.hygino;

import br.dev.hygino.dao.BookDao;
import br.dev.hygino.dao.UserDao;
import br.dev.hygino.dto.InsertBookDto;
import br.dev.hygino.dto.InsertUserDto;
import br.dev.hygino.dto.ResponseUserMinDto;
import br.dev.hygino.dto.UpdateUserDto;
import br.dev.hygino.exceptions.DatabaseException;
import br.dev.hygino.services.BookService;
import br.dev.hygino.services.UserService;

import java.util.List;
import javax.swing.JOptionPane;

public class BibliotecaEscolar {

    private static BookService bookService;
    private static UserService service;

    public BibliotecaEscolar() {
        try {

            bookService = new BookService(new BookDao());
            service = new UserService(new UserDao());
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void main(String[] args) {
        new BibliotecaEscolar();
        
        //listarUsuarios();
        //inserirUsuario();
        //buscarPorId();
        //atualizarUsuario();
        //testeRetornoLivro();
        //inserirLivro();  
         listarLivros();
    }

    private static void inserirLivro() {
        InsertBookDto dto = new InsertBookDto("As pupilas do senhor reitor", "Julio Dinis", "pt-julio01", 355, "Martin Claret", 2);
        bookService.insertBook(dto);
    }

    private static void testeRetornoLivro() {
        final var userId = 2L;
        service.changeLoanStatus(userId, true);
        System.out.println("Status atualizado!");

        try {
            final var result = service.getUserById(userId);
            System.out.println(result);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private static void listarUsuarios() {
        final List<ResponseUserMinDto> users = service.getUsers("");
        users.forEach(System.out::println);
    }

    private static void inserirUsuario() {
        final InsertUserDto dto = new InsertUserDto("Juvenal Silveira Pinto", "4632321781", "NONO");
        service.insertUser(dto);
    }

    private static void buscarPorId() {
        try {
            final var result = service.getUserById(3L);
            System.out.println(result);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private static void atualizarUsuario() {
        final UpdateUserDto dto = new UpdateUserDto(2, "Juvenal Silveira Pinto", "4632321781", "NONO");
        final var result = service.updateUser(dto);

        if (result) {
            JOptionPane.showMessageDialog(null, "Usuário atualizado!");
        }
    }

    private static void listarLivros() {
        try {
            final var result = bookService.getBooks("ing");
            result.forEach(System.out::println);
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
