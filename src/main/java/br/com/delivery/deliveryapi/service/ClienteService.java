package br.com.delivery.deliveryapi.service;

import br.com.delivery.deliveryapi.model.Cliente;
import br.com.delivery.deliveryapi.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obterClientePorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void atualizarCliente(Long id, Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findById(id).orElse(null);
        if (clienteExistente != null) {
            clienteExistente.setNome(cliente.getNome());
            clienteExistente.setEmail(cliente.getEmail());
            clienteExistente.setTelefone(cliente.getTelefone());
            clienteRepository.save(clienteExistente);
        }
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
