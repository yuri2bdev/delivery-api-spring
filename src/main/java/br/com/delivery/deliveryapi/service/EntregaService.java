package br.com.delivery.deliveryapi.service;

import br.com.delivery.deliveryapi.model.Entrega;
import br.com.delivery.deliveryapi.repository.EntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntregaService {

    private final EntregaRepository entregaRepository;
    private final PedidoService pedidoService;

    public List<Entrega> listarEntregas() {
        return entregaRepository.findAll();
    }

    public Entrega obterEntregaPorId(Long id) {
        return entregaRepository.findById(id).orElse(null);
    }

    public Entrega cadastrarEntrega(Entrega entrega) {
        return entregaRepository.save(entrega);
    }

    public void atualizarEntrega(Long id, Entrega entrega) {
        Entrega entregaExistente = entregaRepository.findById(id).orElse(null);
        if (entregaExistente != null) {
            entregaExistente.setEndereco(entrega.getEndereco());
            entregaExistente.setPedido(pedidoService.obterPedidoPorId(entrega.getPedido().getId()));
            entregaRepository.save(entregaExistente);
        }
    }

    public void deletarEntrega(Long id) {
        entregaRepository.deleteById(id);
    }
}
