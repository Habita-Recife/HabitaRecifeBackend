package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.EmpresaDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Empresa;
import br.com.habita_recife.habita_recife_backend.domain.repository.EmpresaRepository;
import br.com.habita_recife.habita_recife_backend.service.EmpresaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaServiceImpl(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }


    @Override
    public List<Empresa> listarTodos() {
        return empresaRepository.findAll();
    }

    @Override
    public Optional<Empresa> buscarPorId(Long id) {
        return empresaRepository.findById(id);
    }

    @Override
    public Empresa salvar(EmpresaDTO empresaDTO) {
        Empresa empresa = new Empresa();
        empresa.setNomeEmpresa(empresaDTO.getNome_empresa());

        return empresaRepository.save(empresa);
    }


    @Override
    public Empresa atualizar(Long id, EmpresaDTO empresaDTO) {
        Empresa empresaExistente = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada com o id: " + id));

        empresaExistente.setNomeEmpresa(empresaDTO.getNomeEmpresa());
        return empresaRepository.save(empresaExistente);
    }

    @Override
    public void excluir(Long id) {

        empresaRepository.deleteById(id);

    }
}
