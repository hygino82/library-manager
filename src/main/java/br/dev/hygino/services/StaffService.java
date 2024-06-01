package br.dev.hygino.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.dto.StaffDTO;
import br.dev.hygino.dto.StaffInsertDTO;
import br.dev.hygino.entities.Staff;
import br.dev.hygino.repositories.StaffRepository;
import br.dev.hygino.services.exceptions.StaffNotFoundException;
import br.dev.hygino.services.exceptions.StudentNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Transactional
    public StaffDTO insert(@Valid StaffInsertDTO dto) {
        Staff entity = new Staff();
        copyDtoToEntity(dto, entity);
        return new StaffDTO(staffRepository.save(entity));
    }

    private void copyDtoToEntity(@Valid StaffInsertDTO dto, Staff entity) {
        entity.setName(dto.name());
        entity.setPosition(dto.position());
        entity.setPhone(dto.phone());
        entity.setEmail(dto.email());
    }

    @Transactional(readOnly = true)
    public Page<StaffDTO> findAll(Pageable pageable) {
        Page<Staff> page = staffRepository.findAll(pageable);
        return page.map(StaffDTO::new);
    }

    @Transactional(readOnly = true)
    public StaffDTO findById(Long id) {
        Staff entity = staffRepository.findById(id)
                .orElseThrow(() -> new StaffNotFoundException("Funcionário com o Id: " + id + " não encontrado"));
        return new StaffDTO(entity);
    }

    @Transactional
    public StaffDTO update(Long id, @Valid StaffInsertDTO dto) {
        try {
            Staff entity = staffRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            return new StaffDTO(staffRepository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new StaffNotFoundException("Funcionário com o Id: " + id + " não encontrado");
        }
    }

    public void remove(Long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Impossível remover o Funcionário com o Id: " + id + ", pois ele não existe"));
        staffRepository.delete(staff);
    }
}
