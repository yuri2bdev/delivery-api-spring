package br.com.delivery.deliveryapi.service;

import br.com.delivery.deliveryapi.model.Pedido;
import br.com.delivery.deliveryapi.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido obterPedidoPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido cadastrarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void atualizarPedido(Long id, Pedido pedido) {
        Pedido pedidoExistente = pedidoRepository.findById(id).orElse(null);
        if (pedidoExistente != null) {
            pedidoExistente.setDescricao(pedido.getDescricao());
            pedidoExistente.setCliente(clienteService.obterClientePorId(pedido.getCliente().getIdCliente()));
            pedidoRepository.save(pedidoExistente);
        }
    }

    public void deletarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
