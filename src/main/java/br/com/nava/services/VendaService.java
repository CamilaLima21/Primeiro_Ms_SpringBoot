package br.com.nava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.dtos.VendaDTO;
import br.com.nava.entities.VendaEntity;
import br.com.nava.repositories.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;

	
	public List<VendaDTO> getAll(){
		List<VendaEntity> lista = vendaRepository.findAll();
		
		List<VendaDTO> listaDTO = new ArrayList<>();
		
		for (VendaEntity vendaEntity : lista) {
			listaDTO.add(vendaEntity.toDTO());
		}
		return listaDTO;		
	}
	
	public VendaDTO getOne(int id) {
		Optional<VendaEntity> optional = vendaRepository.findById(id);
		VendaEntity venda = optional.orElse(new VendaEntity());
		return venda.toDTO();
	}
	
	public VendaDTO save(VendaEntity venda) {
		return vendaRepository.save(venda).toDTO();
	
	}
	
	public VendaDTO update(int id, VendaEntity venda) {

		Optional<VendaEntity> optional = vendaRepository.findById(id);

		if (optional.isPresent()) {

			VendaEntity vendaBD = optional.get();
			vendaBD.setValorTotal(venda.getValorTotal());
			
			return vendaRepository.save(vendaBD).toDTO();
		}

		else {
			return new VendaEntity().toDTO();
		}
	}
	
	public void delete(int id) {
		vendaRepository.deleteById(id);
	}
}