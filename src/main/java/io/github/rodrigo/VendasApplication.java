package io.github.rodrigo;

import ch.qos.logback.core.net.server.Client;
import io.github.rodrigo.domain.entity.Cliente;
import io.github.rodrigo.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Value("${application.name}")
    private String applicationName;

    @GetMapping("/hello")
    public String helloWorld(){
        return applicationName;
    }

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return  args -> {
            System.out.println("Salvando Clientes");

            clientes.salvar(new Cliente("Rodrigo"));
            clientes.salvar(new Cliente("Outro Cliente"));

            List<Cliente> todosCLientes = clientes.obterTodos();
            todosCLientes.forEach(System.out::println);


            /*System.out.println("Atualizando Clientes");
            todosCLientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado.");
                clientes.atualizar(c);
            });

            todosCLientes = clientes.obterTodos();
            todosCLientes.forEach(System.out::println);

            System.out.println("Buscando Clientes");
            clientes.buscarPorNome("Cli").forEach(System.out::println);

            System.out.println("Deletando Clientes");
            clientes.obterTodos().forEach(c -> {
                clientes.deletar(c);
            });

            todosCLientes = clientes.obterTodos();
            if(todosCLientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado.");
            }else{
                todosCLientes.forEach(System.out::println);
            }*/
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
